import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SelectOnlyOptionDirective } from './select-only-option/select-only-option.directive';
import { FormFieldDirective } from './form-field/form-field.directive';
import { FormFieldInputDirective } from './form-field/form-field-input/form-field-input.directive';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VinValidatorDirective } from './vin-validator.directive';
import { TrimOnBlurDirective } from './trim-on-blur.directive';
import { IsFocusedDirective } from './is-focused.directive';
import { FormFieldErrorDirective } from './form-field/form-field-error/form-field-error.directive';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    SelectOnlyOptionDirective,
    FormFieldErrorDirective,
    FormFieldDirective,
    FormFieldInputDirective,
    IsFocusedDirective,
    TrimOnBlurDirective,
    VinValidatorDirective
  ],
  exports: [
    SelectOnlyOptionDirective,
    FormFieldDirective,
    FormFieldInputDirective
  ],

})
export class DirectivesModule { }