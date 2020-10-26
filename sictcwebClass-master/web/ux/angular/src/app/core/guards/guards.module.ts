import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthGuard } from './auth.guard';
// import { CanDeactivateGuard } from './can-deactivate/can-deactivate.guard';
import { OrganizationGuard } from './organization.guard';
import { EmailVerifiedGuard } from './email-verified.guard';

@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule
  ],
  providers: [
    AuthGuard,
    // CanDeactivateGuard,
    OrganizationGuard,
    EmailVerifiedGuard
  ]
})
export class GuardsModule { }
