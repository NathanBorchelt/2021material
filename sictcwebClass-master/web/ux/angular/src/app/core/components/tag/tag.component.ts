import { Component, Input, OnInit } from '@angular/core';
import { SimpleTagDefinition } from '../../interfaces/SimpleTagDefinition';

@Component({
  selector: 'app-tag',
  templateUrl: './tag.component.html',
  styleUrls: ['./tag.component.scss']
})
export class TagComponent implements OnInit {

  @Input() simpleTagDefinition: SimpleTagDefinition;
  // Advanced tag active / inactive status
  @Input() active = true;

  constructor() { }

  ngOnInit() {

  }

  toggle() {
    if (this.simpleTagDefinition.Type === 'ADVANCED') {
      this.active = !this.active;
    }
  }

  generateStyle(color: string): any {

    let style = {
      'background-color': color,
      'border-color': color,
      'color': '#FFF'
    };

    if (!this.active && this.simpleTagDefinition.Type === 'ADVANCED') {
      style = {
        'background-color': '#FFF',
        'border-color': color,
        'color': color,
      };
    }

    if (this.simpleTagDefinition.Type === 'BASIC') {
      style['padding-left'] = '20px';
    }

    return style;
  }
}
