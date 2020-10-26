import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MultiSelectComponent } from './multi-select.component';
import { BsDropdownModule } from 'ngx-bootstrap';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [MultiSelectComponent],
  exports: [
    MultiSelectComponent
  ],
  imports: [
    CommonModule,
    BsDropdownModule,
    FormsModule
  ]
})
export class MultiSelectModule { }
