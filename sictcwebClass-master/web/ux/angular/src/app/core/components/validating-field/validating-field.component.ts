import { Component, OnInit, Input, forwardRef, OnChanges, Output, EventEmitter, ViewChild } from '@angular/core';
import { FormControl, ControlValueAccessor, NG_VALUE_ACCESSOR, NG_VALIDATORS } from '@angular/forms';
import { ValidationFields } from './validation-fields';
import * as _ from 'lodash';

export function createValidatior(validators?: string) {
  let v;
  if (validators) {
    v = JSON.parse(validators) as ValidationFields;
  }
  return (c: FormControl) => {
    const errs = {} as ValidationFields;
    if (!v) { return errs; }
    if (v.required && (c.value === '' || !c.value)) {
      errs.required = 'Field is required';
    }
    if (v.pattern && c.value) { const regex = new RegExp(v.pattern); if (!regex.test(c.value)) { errs.pattern = 'Invalid input'; } }
    if (v.canPerform && v.canPerform !== '') { errs.canPerform = v.canPerform; }
    // if(v.minLength && c.value) {if(c.value.length < v.minLength) {errs.minLength = `Minimum length of ${v.minLength} not met`}}
    // if(v.maxLength && c.value) {if (c.value.length > v.maxLength) {errs.maxLength = `Maximum length of ${v.maxLength}`}}
    return errs;
  };
}

@Component({
  selector: 'app-validating-field',
  templateUrl: './validating-field.component.html',
  styleUrls: ['./validating-field.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => ValidatingFieldComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => ValidatingFieldComponent),
      multi: true
    }
  ]
})
export class ValidatingFieldComponent implements OnChanges, ControlValueAccessor {

  uniqueId: string = _.uniqueId();

  constructor() {}

  get value(): any {
    return this._value;
  }

  set value(v: any) {
    if (v != this._value) {
      this._value = v;
      this.onChange(v);
    }
  }

  onTouched: () => {};

  @Input() type: string;
  @Input() label: string;
  @Input() formCtrl: FormControl;
  @Input() validators: string;
  @Input() placeholder: string;
  @Input() maxLength: number;
  @Input() disabled: boolean;

  @Output() onValidatedBlur = new EventEmitter();
  @Output() onValidatorFocus = new EventEmitter();

  @ViewChild('validatingField', { static: false }) inputElem;

  private _value: any;

  private _errors;
  public fieldError = false;
  private errorMessage: string;

  onChange: any = () => {};
  validateFn: any = () => {};

  ngOnInit() {}

  ngOnChanges(inputs) {
    if (inputs.validators) {
      this.validateFn = createValidatior(this.validators);
      this.onChange(this.value);
      if (this.formCtrl) { this.fieldChanged(); }
    }
  }

  fieldChanged() {
    if (!this.formCtrl.errors) {
      this.fieldError = false;
      return;
    }
    this.fieldError = true;
    this.errorMessage = this.formCtrl.errors[Object.keys(this.formCtrl.errors)[0]];
  }

  onBlur() {
    this.onValidatedBlur.emit();
    if (this.formCtrl) {
      this.fieldChanged();
    }
    this.onTouched();
  }

  onFocus() {
    this.onValidatorFocus.emit();
  }

  writeValue(value: any) {
    if (value != this._value) {
      this.value = value;
    }
  }

  registerOnChange(fn: any) {
    this.onChange = fn;
  }

  registerOnTouched(fn: any) {
    this.onTouched = fn;
  }
  validate(c: FormControl) {
    return this.validateFn(c);
  }

  focus() {
      this.inputElem.nativeElement.focus();
  }
}
