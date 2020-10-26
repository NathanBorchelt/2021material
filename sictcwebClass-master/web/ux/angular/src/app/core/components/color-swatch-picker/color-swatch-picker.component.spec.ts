import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ColorSwatchPickerComponent } from './color-swatch-picker.component';

describe('ColorSwatchPickerComponent', () => {
  let component: ColorSwatchPickerComponent;
  let fixture: ComponentFixture<ColorSwatchPickerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ColorSwatchPickerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ColorSwatchPickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
