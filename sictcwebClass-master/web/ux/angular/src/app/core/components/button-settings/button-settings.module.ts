import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonSettingsComponent } from './button-settings.component';
import { ThrobberModule } from '../throbber/throbber.module';
import { ToastModule } from '../toasts/toast.module';

@NgModule({
  declarations: [ButtonSettingsComponent],
  imports: [
    CommonModule,
    ThrobberModule,
    ToastModule
  ],
  exports: [ButtonSettingsComponent]
})
export class ButtonSettingsModule { }
