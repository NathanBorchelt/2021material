import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SelectComponent } from './select.component';
import { BsDropdownModule } from 'ngx-bootstrap';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [SelectComponent],
  imports: [
    CommonModule,
    BsDropdownModule,
    FormsModule
  ],
  exports: [SelectComponent]
})
export class SelectModule { }
