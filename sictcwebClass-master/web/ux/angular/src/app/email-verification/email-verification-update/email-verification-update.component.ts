import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { User } from '../../core/interfaces/User';
import { VERIFICATION_STATE } from '../email-verification.component';
import { NgForm } from '@angular/forms';
import { ButtonSettingsComponent } from '../../core/components/button-settings/button-settings.component';
import { UsersService } from '../../core/services/users.service';
import { Toast, ToastStyle, ToastTimeout } from '../../core/components/toasts/toast/toast';
import { ToastService } from '../../core/components/toasts/toast.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-email-verification-update',
  templateUrl: './email-verification-update.component.html',
  styleUrls: ['./email-verification-update.component.scss']
})
export class EmailVerificationUpdateComponent implements OnInit {

  VERIFICATION_STATE = VERIFICATION_STATE;

  @ViewChild('nextButton', { static: false }) nextButton: ButtonSettingsComponent;

  @Input() user: User;
  @Output() stateChanged = new EventEmitter<VERIFICATION_STATE>();

  emailInput: string;
  emailConfirmInput: string;



  constructor(private usersService: UsersService,
              private toastService: ToastService) { }

  ngOnInit() { }

  onSubmit(form: NgForm) {
    if (this.emailInput === this.emailConfirmInput) {

      this.user.Email = this.emailConfirmInput;

      const obs = this.usersService.updateUserEmail(this.user);
      this.nextButton.watchObservable(obs);

      obs.subscribe(_ => {
        this.stateChanged.emit(VERIFICATION_STATE.COMPLETE);
      }, (error: HttpErrorResponse) => {
        const errorToast = new Toast({
          title: 'Something went Wrong',
          body: error.error,
          style: ToastStyle.ERROR,
          timeout: ToastTimeout.LONG
        });
        this.toastService.show(errorToast);
      });
    }
  }
}
