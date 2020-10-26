import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ValidatingFieldComponent } from './validating-field.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    ValidatingFieldComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    ValidatingFieldComponent
  ]
})
export class ValidatingFieldModule { }
