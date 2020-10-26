import { Component, OnInit, ViewChild, Renderer2, AfterViewInit } from '@angular/core';
import { AuthService, LoginError } from '../../core/services/auth.service';
import { OrganizationsService } from '../../core/services/organizations.service';
// import { FilterService } from '../../inventory/filter.service';
import { ButtonSettingsComponent } from '../../core/components/button-settings/button-settings.component';
import { TopbarStateManager, TopbarConfig } from '../../topbar/topbar.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, AfterViewInit, TopbarStateManager {

  @ViewChild('submitButton', { static: false }) submitButton: ButtonSettingsComponent;
  @ViewChild('usernameInput', { static: false }) usernameInputRef;
  usernameValue: string;
  passwordValue: string;
  error: LoginError;
  isFirstError = true;
  isShowingErrorAnimation = false;

  constructor(public auth: AuthService,
              private rend: Renderer2,
              private organizationsService: OrganizationsService,
              // private filterService: FilterService
              ) { }

  ngOnInit() {
    // this.auth.logout();
    this.organizationsService.clearCurrentOrganization();
  }

  ngAfterViewInit() {
    this.rend.selectRootElement(this.usernameInputRef.nativeElement).focus();
  }

  setTopbarState(config: TopbarConfig) {
    config.showTopbar = false;
  }

  onSubmit() {

    // if (!this.usernameValue || !this.passwordValue) {
    //   this.error = {
    //     hasError: true,
    //     errorMessage: 'Please enter your username and password.'
    //   };
    //   this.showErrorAnimation();
    //   return;
    // }

    // const observable = this.auth.login(this.usernameValue, this.passwordValue);
    // this.submitButton.watchObservable(observable);

    // observable.subscribe((loginError: LoginError) => {
    //   this.error = loginError;
    //   this.showErrorAnimation();
    // });
  }

  showErrorAnimation() {
    if (this.isFirstError) {
      this.isFirstError = false;
      return;
    }
    this.isShowingErrorAnimation = true;
    setTimeout(() => {
      this.isShowingErrorAnimation = false;
    }, 1000);
  }
}
