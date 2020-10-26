import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guard';
import { PasswordResetComponent } from './auth/password-reset/password-reset.component';
import { LoginComponent } from './auth/login/login.component';
import { EmailVerificationComponent } from './email-verification/email-verification.component';
import { EmailVerifiedGuard } from './core/guards/email-verified.guard';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'password-reset',
    component: PasswordResetComponent
  },
  // {
  //   path: 'organization',
  //   loadChildren: './organization/organization.module#OrganizationModule',
  //   canActivate: [AuthGuard, EmailVerifiedGuard],
  // },
  {
    path: 'email-verification',
    component: EmailVerificationComponent,
    canActivate: [AuthGuard],
  },
  // {
  //   path: '',
  //   redirectTo: 'inventory',
  //   pathMatch: 'full',
  // },
  // {
  //   path: 'inventory',
  //   loadChildren: './inventory/inventory.module#InventoryModule',
  // },
  // {
  //   path: 'maint',
  //   loadChildren: './maintenance/maintenance.module#MaintenanceModule',
  // },
  // {
  //   path: 'support',
  //   loadChildren: './support/support.module#SupportModule',
  // },
  // {
  //   path: 'store',
  //   loadChildren: './store/store.module#StoreModule'
  // },
  // { path: '**',
  //   redirectTo: 'inventory',
  //   pathMatch: 'full'
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
