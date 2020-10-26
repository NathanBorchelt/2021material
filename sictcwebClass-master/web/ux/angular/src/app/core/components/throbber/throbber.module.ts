import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThrobberComponent } from './throbber.component';

@NgModule({
  declarations: [ThrobberComponent],
  imports: [
    CommonModule
  ],
  exports: [
    ThrobberComponent
  ]
})
export class ThrobberModule { }
