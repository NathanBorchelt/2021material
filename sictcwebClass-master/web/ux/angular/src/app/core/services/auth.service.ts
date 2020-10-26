import { Injectable } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject, throwError, of, forkJoin } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from '../../../environments/environment';
import { Organization } from '../interfaces/Organization';
import { BrowserInfoService } from './browser-info.service';
import { OrganizationsService } from './organizations.service';
import { User } from '../interfaces/User';
import { CP_UNISON_API, EndpointsService } from './endpoints.service';
import { map, share } from 'rxjs/operators';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { CPResponse } from '../interfaces/CPResponse';
import { Auth0UserProfile } from 'auth0-js';
import * as _ from 'lodash';
import { VehicleService } from './vehicle.service';
import { CookieService } from 'ngx-cookie-service';

// Avoid name not found warnings
declare var auth0: any;
const TOKEN_KEY = 'id_token';

@Injectable({providedIn: 'root'})
export class AuthService {

  userProfile: Object = null;
  userName = '';
  roles: string[] = [];
  userProfile$: BehaviorSubject<Object> = new BehaviorSubject<Object>(null);
  loginErrorSubject: Subject<LoginError> = new Subject();
  delegationToken = '';
  organizations: Organization[] = [];
  webAuth0: any;
  private cpProfile: User;
  showUpcomingChangesPrompt = false;
  // for the top bar
  cpProfileSubject: BehaviorSubject<User> = new BehaviorSubject<User>(null);
  cpProfile$ = this.cpProfileSubject.asObservable();

  constructor(private orgService: OrganizationsService,
              private location: Location,
              private router: Router,
              private browserInfo: BrowserInfoService,
              private jwtHelperService: JwtHelperService,
              private http: HttpClient,
              private endpoints: EndpointsService,
              private vehicleService: VehicleService,
              private cookieService: CookieService) {

    let cUrl = this.location.prepareExternalUrl(environment.auth0_redirectUrl);
    cUrl = `${window.location.protocol}//${window.location.host}${cUrl}`;

    // Configure Auth0
    this.webAuth0 = new auth0.Authentication({
      domain: environment.auth0_domain,
      clientID: environment.auth0_client_id,
      responseType: 'token id_token'
    });

    this.init();
  }

  init() {
    // Grab the cached profile if we have one.
    // This also checks if we're authenticated; if we are not
    // bail early because we should land at the login screen.
    this.restoreProfile();
  }

  login(username: string, password: string): Observable<LoginError> {
    
    const loginError: LoginError = {
      hasError: true,
      errorMessage: 'Something went wrong. Please try again. If the problem persists, please contact CP Handheld Support 954-812-6921 support@cphandheld.com'
    };

    this.webAuth0.loginWithResourceOwner({
      connection: 'Username-Password-Authentication',
      username: username,
      password: password,
      scope: 'openid roles uid'
    }, (auth0Error, result) => {

      // Handle error
      if (auth0Error) {
        loginError.errorMessage = this.parseAuth0Error(auth0Error);
        this.loginErrorSubject.next(loginError);
        return;
      }


      // grab the roles
      this.extractRoles(result.idToken);

      // Store the token - we're authenticated!
      localStorage.setItem(TOKEN_KEY, result.idToken);

      // While that's navigating, fetch the user profile
      this.webAuth0.userInfo(result.accessToken, (auth0Error, profile: Auth0UserProfile) => {

        // Handle error
        if (auth0Error) {
          loginError.errorMessage = this.parseAuth0Error(auth0Error);
          this.loginErrorSubject.next(loginError);
          return;
        }

        const profileObject = JSON.stringify(profile);
        localStorage.setItem('profile', profileObject);
        this.saveProfile(profile);

        // Perform this check to see if the user is actually in the unison database
        const getCPProfile = this.getCPProfile();
        const getOrganizations = this.orgService.getOrganizations();
        const getStandardMakes = this.vehicleService.getStandardMakes();
        const getStandardModels = this.vehicleService.getStandardModels();
        const joined = forkJoin(getCPProfile, getOrganizations, getStandardMakes, getStandardModels);

        joined.subscribe(results => {
          const organizations = results[1];

          const previewUpcomingChanges = (this.cookieService.get(`previewUpcomingChanges-${profile.user_id}`) === 'true');

          if (!previewUpcomingChanges) {
            this.showUpcomingChangesPrompt = true;
          }

          // Check if no orgs/dealerships
          if (organizations.length === 0) {
            this.logout();
            loginError.errorMessage = 'No Dealerships are assigned to your user. Contact your manager for access.';
            this.loginErrorSubject.next(loginError);
            return;
          }

          // Check if email verified
          if (!profile.email_verified) {
            this.router.navigate(['/email-verification']);
            return;
          }

          // Success: route to inventory page
          this.router.navigate(['/inventory']);

        }, error => {
          // Handle error
          this.logout();
          const isProfileError = error && error.url && (<string>error.url).endsWith('/Profile');
          if (isProfileError) { //Did error originate from profile API?
            loginError.errorMessage = 'User was successfully authenticated, but wasn\'t found in the database. Remove and re-add this user.'
          }
          this.loginErrorSubject.next(loginError);
        });
      });

      // Grab delegationToken to authenticate with IntegrationAPI
      const delegationOptions = {
        client_id: environment.auth0_client_id,
        id_token: result.idToken,
        target: environment.auth0_integration_client_id,
        grant_type: environment.auth0_delegation_grant_type,
        scope: 'openid roles'
      };

      this.webAuth0.delegation(delegationOptions, (_, delegationResult) => {
        this.delegationToken = delegationResult.idToken;
        localStorage.setItem('delegation_token', delegationResult.idToken);
      });
    });
    return this.loginErrorSubject;
  }

  parseAuth0Error(error: any): string {
    switch (error.code) {
      case 'invalid_user_password':
        return 'Your username or password are incorrect. Please try again.';
      case 'too_many_attempts':
        return 'Your account has been blocked after multiple consecutive login attempts. We\'ve sent you an email with instructions on how to unblock it.';
      default:
        console.error(error);
        return 'Something went wrong. Please try again. If the problem persists, please contact CP Handheld Support 954-812-6921 support@cphandheld.com';
    }
  }

  isAuthenticated(routeToLogin = false): boolean {
    // Check if there's an unexpired JWT
    // This searches for an item in localStorage with the Key == 'id_token'
    // It also ensure that the token is valid (not hacked via browser dev tools)
    const signedIn = !!localStorage.getItem('id_token');

    let result = false;
    try {
      result = !this.jwtHelperService.isTokenExpired();
    } catch (error) {
      result = false;
    }

    if (signedIn && !result) {
      this.logout();
      if (routeToLogin) {
        this.router.navigate(['/login']);
      }
    }

    return result;
  }

  logout() {
    // Clear organizations
    this.orgService.clearCurrentOrganization();
    // Remove token from localStorage
    const filters = localStorage.getItem('filters');
    localStorage.clear();
    localStorage.setItem('filters', filters);
    this.userName = '';
    this.delegationToken = '';
    this.userProfile = null;
    this.userProfile$.next(this.userProfile);
    this.cpProfile = null;
    this.cpProfileSubject.next(this.cpProfile);
  }


  resetPassword(email: string): Observable<any> {
    const obs = new Subject();
    this.webAuth0.dbConnection.changePassword({
      connection: 'Username-Password-Authentication',
      email: email
    }, (err, resp) => {
      if (err) {
        obs.next(throwError(err));
      } else {
        obs.next(resp);
      }
    });
    return obs;
  }

  isPrinterAdmin() {
    return this.roles.includes('PrinterAdmin');
  }

  isAdmin() {
    return this.roles.includes('Admin');
  }

  isOrgAdmin() {
    return this.roles.includes('OrgAdmin');
  }

  isManager() {
    if (this.roles.length === 1) {
      return this.roles.includes('Manager');
    } else {
      return false;
    }
  }

  isBilling() {
    return this.roles.includes('Billing');
  }

  isReadOnly(): boolean {
    return this.roles.includes('ReadOnly');
  }

  isAdminOrOrgAdmin() {
    return ((this.isAdmin()) || (this.isOrgAdmin()));
  }

  private restoreProfile() {
    let isValid = false;

    if (!this.isAuthenticated()) {
      return;
    }
    if (this.extractRoles(localStorage.getItem('id_token'))) {
      this.userProfile = JSON.parse(localStorage.getItem('profile'));
      this.userName = JSON.parse(localStorage.getItem('auth_userName'));
      this.delegationToken = localStorage.getItem('delegation_token');
      this.userProfile$.next(this.userProfile);
      this.cpProfile = JSON.parse(localStorage.getItem('cp_profile'));
      this.cpProfileSubject.next(_.cloneDeep(this.cpProfile));
      isValid = true;
    }

    if (!isValid) {
      this.router.navigate(['/login']);
    }
  }

  // We can't use the GetProfile call from UsersService, since that class already depends on AuthService
  // (it would cause a circular dependency)
  getCPProfile(): Observable<User> {
    if (this.cpProfile) {
      this.cpProfileSubject.next(_.cloneDeep(this.cpProfile));
      return of(this.cpProfile);
    }

    return this.http.get(this.endpoints.build(CP_UNISON_API, '/v2/Users/Profile'), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<User>>) => {
        if (response.status > 299) {
          throw new Error('Get user profile failed: ' + response.status);
        }
        localStorage.setItem('cp_profile', JSON.stringify(this.cpProfile));
        this.cpProfile = response.body.data;
        this.cpProfileSubject.next(_.cloneDeep(this.cpProfile));
        return this.cpProfile;
      }), share());
  }

  clearCPProfile(userId:number = null) {
    if (!this.cpProfile) {
      return;
    }

    //Is this the logged in user?
    if (userId == null || userId === this.cpProfile.RecordId) {
      localStorage.removeItem('cp_profile');
      this.cpProfile = null;
    }
  }

  private saveProfile(profile: any) {
    this.userName = profile.nickname;
    this.roles = profile.app_metadata.roles;
    localStorage.setItem('auth_userName', JSON.stringify(profile.nickname));
    this.userProfile = profile;
    this.userProfile$.next(this.userProfile);
  }

  private extractRoles(token: string): boolean {
    this.roles = this.jwtHelperService.decodeToken(token).roles;
    if (this.roles) {
      return true;
    }
    return false;
  }
}

export interface LoginError {
  hasError: boolean;
  errorMessage: string;
}
