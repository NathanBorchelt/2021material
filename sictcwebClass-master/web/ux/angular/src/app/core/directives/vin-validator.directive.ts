import { Directive, forwardRef, Input } from '@angular/core';
import { NG_VALIDATORS, FormControl, Validator, AbstractControl, ValidationErrors } from '@angular/forms';
import { UtilService } from '../services/util.service';

@Directive({
  selector: '[appVinValidator]', //'[appVinValidator][ngModel], [appVinValidator][formControlName], [appVinValidator][formControl]',
  providers: [
    { provide: NG_VALIDATORS, useExisting: VinValidatorDirective, multi: true }
  ]
})
export class VinValidatorDirective implements Validator {

  constructor(private utilService: UtilService) {}

  validate(control: AbstractControl): ValidationErrors {
    const value = control.value;
    if (value && !this.utilService.isValidVin(value)) {
      return {
        vinInvalid: true
      };
    }
    return null;
  }
  
  registerOnValidatorChange?(fn: () => void): void {}
}