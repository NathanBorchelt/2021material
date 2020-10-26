import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';


@Injectable()
export class EmailVerifiedGuard implements CanActivate, CanActivateChild {

  constructor(private router: Router, private authService: AuthService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.checkEmailVerified();
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.checkEmailVerified();
  }

  checkEmailVerified(): Observable<any> {
    return this.authService.userProfile$.map((profile: any) => {
      if (!profile.email_verified) {
        this.router.navigate(['email-verification']);
        return false;
      }
      return true;
    });
  }
}
