import { BrowserInfoService } from '../services/browser-info.service';
import { Injectable } from '@angular/core';
import {
  Router, Route,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { CanActivate, CanActivateChild } from '@angular/router';

import { AuthService } from '../services/auth.service';
import { OrganizationsService } from '../services/organizations.service';
import { Observable } from 'rxjs';

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild {

  constructor(private auth: AuthService,
              private router: Router,
              private org: OrganizationsService,
              private browserInfo: BrowserInfoService) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.checkLogin(next, state);
  }

  canActivateChild(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.checkLogin(next, state);
  }

  private checkLogin(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    
    if (!this.auth.isAuthenticated()) {
      this.routeToLogin();
      return false;
    }

    // Now check if there is a role guard
    let data = next.data;
    /*if (!data && next.firstChild) { //Bug-fix, also check children for "data", leaving this commented for now, may fix as future enhancement
      data = next.firstChild.data;
    }*/

    if (data && data['roles']) {
      const authRoles = new Set(this.auth.roles);
      const routeRoles = data['roles'];
      const intersection = new Set(
        routeRoles.filter(x => authRoles.has(x))
      );

      const passFulfiller = data.allowFulfiller && this.org.isCurrentFulfiller();
      if (intersection.size === 0 && !passFulfiller) {
        return this.routeToLogin();
      }
    }

    // Standard url, let it pass
    return true;
  }

  private routeToLogin(): boolean {
    this.router.navigate(['/login']);
    return false;
  }

  /*private routeToOrganizations(): boolean {
    this.router.navigate(['/organization/select']);
    return false;
  }*/

}
