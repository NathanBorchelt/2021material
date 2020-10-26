import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[appIsFocused]',
  exportAs: 'isFocused'
})
export class IsFocusedDirective {
  public focused: boolean = false;

  constructor() { }

  @HostListener('focus') focus() {
    this.focused = true;
  }

  @HostListener('blur') blur() {
    this.focused = false;    
  }
}
