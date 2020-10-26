import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap';
import { ToastComponent } from './toast/toast.component';
import { ToastsComponent } from './toasts.component';

@NgModule({
  imports: [
    CommonModule,
    ModalModule,
  ],
  declarations: [
    ToastComponent,
    ToastsComponent,
  ],
  exports: [
    ToastComponent,
    ToastsComponent,
  ],
})
export class ToastModule { }
