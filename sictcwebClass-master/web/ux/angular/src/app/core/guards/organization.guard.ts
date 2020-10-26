import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot } from '@angular/router';
import { OrganizationsService } from '../services/organizations.service';
import { Organization } from '../interfaces/Organization';
import { Observable } from 'rxjs';
import { of } from 'rxjs/internal/observable/of';


@Injectable()
export class OrganizationGuard implements CanActivate, CanActivateChild {

  constructor(private organizationsService: OrganizationsService,
              private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.checkOrganizations(state.url);
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.checkOrganizations(state.url);
  }

  checkOrganizations(path: string): Observable<boolean> {
    if (this.organizationsService.currentOrganization.RecordId !== -1) {
      
      if (path === '/' || path === '/inventory') {
      const isFulfiller = this.organizationsService.isCurrentFulfiller();
        if (!isFulfiller) {
          //Dealership org
          return of(true);

        } else {
          // Fulfiller org
          console.log('navigating to orders', path);
          this.router.navigate(['/maint/orders']);
          return of(false);
        }
      } else {

        return of(true);
      }
    }

    // return this.organizationsService.getOrganizations().map((organizations: Organization[]) => {

    //   if (organizations.length === 1) {
    //     this.organizationsService.currentOrganization = organizations[0];
    //     const isFulfiller = this.organizationsService.isCurrentFulfiller();

    //     if (!isFulfiller) {
    //       // Dealership org
    //       return true;

    //     } else {
    //       // Fulfiller org
    //       this.router.navigate(['/maint/orders']);
    //       return false;
    //     }

    //   } else {
    //     // Multiple orgs
    //     this.router.navigate(['/organization/select']);
    //     return false;
    //   }
    // });
  }
}
