import { AfterViewInit, Directive, ElementRef, Renderer2, Input } from '@angular/core';

@Directive({
  selector: 'select[ngModel][appSelectOnlyOption]',
})
export class SelectOnlyOptionDirective implements AfterViewInit {
  @Input('ngModel') set ngModel(value: any) { this.trySelect(); }
  @Input() offset: number = 0;

  private observer: MutationObserver;
  
  constructor(
      private elementRef: ElementRef,
      private renderer: Renderer2) {
  }

  ngAfterViewInit() {
    this.observer = new MutationObserver(mutations => this.trySelect());
    this.observer.observe(this.elementRef.nativeElement, { childList: true });
    this.trySelect();
  }

  private trySelect() {
    setTimeout(() => {
      const el = this.elementRef.nativeElement;
      if (el.options.length === this.offset + 1 && el.selectedIndex !== this.offset) {
        //el.selectedIndex = this.offset;
        this.renderer.setProperty(el, 'value', el.options[this.offset].value);
        el.dispatchEvent(new Event('change'));
      }
    }, 0);
  }
}