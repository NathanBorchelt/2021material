
import {throwError as observableThrowError,  Observable ,  BehaviorSubject ,  Subject } from 'rxjs';
import { Injectable } from '@angular/core';
import { CanPerformAction } from '../interfaces/CanPerformAction';
import { Organization } from '../interfaces/Organization';

import { EndpointsService, CP_UNISON_API } from './endpoints.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { CPResponse } from '../interfaces/CPResponse';
import { map, publishReplay, refCount, share } from 'rxjs/operators';
import { Org } from '../interfaces/orgProfile';

@Injectable({providedIn: 'root'})
export class OrganizationsService {

  //-------------------------------------------------------------------------------
  // PRIVATE FIELDS
  //-------------------------------------------------------------------------------
  private organizations: Organization[];
  private currentOrganizationSubject: BehaviorSubject<Organization>;

  private organizationSubject: BehaviorSubject<Organization[]>;

  private organizations$: Observable<Organization[]>;

  private dataStore: Organization[];

  private orgs$: Observable<Organization[]>;

  private orgUpdatedSubject = new Subject<Organization>();
  //------------------------------------------------//

  //-------------------------------------------------------------------------------
  // PUBLIC FIELDS
  //-------------------------------------------------------------------------------
  currentOrganization$: Observable<Organization>;

  //-------------------------------------------------------------------------------
  // CONSTRUCTOR
  //-------------------------------------------------------------------------------
  constructor(private http: HttpClient, private endpoints: EndpointsService) {
    this.dataStore = [];
    this.organizationSubject = new BehaviorSubject<Organization[]>(this.dataStore);
    this.organizations$ = this.organizationSubject.asObservable();
    this.currentOrganizationSubject = new BehaviorSubject<Organization>(null);
    this.currentOrganization$ = this.currentOrganizationSubject.asObservable();
    let org = this.loadOrganization();
    if (org) {
      this.currentOrganization = this.loadOrganization();
    } else {
      this.clearCurrentOrganization();
    }
  }

  //-------------------------------------------------------------------------------
  // PROPERTIES
  //-------------------------------------------------------------------------------
  private _currentOrganization: Organization;
  get currentOrganization(): Organization {
    return this._currentOrganization;
  }
  set currentOrganization(org: Organization) {
    this._currentOrganization = org;
    this.saveOrganization(this._currentOrganization);
    this.currentOrganizationSubject.next(this._currentOrganization);
  }

  get hasCurrentOrganization(): boolean {
    return this.currentOrganization && this.currentOrganization.RecordId > -1;
  }

  //-------------------------------------------------------------------------------
  // PUBLIC METHODS
  //-------------------------------------------------------------------------------

  checkDelete(id: number): Observable<CanPerformAction> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/Organizations/CanDelete/${id}`))
      .pipe(map((response: CPResponse<CanPerformAction>) => {
        return response.data;
      }));
  }

  deleteOrganization(organization: Organization): Observable<unknown> {
    return this.http.delete(this.endpoints.build(CP_UNISON_API, `/v1/Organizations/${organization.RecordId}`))
      .pipe(map((response: HttpResponse<unknown>) => {
        const index = this.dataStore.findIndex((o: Organization) => o.RecordId === organization.RecordId);
        this.dataStore.splice(index, 1);
        this.organizationSubject.next(this.dataStore);
      }), share());
  }

  getOrganizations(): Observable<Organization[]> {
    return this.getData();
  }

  getOrganizationsList(): Observable<Organization[]> {
    return this.getList();
  }

  getOrganization(id: number): Observable<Organization> {
    return this.getData().map(v => v.find(o => o.RecordId === id));
  }

  getOrg(id: number): Observable<Organization> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, '/v1/organizations/' + id), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<Organization>>) => {
        if (response.status > 299) {
          throw new Error('Get Organization failed: ' + response.status);
        }
        return response.body.data;
      }));
  }

  getNewOrg(): Organization {
    return { RecordId: -1 } as Organization;
  }

  clearCurrentOrganization() {
    this.orgs$ = null;
    let org = { RecordId: -1, Name: '' } as Organization;
    this.saveOrganization(org);
    this.currentOrganization = org;
  }

  updateOrganization(organization: Organization): Observable<Organization> {
    return this.http.put(this.endpoints.build(CP_UNISON_API, '/v1/organizations'), organization)
      .pipe(map((response: CPResponse<Organization>) => {
        const i = this.dataStore.findIndex(x => x.RecordId === organization.RecordId);
        this.dataStore[i] = organization;
        this.organizationSubject.next(this.dataStore);
        return response.data;
      }), share());
  }

  validateSharedLabel(label: string): Observable<any> {
    const validateUrl = this.endpoints.build(CP_UNISON_API, `/v1/organizations/validatesharedlabel/${label}`);
    return this.http.get<HttpResponse<CPResponse<any>>>(validateUrl)
      .pipe(map((response) => {
        if (response.status > 299) {
          throw new Error('Validate label failed: ' + response.status);
        }
        return response['data'];
      }));
  }


  //-------------------------------------------------------------------------------
  // PRIVATE METHODS
  //-------------------------------------------------------------------------------
  private getData(): Observable<Organization[]> {
    if (!this.orgs$) {
      this.orgs$ = this.http.get(this.endpoints.build(CP_UNISON_API, '/v1/organizations/getbyuserid'))
        .pipe(map((response: CPResponse<Organization[]>) => response.data), publishReplay(1), refCount())
        .catch(this.handleError);
    }
    return this.orgs$;
  }

  // Gets list of organizations without the userid
  private getList(): Observable<Organization[]> {
    this.http.get(this.endpoints.build(CP_UNISON_API, '/v1/organizations'))
      .pipe(map((response: CPResponse<Organization[]>) => response.data))
      .subscribe(o => {
        this.dataStore = o;
        this.organizationSubject.next(this.dataStore);
      });
    return this.organizations$;
  }

  private saveOrganization(org: Organization) {
    localStorage.setItem('org', JSON.stringify(org));
  }

  private loadOrganization(): Organization {
    return JSON.parse(localStorage.getItem('org')) as Organization;
  }

  private handleError(error: Response) {
    return observableThrowError(error || 'Server error');
  }

  public isCurrentFulfiller() {
    return this.isFulfiller(this.currentOrganization);
  }

  public isFulfiller(org: Organization) {
    return org && org.Type === 'V';
  }
}
