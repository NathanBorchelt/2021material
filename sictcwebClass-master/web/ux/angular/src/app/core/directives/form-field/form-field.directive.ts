import { AfterContentInit, ContentChild, Directive, ElementRef, Renderer2} from '@angular/core';
import { FormFieldInputDirective } from './form-field-input/form-field-input.directive';


@Directive({
  selector: '[appFormField]',
})
export class FormFieldDirective implements AfterContentInit {


  constructor(private renderer: Renderer2, private hostElement: ElementRef) {}

  @ContentChild(FormFieldInputDirective, {static: false}) formFieldInput: FormFieldInputDirective;

  ngAfterContentInit() {
    if (!this.formFieldInput) {
      console.error('No FormFieldInputDirective was found as a child of a FormFieldDirective, you might need to add an input.');
    }
    this.formFieldInput.control.statusChanges.subscribe((statusChanges: any) => {
      if (this.formFieldInput.control.dirty) {
        this.checkInputStatus(statusChanges);
      }
    });
  }

  checkInputStatus(inputStatus: string) {
    switch (inputStatus) {
      case 'VALID':
        this.renderer.removeClass(this.hostElement.nativeElement, 'error');
        break;
      case 'INVALID':
        this.renderer.addClass(this.hostElement.nativeElement, 'error');
        break;
      case 'DISABLED':
        break;
    }
  }
}
