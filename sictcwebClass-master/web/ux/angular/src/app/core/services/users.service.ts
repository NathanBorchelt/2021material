import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { EndpointsService, CP_UNISON_API } from './endpoints.service';
import { User } from '../interfaces/User';
import { OrganizationsService } from './organizations.service';
import { AuthService } from './auth.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { CPResponse } from '../interfaces/CPResponse';
import { map, share, flatMap } from 'rxjs/operators';
import { SimpleUser, SimpleUserView, VerySimpleUser } from '../interfaces/SimpleUser';
import { APIResult } from '../interfaces/APIResult';
import { ExportUserRequest } from '../interfaces/ExportUserRequest';
import { ExportUser } from '../interfaces/ExportUser';
import * as _ from 'lodash';

export const EMAIL_IN_USE = 'EMAIL_IN_USE';

@Injectable({providedIn: 'root'})
export class UsersService {

  private users: User[] = [];
  private _users: BehaviorSubject<User[]> = new BehaviorSubject<User[]>(this.users);
  usersObservable: Observable<User[]> = this._users.asObservable();

  constructor(private http: HttpClient,
              private orgService: OrganizationsService,
              private auth: AuthService,
              private endpoints: EndpointsService) {}

  getSimpleUsersv2(): Observable<VerySimpleUser[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/users/byorg/${this.orgService.currentOrganization.RecordId}`))
      .pipe(map((verySimpleUsersResponse: CPResponse<VerySimpleUser[]>) => {
        return verySimpleUsersResponse.data;
      }));
  }

  getUserv2(userId: number): Observable<SimpleUserView> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/users/${userId}`))
      .pipe(map((userResponse: CPResponse<SimpleUserView>) => {
        return userResponse.data;
      }));
  }

  getDealershipUsers(): Observable<VerySimpleUser[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/users/fordealershipadmin/${this.orgService.currentOrganization.RecordId}`))
      .pipe(map((response: CPResponse<VerySimpleUser[]>) => {
        return response.data;
      }));
  }

  updateUserv2(simpleUser: SimpleUser): Observable<any> {
    this.auth.clearCPProfile(simpleUser.RecordId);
    return this.http.put(this.endpoints.build(CP_UNISON_API, `/v2/users`), simpleUser)
      .pipe(map((response: CPResponse<APIResult<any>>) => {
        this.auth.getCPProfile().subscribe(); //request fresh profile
        return response;
      }));
  }

  createUserv2(simpleUser: SimpleUser): Observable<any> {
    return this.http.post(this.endpoints.build(CP_UNISON_API, `/v2/users`), simpleUser)
      .pipe(map((response: CPResponse<APIResult<any>>) => {
        return response;
    }), share());
  }

  updateUserIfValidEmailv2(user: SimpleUser, initialEmail:string): Observable<any> {
    const email1 = user.Email && user.Email.toLowerCase().trim();
    const email2 = initialEmail && initialEmail.toLowerCase().trim();
    if (email1 === email2) {
      return this.updateUserv2(user).pipe(share());
    }

    return this.canAddUserEmail(user.Email).pipe(flatMap(x => {
      if (!x) {
        const error = <any>new Error('Email address is already in use');
        error.code = EMAIL_IN_USE;
        throw error;
      }
      return this.updateUserv2(user);
    }), share());
  }

  createUserIfValidEmailv2(user: SimpleUser): Observable<any> {
    user.Email = user.Email && user.Email.toLowerCase().trim();
    return this.canAddUserEmail(user.Email).pipe(flatMap(x => {
      if (!x) {
        const error = <any>new Error('Email address is already in use');
        error.code = EMAIL_IN_USE;
        throw error;
      }
      return this.createUserv2(user);
    }), share());
  }

  deleteUserv2(simpleUser: SimpleUser): Observable<APIResult<any>> {
    return this.http.delete(this.endpoints.build(CP_UNISON_API, `/v2/users/${simpleUser.RecordId}`))
      .pipe(map((response: CPResponse<APIResult<any>>) => {
        return response.data;
      }), share());
  }

  getUser(userId: number): Observable<User> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/Users/${userId}`))
      .pipe(map((userResponse: CPResponse<User>) => {
        return userResponse.data;
      }));
  }

  getNewUser(): User {
    return { RecordId: -1 } as User;
  }

  getUserExport(request: ExportUserRequest) {
    return this.http.post(this.endpoints.build(CP_UNISON_API, `/v2/users/export`), request)
      .pipe(map((userResponse: CPResponse<ExportUser[]>) => {
        return userResponse.data;
      }));
  }

  setUserDealershipOn(user: User, dealershipId: number) {
    this.http.post(this.endpoints.build(CP_UNISON_API, '/v1/Users/UserDealership/'), { 'userId': user.RecordId, 'dealershipId': dealershipId }, { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<number>>) => {
        if (response.status > 299) {
          throw new Error('Post failed: ' + response.status);
        }
        return response.body.data;
      }))
      .subscribe(status => {
        const index = this.users.findIndex(i => i.RecordId === user.RecordId);
        this.users[index].UserDealershipId = status;
        this._users.next(this.users);
      }, err => {
        console.log(err);
      });
  }

  setUserDealershipOff(user: User) {
    this.http.delete(this.endpoints.build(CP_UNISON_API, '/v1/Users/UserDealership/' + user.UserDealershipId), { observe: 'response' })
      .pipe(map((response: HttpResponse<null>) => {
        if (response.status > 299) {
          throw new Error('Delete failed: ' + response.status);
        }
      }))
      .subscribe(_ => {
        const index = this.users.findIndex(i => i.RecordId === user.RecordId);
        this.users[index].UserDealershipId = 0;
        this._users.next(this.users);
      }, err => {
        console.log(err);
      });
  }

  // A call to /v1/Users/Profile exists in AuthService as well
  getProfile(): Observable<User> {
    return this.auth.getCPProfile();
  }

  updateProfile(user: User): Observable<null> {
    this.auth.clearCPProfile();
    return this.http.put<null>(this.endpoints.build(CP_UNISON_API, '/v2/Users/Profile'), user)
      .pipe(map(() => {
        const i = this.users.findIndex((x: User) => x.RecordId === user.RecordId);
        this.users[i] = user;
        this.auth.cpProfileSubject.next(_.cloneDeep(user));
        this._users.next(this.users);
        this.auth.getCPProfile().subscribe(); //request fresh profile
        return null;
      }), share());
  }

  updateProfileIfValidEmail(user: User, initialEmail: string): Observable<null> {
    const email1 = user.Email && user.Email.toLowerCase().trim()
    const email2 = initialEmail && initialEmail.toLowerCase().trim();
    if (email1 === email2) {
      return this.updateProfile(user).pipe(share());
    }

    return this.canAddUserEmail(user.Email).pipe(flatMap(x => {
      if (!x) {
        const error = <any>new Error('Email address is already in use');
        error.code = EMAIL_IN_USE;
        throw error;
      }
      return this.updateProfile(user);
    }), share());
  }

  changePassword(userId: number, newPassword: string, isActiveUser?: boolean): Observable<any> {
    let url = '';
    const data: any = {
      NewPassword: newPassword,
      RecordId: userId,
    };

    if (isActiveUser) {
      url = this.endpoints.build(CP_UNISON_API, '/v1/Users/ChangeMyPassword/');
    } else {
      url = this.endpoints.build(CP_UNISON_API, '/v1/Users/ChangeUserPassword/');
    }
    return this.http.put(url, data, { observe: 'response' });
  }

  getUsersByDealershipId(dealershipId: number): Observable<User[]> {
    let url: string;
    if (this.auth.isManager()) {
      url = this.endpoints.build(CP_UNISON_API, `/v2/Users/GetDealershipUsersByManager/${dealershipId}`);
    } else {
      url = this.endpoints.build(CP_UNISON_API, `/v1/Users/GetDealershipUsers/${dealershipId}/${this.orgService.currentOrganization.RecordId}`);
    }
    return this.refreshData(url);
  }

  getUsersByCurrentOrganization(): Observable<User[]> {
    let url: string;
    if (this.auth.isManager()) {
      url = this.endpoints.build(CP_UNISON_API, '/v2/Users/GetUsersByManager');
    } else {
      url = this.endpoints.build(CP_UNISON_API, `/v1/Users/GetByOrg/${this.orgService.currentOrganization.RecordId}`);
    }
    return this.refreshData(url);
  }

  canAddUserEmail(email: String): Observable<boolean> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/Users/ValidateEmail/${email}`))
      .pipe(map((response: CPResponse<CanPerformAction>) => {
        return response.data.CanPerformAction;
      }));
  }

  sendUserVerificationEmail(userId: number): Observable<any> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/Users/VerifyEmail/${userId}`))
      .pipe(map((response: CPResponse<any>) => {
        return response.data;
    }), share());
  }

  updateUserEmail(user: User): Observable<any> {
    this.auth.clearCPProfile(user.RecordId);
    const requestObject = { RecordId: user.RecordId, Email: user.Email };
    return this.http.put(this.endpoints.build(CP_UNISON_API, `/v2/Users/UpdateEmail`), requestObject).pipe(map(x => {
      this.auth.getCPProfile().subscribe(); //request fresh profile
    }), share());
  }
  
  unblockUser(userId: number): Observable<Object> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/Users/UnblockUser/${userId}`));
  }

  updateUserGuide(guideNum: number) {
    return this.http.put(this.endpoints.build(CP_UNISON_API, '/v1/Users/LatestGuideViewed/' + guideNum), {});
  }

  clearUserCache() {
    this.users = [];
  }

  private refreshData(url: string): Observable<User[]> {
    if (this.users.length > 0) {
      return this.usersObservable;
    }
    return this.http.get(url, { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<User[]>>) => {
        if (response.status > 299) {
          throw new Error(`Refresh failed: ${response.status}`);
        }
        this.users = response.body.data;
        this._users.next(this.users);
        return this.users;
      }));
  }

}

interface CanPerformAction {
  CanPerformAction: boolean;
  Reason: String;
}
