import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ColorSwatchPickerComponent } from './color-swatch-picker.component';
import { BsDropdownModule } from 'ngx-bootstrap';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [ColorSwatchPickerComponent],
  exports: [
    ColorSwatchPickerComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    BsDropdownModule.forRoot()
  ]
})
export class ColorSwatchPickerModule { }
