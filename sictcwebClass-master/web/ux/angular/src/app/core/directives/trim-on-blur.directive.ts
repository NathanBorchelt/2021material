import { Directive, Self, HostListener } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[appTrimOnBlur]'
})
export class TrimOnBlurDirective {

  constructor(@Self() private ngControl: NgControl) { }

  @HostListener('blur') onBlur() {
    const whitespace: string = ' ';
    const value: string = this.ngControl.value.toString();    
    if (value.startsWith(whitespace) || value.endsWith(whitespace)) {
      this.ngControl.control.patchValue(value.trim());
    }
  }
}
