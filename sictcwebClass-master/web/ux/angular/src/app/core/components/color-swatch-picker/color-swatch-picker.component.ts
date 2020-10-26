import { Component, OnInit, Input, forwardRef, HostBinding, ViewChild, OnChanges, SimpleChanges } from '@angular/core';
import { NG_VALUE_ACCESSOR, ControlValueAccessor } from '@angular/forms';
import { BsDropdownDirective } from 'ngx-bootstrap';
import { ColorService } from '../../services/color.service';

@Component({
  selector: 'app-color-swatch-picker',
  templateUrl: './color-swatch-picker.component.html',
  styleUrls: ['./color-swatch-picker.component.scss'],
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => ColorSwatchPickerComponent),
    multi: true
}]
})
export class ColorSwatchPickerComponent implements OnChanges, OnInit, ControlValueAccessor {
  
  @ViewChild('dropdown', { static: false }) dropdown: BsDropdownDirective;
  @Input() swatches: ColorSwatch[] = [];
  @Input() label: string = 'Select a color';
  @Input() disabled: boolean = false;
  
  value: ColorSwatch;

  constructor(private colorService: ColorService) { }

  ngOnChanges(changes: SimpleChanges) {
    if (!this.swatches || !this.swatches.length) {
      this.swatches = this.colorService.getStandardColors().map(x => <ColorSwatch>{ color: x.Color, hex: x.Hex });
    }
    
    this.normalizeInputs();
    this.value = this.value && this.findSwatch(this.value.color);
  }

  ngOnInit() { }

  onSelect(swatch: ColorSwatch) {
    if (!this.disabled && swatch !== this.value) {
      this.value = swatch;
      this.propagateChange(swatch.color);
    }
    this.propagateTouched();
    this.dropdown.hide();
  }

  isSelected(swatch: ColorSwatch) {
    return this.value && this.compareColors(this.value.color, swatch.color);
  }

  findSwatch(color: string) {
    return this.swatches.find(x => this.compareColors(x.color, color));
  }

  compareColors(a: string, b: string) {
    const result = this.getColorKey(a) === this.getColorKey(b);
    return result;
  }

  getColorKey(color: string): string {
    color = color != null ? color.trim().toLowerCase() : '';
    return color;
  }

  normalizeInputs() {
    for (const swatch of this.swatches) {
      swatch.color = swatch.color !== undefined ? swatch.color : swatch['Color'];
      swatch.hex = swatch.hex !== undefined ? swatch.hex : swatch['Hex'];
    }
  }
  
  //////////////////////////
  // ControlValueAccessor
  //////////////////////////

  propagateChange = (color: string) => {};
  propagateTouched = () => {};

  writeValue(color: string): void {
    this.value = this.findSwatch(color);
  }

  registerOnChange(fn: any): void {
    this.propagateChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.propagateTouched = fn;
  }

  setDisabledState?(isDisabled: boolean): void {
    this.disabled = isDisabled;
  }
}


export interface ColorSwatch {
  color: string;
  hex: string;
}