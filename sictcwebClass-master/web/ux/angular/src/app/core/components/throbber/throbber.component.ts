import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-throbber',
  templateUrl: './throbber.component.html',
  styleUrls: ['./throbber.component.scss']
})
export class ThrobberComponent implements OnInit {

  @Input()
  diameter = 30;

  @Input()
  color = 'white';

  constructor() { }

  ngOnInit() {}
}
