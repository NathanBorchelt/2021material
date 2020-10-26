import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { EmailVerificationComponent } from './email-verification.component';
import { EmailVerificationQuestionComponent } from './email-verification-question/email-verification-question.component';
import { EmailVerificationUpdateComponent } from './email-verification-update/email-verification-update.component';
import { EmailVerificationCompleteComponent } from './email-verification-complete/email-verification-complete.component';
import { RouterModule } from '@angular/router';
import { ThrobberModule } from '../core/components/throbber/throbber.module';
import { ButtonSettingsModule } from '../core/components/button-settings/button-settings.module';

@NgModule({
  declarations: [
    EmailVerificationComponent,
    EmailVerificationQuestionComponent,
    EmailVerificationUpdateComponent,
    EmailVerificationCompleteComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ThrobberModule,
    ButtonSettingsModule
  ]
})
export class EmailVerificationModule { }
