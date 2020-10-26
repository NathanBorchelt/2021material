import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { AuthService } from './auth.service';
import { EndpointsService, CP_UNISON_API, CP_PRINTER_API, CP_SCANNER_API } from './endpoints.service';
import { Dealership } from '../interfaces/Dealership';
import { OrganizationsService } from './organizations.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, share } from 'rxjs/operators';
import { CPResponse } from '../interfaces/CPResponse';
import { CanPerformAction } from '../interfaces/CanPerformAction';
import { User } from '../interfaces/User';
import { SimpleDealership } from '../interfaces/SimpleDealership';
import { DealershipAdminView } from '../interfaces/DealershipAdminView';
import { EditDealership } from '../interfaces/EditDealership';
import { APIResult } from '../interfaces/APIResult';

@Injectable({ providedIn: 'root' })
export class DealershipsService {

  dealerships: Observable<Dealership[]>;
  private _dealerships: BehaviorSubject<Dealership[]>;
  private dataStore: Dealership[];

  showCanAccessDMSCheckbox = false;
  dmsCheckboxSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.showCanAccessDMSCheckbox);
  dmsCheckboxObservable: Observable<boolean> = this.dmsCheckboxSubject.asObservable();

  constructor(private http: HttpClient, private orgService: OrganizationsService, private endpoints: EndpointsService,
    private auth: AuthService) {
    this.dataStore = [];
    this._dealerships = new BehaviorSubject<Dealership[]>(this.dataStore);
  }

  // getDealerships(orgId?: number, userId?: number, forceRefresh?: boolean): Observable<Dealership[]> {
  //   return this.refreshData(orgId, userId, forceRefresh);
  // }

  getDealership(id: number): Observable<Dealership> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, '/v1/Dealerships/' + id), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<Dealership>>) => {
        if (response.status > 299) {
          throw new Error('Get dealership failed: ' + response.status);
        }
        return response.body.data;
      }));
  }

  getDealershipV2(dealershipRecordId: number): Observable<DealershipAdminView> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, '/v2/Dealerships/' + dealershipRecordId))
      .pipe(map((response: CPResponse<DealershipAdminView>) => {
        return response.data;
      }));
  }

  getNewDealership(): Dealership {
    return { RecordId: -1, OrganizationId: this.orgService.currentOrganization.RecordId } as Dealership;
  }

  // getPrinterGroups(): Observable<any> {
  //   try {
  //     return this.http.get(this.endpoints.build(CP_PRINTER_API, '/organization/' + this.orgService.currentOrganization.SharedLabel + '/groups'), { observe: 'response' })
  //     .pipe(map((response: HttpResponse<CPResponse<any>>) => {
  //       if (response.status === 204) { return []; }
  //       return response.body.data;
  //     }))
  //   } catch(err) {
  //     return Observable.of(err);
  //   }
  // }

  getScannerGroups(): Observable<any> {
    return this.http.get(this.endpoints.build(CP_SCANNER_API, '/organization/' + this.orgService.currentOrganization.SharedLabel + '/groups'), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<any>>) => {
        if (response.status > 299) {
          throw new Error('Get Scanner groups failed: ' + response.status);
        }
        return response.body.data;
      }));
  }

  canDeleteDealership(id: number): Observable<CanPerformAction> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/Dealerships/CanDelete/${id}`))
      .pipe(map((response: CPResponse<CanPerformAction>) => {
        return response.data;
      }));
  }

  deleteDealership(id: number): Observable<void> {
    return this.http.delete(this.endpoints.build(CP_UNISON_API, `/v1/Dealerships/${id}`))
      .pipe(map(() => {
        const index = this.dataStore.findIndex((dealership: Dealership) => dealership.RecordId === id);
        this.dataStore.splice(index, 1);
        this._dealerships.next(this.dataStore);
      }), share());
  }

  setUserDealershipOn(user: User, dealershipId: number) {
    this.http.post(this.endpoints.build(CP_UNISON_API, '/v1/Users/UserDealership/'),
      { 'userId': user.RecordId, 'dealershipId': dealershipId }, { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<number>>) => {
        if (response.status > 299) {
          throw new Error('Post failed: ' + response.status);
        }
        return response.body.data;
      })).subscribe(d => {
        const index = this.dataStore.findIndex(i => i.RecordId === dealershipId);
        this.dataStore[index].UserDealershipId = d;
        this.canSeeDMSAcessCheckbox(this.dataStore);
        this._dealerships.next(this.dataStore);
      }, err => {
        console.log(err);
      });
  }

  setUserDealershipOff(user: User, dealershipId: number) {
    this.http.delete(this.endpoints.build(CP_UNISON_API, '/v1/Users/UserDealership/' + user.UserDealershipId), { observe: 'response' })
      .pipe(map((response: HttpResponse<null>) => {
        if (response.status > 299) {
          throw new Error('Delete failed: ' + response.status);
        }
      })).subscribe(_ => {
        const index = this.dataStore.findIndex(i => i.RecordId === dealershipId);
        this.dataStore[index].UserDealershipId = 0;
        this.canSeeDMSAcessCheckbox(this.dataStore);
        this._dealerships.next(this.dataStore);
      }, err => {
        console.log(err);
      });
  }

  createDealership(dealership: Dealership): Observable<Dealership> {
    return this.http.post(this.endpoints.build(CP_UNISON_API, '/v1/Dealerships'), dealership)
      .pipe(map((response: CPResponse<Dealership>) => {
        this.dataStore.push(response.data);
        this._dealerships.next(this.dataStore);
        return response.data;
      }));
  }

  createDealershipV2(dealership: DealershipAdminView): Observable<number> {
    return this.http.post(this.endpoints.build(CP_UNISON_API, '/v2/dealerships'), dealership)
      .pipe(map((response: CPResponse<APIResult<number>>) => {
        // RecordId of created dealership
        return response.data.result;
      }), share());
  }

  updateDealership(dealership: Dealership): Observable<Dealership> {
    return this.http.put<CPResponse<Dealership>>(this.endpoints.build(CP_UNISON_API, '/v1/Dealerships'), dealership)
      .pipe(map((response: CPResponse<Dealership>) => {
        const i = this.dataStore.findIndex(x => x.RecordId === dealership.RecordId);
        this.dataStore[i] = dealership;
        this._dealerships.next(this.dataStore);
        return response.data;
      }));
  }

  updateDealershipV2(editDealership: EditDealership): Observable<any> {
    return this.http.put(this.endpoints.build(CP_UNISON_API, '/v2/dealerships'), editDealership)
      .pipe(map((response: CPResponse<Dealership>) => {
        return response.data;
      }), share());
  }

    getDealershipsByUserAndOrg(): Observable<Dealership[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, '/v1/dealerships/GetByOrgAndUser/' + this.orgService.currentOrganization.RecordId), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<any>>) => {
        if (response.body.data.length > 0) {
          this.dataStore = <Dealership[]>response.body.data[0].Dealerships;
          this._dealerships.next(this.dataStore);
          return <Dealership[]>response.body.data[0].Dealerships;
        } else {
          return [];
        }
      }));
  }


  getDealershipsByUserAndOrgExtended(): Observable<Dealership[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, '/v3/Scanner/DealershipsByUserExtended/' + this.orgService.currentOrganization.RecordId), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<Dealership[]>>) => {
        this.dataStore = response.body.data;
        this._dealerships.next(this.dataStore);
        return response.body.data;
      }));
  }

  getDealershipsByUserAndOrgV2(): Observable<SimpleDealership[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, '/v2/dealerships/ByUserAndOrg/' + this.orgService.currentOrganization.RecordId))
      .pipe(map((response: CPResponse<SimpleDealership[]>) => {
        return response.data;
      }));
  }

  private getData(orgId?: number, userId?: number): Observable<Dealership[]> {
    let url = '';
    if (this.auth.isAdmin() || this.auth.isOrgAdmin()) {
      if (userId) {
        url = this.endpoints.build(CP_UNISON_API, '/v1/Dealerships/GetUserDealerships/' + userId + '/' + this.orgService.currentOrganization.RecordId);
      } else if (orgId) {
        url = this.endpoints.build(CP_UNISON_API, '/v1/Dealerships/GetByOrg/' + orgId);
      } else {
        url = this.endpoints.build(CP_UNISON_API, '/v1/Dealerships/GetByOrg/' + this.orgService.currentOrganization.RecordId);
      }
    } else if (this.auth.isManager()) {
      if (userId) {
        url = this.endpoints.build(CP_UNISON_API, '/v2/dealerships/GetUserDealershipsByManager/' + userId);
      } else {
        url = this.endpoints.build(CP_UNISON_API, '/v2/dealerships/GetDealershipsByUser');
      }
    } else {
      url = this.endpoints.build(CP_UNISON_API, '/v1/Dealerships/GetByOrg/' + this.orgService.currentOrganization.RecordId);
    }
    return this.http.get(url, { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<Dealership[]>>) => {
        if (response.status > 299) {
          throw new Error('Refresh failed: ' + response.status);
        }
        return response.body.data;
      }));
  }

  // private refreshData(orgId?: number, userId?: number, forceRefresh?: boolean): Observable<Dealership[]> {
  //   if (!this.dealerships || forceRefresh) {
  //     return this.getData(orgId, userId)
  //       .map(d => {
  //         this.dataStore = d;
  //         this._dealerships.next(this.dataStore);
  //         return this.dataStore;
  //       },
  //         err => {
  //           console.log(err);
  //         });
  //   }
  // }

  canSeeDMSAcessCheckbox(d: Dealership[]) {
    this.showCanAccessDMSCheckbox = false;
    for (let i = 0; i < d.length; i++) {
      if (d[i].IsIntegrated) {
        if (d[i].UserDealershipId) {
          this.showCanAccessDMSCheckbox = true;
          break;
        }
      }
    }
    this.dmsCheckboxSubject.next(this.showCanAccessDMSCheckbox);
  }
}
