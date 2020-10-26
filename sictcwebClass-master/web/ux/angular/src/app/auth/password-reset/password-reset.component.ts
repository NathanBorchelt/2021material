import { Component, OnInit, ViewChild, Renderer2, AfterViewInit } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';
import { Location } from '@angular/common';
import { TopbarStateManager, TopbarConfig } from '../../topbar/topbar.component';
import { EMAIL_VALID_REGEX } from 'src/app/core/services/util.service';

@Component({
  selector: 'app-password-reset',
  templateUrl: './password-reset.component.html',
  styleUrls: ['./password-reset.component.scss']
})
export class PasswordResetComponent implements OnInit, AfterViewInit, TopbarStateManager {

  @ViewChild('emailInput', { static: false }) userInput;
  email: string;
  validEmail = true;
  submitted = false;

  constructor(public authService: AuthService, private rend: Renderer2, private _location: Location) { }

  ngOnInit() {
    this.submitted = false;
  }

  ngAfterViewInit() {
    this.rend.selectRootElement(this.userInput.nativeElement).focus();
  }

  setTopbarState(currentConfig: TopbarConfig) {
    currentConfig.showTopbar = false;
  }

  cancel() {
    this._location.back();
  }

  validateEmail() {
    const validEmailRegEx = EMAIL_VALID_REGEX; // /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (validEmailRegEx.test(this.email)) {
      this.validEmail = true;
    } else {
      this.validEmail = false;
    }
  }

  submit() {
    // this.authService.resetSuccessMessage = '';
    // this.authService.resetErrorMessage = '';
    this.validateEmail();

    if (this.validEmail) {
      this.authService.resetPassword(this.email);
      this.submitted = true;
    }
  }

}
