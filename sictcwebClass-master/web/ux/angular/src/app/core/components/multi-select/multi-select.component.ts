import {
  Component, DoCheck,
  ElementRef,
  EventEmitter, HostListener,
  Input,
  IterableDiffer,
  IterableDiffers, OnChanges, OnInit,
  Output,
  QueryList, SimpleChanges,
  ViewChild,
  ViewChildren
} from '@angular/core';
import * as _ from 'lodash';


interface SelectableGenericWithReflectedValue<T> {
  selected: boolean;
  data: T;
  reflect: keyof T;
}

@Component({
  selector: 'app-multi-select',
  templateUrl: './multi-select.component.html',
  styleUrls: ['./multi-select.component.scss']
})
export class MultiSelectComponent<T> implements OnInit, OnChanges, DoCheck {

  // An array of any object
  @Input()
  data: T[];

  // The property of the object to scope on
  @Input()
  reflect: string;

  // The initial selected element, this would be the object[reflect] property
  @Input()
  initialSelect: any[];

  @Input()
  initialSelectReflect: any;

  // A label for the input other than the default (Select Item)
  @Input()
  label: string;

  // Outputs the selected data
  @Output()
  selected: EventEmitter<T[]> = new EventEmitter();

  selectable: SelectableGenericWithReflectedValue<T>[] = [];
  // The items that are currently filtered by the input in the searchbox
  filteredSelectable: SelectableGenericWithReflectedValue<T>[] = [];
  // A datastructure used to determine if data in the box has changed
  dataIterableDiffer: IterableDiffer<T>;
  initialSelectIterableDiffer: IterableDiffer<T>;
  // The current string we're filtering by
  filterSubstring = '';
  dropunderOpen = false;
  // The current highlgihted item by the arrow keys
  highlightedItemIndex = 0;

  allSelected = false;
  uniqueId: string;

  @ViewChild('input', { static: false }) input: ElementRef;
  @ViewChildren('listItem') listItems: QueryList<ElementRef>;


  constructor(private self: ElementRef,
    private iterableDiffers: IterableDiffers) {
      this.dataIterableDiffer = this.iterableDiffers.find([]).create(null);
      this.initialSelectIterableDiffer = this.iterableDiffers.find([]).create(null);
      this.uniqueId = _.uniqueId();
  }

  ngOnInit() {
    this.generateList();
  }

  ngOnChanges(changes: SimpleChanges) {
    // this.reset();
    // this.generateList();
  }

  ngDoCheck(): void {
    const changes1 = this.dataIterableDiffer.diff(this.data);
    const changes2 = this.initialSelectIterableDiffer.diff(this.initialSelect);
    const changes = changes1 || changes2;
    if (!changes) { return; }
    this.reset();
    this.generateList();
  }

  openDropunder() {
    this.dropunderOpen = true;
  }

  closeDropunder() {
    this.dropunderOpen = false;
  }

  filter(substring: string) {
    const filtered = [];
    this.selectable.forEach((selectable: SelectableGenericWithReflectedValue<T>) => {
      if (selectable.reflect.toString().toUpperCase().indexOf(substring.toUpperCase()) === -1) {
        return;
      }
      filtered.push(selectable);
    });
    this.filteredSelectable = filtered;
  }


  setSelection(selectedItems: any[], reflect?: any) {
    if (selectedItems && this.reflect) {
      this.selectable.forEach((selectable: SelectableGenericWithReflectedValue<T>, index: number) => {

        if (reflect) {
          selectedItems.forEach((selectedItem: any) => {
            if (selectable.data[reflect] === selectedItem) {
              this.select(index, selectable);
            }
          });
        }

        selectedItems.forEach((selectedItem: any) => {
          if (selectable.data[this.reflect] === selectedItem) {
            this.select(index, selectable);
          }
        });
      });
    }
  }

  reset() {
    this.selectable = [];
    this.allSelected = false;
  }

  clearSearch() {
    this.filterSubstring = '';
    this.filteredSelectable = this.selectable;
  }

  select(index: number, genericWithReflectedValue: SelectableGenericWithReflectedValue<T>) {
    this.highlightedItemIndex = index;

    genericWithReflectedValue.selected = !genericWithReflectedValue.selected;

    const items: T[] = [];

    this.selectable.forEach((item: SelectableGenericWithReflectedValue<T>) => {
      if (item.selected) {
        items.push(item.data);
      }
    });

    this.checkSelectAllState();
    this.selected.emit(items);
    this.clearSearch();
  }

  selectAll(event: any) {
    this.allSelected = event.target.checked;

    this.selectable.forEach((selectable: SelectableGenericWithReflectedValue<T>) => {
      selectable.selected = this.allSelected;
    });

    const items: T[] = [];

    this.selectable.forEach((item: SelectableGenericWithReflectedValue<T>) => {
      if (item.selected) {
        items.push(item.data);
      }
    });

    this.selected.emit(items);
    this.clearSearch();
  }


  @HostListener('document:mousedown', ['$event'])
  clickOutside($event: any) {
    if (!this.self.nativeElement.contains(event.target)) {
      this.clearSearch();
      this.closeDropunder();
      this.input.nativeElement.blur();
    }
  }

  focusInput() {
    setTimeout(() => {
      this.input.nativeElement.focus();
    }, 0);
  }

  onInputKeydown($event: any) {
    switch ($event.keyCode) {
      case 9: // Tab
        this.clearSearch();
        this.closeDropunder();
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
  }

  private generateList() {
    const _selectable = [];
    if (Array.isArray(this.data)) {
      this.data.forEach((value: T) => {
        const item: SelectableGenericWithReflectedValue<T> = {
          selected: false,
          data: value,
          reflect: value[this.reflect]
        };
        _selectable.push(item);
      });
      this.selectable = _selectable;
      this.filteredSelectable = _selectable;
    }
    this.setSelection(this.initialSelect, this.initialSelectReflect);
  }

  generatePlaceholder(): string {

    const selected = [];
    this.selectable.forEach((item: SelectableGenericWithReflectedValue<T>) => {
      if (item.selected) {
        selected.push(item.reflect);
      }
    });

    if (this.allSelected) {
      return 'All Selected';
    }
    if (this.label) {
      return selected.length > 0 ? selected.join(', ') : this.label;
    }
    return selected.length > 0 ? selected.join(', ') : 'Select item';
  }

  private checkSelectAllState() {

    let count = 0;
    this.selectable.forEach((selectable: SelectableGenericWithReflectedValue<T>) => {
      if (selectable.selected) {
        count++;
      }
    });

    if (count === this.selectable.length) {
      this.allSelected = true;
      return;
    }
    this.allSelected = false;
  }
}
