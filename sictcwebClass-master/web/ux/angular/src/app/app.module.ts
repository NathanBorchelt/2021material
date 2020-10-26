import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { CoreModule } from './core/core.module';
// import { SharedModule } from './shared/shared.module';
// import { InventoryModule } from './inventory/inventory.module';
// import { MaintenanceModule } from './maintenance/maintenance.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Daterangepicker } from 'ng2-daterangepicker';
import { BsDropdownModule } from 'ngx-bootstrap';
// import { IntegrationModule } from './maintenance/integration/integration.module';
import { ToastModule } from './core/components/toasts/toast.module';
import { TopbarComponent } from './topbar/topbar.component';
import { GuardsModule } from './core/guards/guards.module';
import { NgxFreshChatModule } from 'ngx-freshchat';
import { AuthModule } from './auth/auth.module';
import { EmailVerificationModule } from './email-verification/email-verification.module';
// import { NewOrganizationV2Module } from './organization/new-organization-v2/new-organization-v2.module';
import { ModalModule, BsModalRef } from 'ngx-bootstrap/modal';
import { Observable as ObservableCompat } from 'rxjs/Rx';
import { DragDropModule } from '@angular/cdk/drag-drop';

@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
  ],
  imports: [
    ModalModule.forRoot(),
    CoreModule,
    // SharedModule,
    BrowserModule,
    BrowserAnimationsModule,
    // InventoryModule,
    // MaintenanceModule,
    FormsModule,
    // NewOrganizationV2Module,
    AppRoutingModule,
    Daterangepicker,
    BsDropdownModule,
    // IntegrationModule,
    ToastModule,
    GuardsModule,
    NgxFreshChatModule,
    AuthModule,
    EmailVerificationModule,
    ReactiveFormsModule,
    DragDropModule,
  ],
  bootstrap: [AppComponent],
  providers: [BsModalRef]
})
export class AppModule {}


//ObservableCompat placeholder below stops WebPack from removing rxjs-compat by tree-shaking
//Long term we should migrate all legacy RxJs 5 code to RxJs 6 and npm uninstall rxjs-compat
ObservableCompat;
