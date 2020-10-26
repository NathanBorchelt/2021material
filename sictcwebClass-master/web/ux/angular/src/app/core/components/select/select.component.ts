import {
  Component, DoCheck,
  ElementRef,
  EventEmitter,
  HostListener,
  Input, IterableDiffer, IterableDiffers,
  OnChanges,
  OnInit,
  Output, QueryList,
  SimpleChanges, ViewChild, ViewChildren, HostBinding,
} from '@angular/core';

const propertyOf = <R>(key: keyof R) => key;

interface GenericWithReflectedValue<T> {
  data: T;
  reflect: keyof T;
}

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss']
})
export class SelectComponent<T> implements OnInit, OnChanges, DoCheck {

  @Input()
  data: T[];

  @Input()
  reflect;

  // @Input()
  // isMultiSelect: boolean;
  //
  // @Input()
  // searchable: boolean;

  @Input()
  initialSelect;

  @Input()
  initialSelectReflect;

  @Input()
  label: string;

  @HostBinding('class.disabled') 
  @Input()
  disabled: boolean = false;

  @Output()
  selected: EventEmitter<T> = new EventEmitter();

  selectable: GenericWithReflectedValue<T>[] = [];
  filteredSelectable: GenericWithReflectedValue<T>[] = [];
  selectedItem: GenericWithReflectedValue<T>;
  dataIterableDiffer: IterableDiffer<T>;

  filterSubstring = '';
  dropunderOpen = false;
  highlightedItemIndex = 0;

  @ViewChild('input', { static: true }) input: ElementRef;
  @ViewChildren('listItem') listItems: QueryList<ElementRef>;

  _placeholder = '';
  get placeholder(): string {
    if (this.label) {
      return this.selectedItem ? this.selectedItem.reflect.toString() : this.label;
    }
    return this.selectedItem ? this.selectedItem.reflect.toString() : 'Select item';
  }


  constructor(private self: ElementRef,
              private iterableDiffers: IterableDiffers) {
    this.dataIterableDiffer = this.iterableDiffers.find([]).create(null);
  }

  ngOnInit() {
    this.generateList();
  }

  ngOnChanges(changes: SimpleChanges) {
    this.reset();
    this.generateList();
  }

  ngDoCheck(): void {
    const changes = this.dataIterableDiffer.diff(this.data);
    if (!changes) { return; }
    this.reset();
    this.generateList();
  }

  reset() {
    this.selectedItem = null;
    this.highlightedItemIndex = 0;
  }

  clear() {
    this.filterSubstring = '';
    this.filteredSelectable = this.selectable;
    this.closeDropunder();
    this.input.nativeElement.blur();
  }

  private generateList() {
    const _selectable = [];
    if (Array.isArray(this.data)) {
      this.data.forEach((value: T) => {
        const item: GenericWithReflectedValue<T> = {
          data: value,
          reflect: value[this.reflect]
        };
        _selectable.push(item);
      });
      this.selectable = _selectable;
      this.filteredSelectable = _selectable;
    }
    this.setManualSelection(this.initialSelect, this.initialSelectReflect);
    this.selectOnlyOption();
  }

  setManualSelection(selectedItem: any, reflect?: any) {
    if (selectedItem) {
      this.selectable.forEach((selectable: GenericWithReflectedValue<T>, index: number) => {

        if (reflect) {
          if (selectable.data[reflect] === selectedItem) {
            this.select(index, selectable);
            return;
          }
        }

        if (selectable.data[this.reflect] === selectedItem) {
          this.select(index, selectable);
        }
      });
    }
  }

  filterDataByReflect(substring: string) {
    const filtered = [];
    this.selectable.forEach((selectable: GenericWithReflectedValue<T>) => {
      if (selectable.reflect.toString().toUpperCase().indexOf(substring.toUpperCase()) === -1) {
        return;
      }
      filtered.push(selectable);
    });
    this.filteredSelectable = filtered;
  }

  onInputKeydown($event: any) {
    switch ($event.keyCode) {
      case 9: // Tab
        this.clear();
        break;
      case 38: // Up Arrow
        if (this.highlightedItemIndex === 0) {
          this.highlightedItemIndex = this.filteredSelectable.length - 1;
          return;
        }
        this.highlightedItemIndex -= 1;
        break;
      case 40: // Down Arrow
        if (this.highlightedItemIndex === this.filteredSelectable.length - 1) {
          this.highlightedItemIndex = 0;
          return;
        }
        this.highlightedItemIndex += 1;
        break;
      case 13: // Enter
        const genericWithReflectedValue = this.filteredSelectable[this.highlightedItemIndex];
        if (genericWithReflectedValue) {
          this.select(this.highlightedItemIndex, genericWithReflectedValue);
          return;
        }
        break;
      default:
        this.highlightedItemIndex = 0;
    }

    // const item = this.listItems.toArray()[this.highlightedItemIndex];
    //
    // if (item) {
    //   item.nativeElement.scrollIntoView();
    // }
  }

  select(index: number, genericWithReflectedValue: GenericWithReflectedValue<T>) {
    this.highlightedItemIndex = index;
    this.selectedItem = genericWithReflectedValue;
    this.selected.emit(this.selectedItem.data);
    // setTimeout(() => {
    //   const el = document.getElementById(`dropdown-item-${index}`);
    //   if (el) {
    //     el.scrollIntoView();
    //   }
    // });
    this.clear();
  }

  openDropunder() {
    this.dropunderOpen = true;
  }

  closeDropunder() {
    this.dropunderOpen = false;
  }

  @HostListener('document:mousedown', ['$event'])
  clickOutside($event: any) {
    if (!this.self.nativeElement.contains(event.target)) {
      this.clear();
    }
  }

  focusInput() {
    setTimeout(() => {
      this.input.nativeElement.focus();
    }, 0);
  }

  selectOnlyOption() {
    if (this.selectable.length === 1 && this.selectedItem === null) {
      this.select(0, this.selectable[0]);
    }
  }
}
