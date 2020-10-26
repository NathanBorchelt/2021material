import { AfterContentInit, Directive, Optional, Self } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[appFormFieldInput]',
})
export class FormFieldInputDirective implements AfterContentInit {


  constructor(public control: NgControl) {
  }

  ngAfterContentInit() {
  }
}
