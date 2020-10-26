import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { PasswordResetComponent } from './password-reset/password-reset.component';
import { FormsModule } from '@angular/forms';
import { ButtonSettingsModule } from '../core/components/button-settings/button-settings.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    LoginComponent,
    PasswordResetComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ButtonSettingsModule,
    RouterModule
  ]
})
export class AuthModule { }
