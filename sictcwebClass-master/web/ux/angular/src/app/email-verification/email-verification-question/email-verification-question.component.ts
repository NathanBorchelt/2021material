import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { User } from '../../core/interfaces/User';
import { VERIFICATION_STATE } from '../email-verification.component';
import { UsersService } from '../../core/services/users.service';
import { ToastService } from '../../core/components/toasts/toast.service';
import { Toast, ToastStyle, ToastTimeout } from '../../core/components/toasts/toast/toast';
import { ButtonSettingsComponent } from '../../core/components/button-settings/button-settings.component';

@Component({
  selector: 'app-email-verification-question',
  templateUrl: './email-verification-question.component.html',
  styleUrls: ['./email-verification-question.component.scss']
})
export class EmailVerificationQuestionComponent implements OnInit {

  @ViewChild('yesButton', { static: false }) yesButton: ButtonSettingsComponent;

  VERIFICATION_STATE = VERIFICATION_STATE;

  @Input() user: User;
  @Output() stateChanged: EventEmitter<VERIFICATION_STATE> = new EventEmitter();

  errorToast = new Toast({
    title: 'Something went Wrong',
    body: 'An error occurred while attempting to send a verification email. Try again in a bit.',
    style: ToastStyle.ERROR,
    timeout: ToastTimeout.LONG
  });

  constructor(private usersService: UsersService,
              private toastService: ToastService) { }

  ngOnInit() { }

  sendVerificationEmailAndContinue() {

    const obs = this.usersService.sendUserVerificationEmail(this.user.RecordId);
    this.yesButton.watchObservable(obs);

    obs.subscribe(_ => {
      this.stateChanged.emit(VERIFICATION_STATE.COMPLETE);
    }, (error: any) => {
      this.toastService.show(this.errorToast);
    });
  }
}
