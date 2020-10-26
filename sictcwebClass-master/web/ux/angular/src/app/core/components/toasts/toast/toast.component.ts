import { Component, Input, OnInit } from '@angular/core';
import { Toast, ToastStyle } from './toast';
import { ToastService } from '../toast.service';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.scss'],
  animations: [
    // the fade-in/fade-out animation.
    trigger('fadeIn', [

      // the "in" style determines the "resting" state of the element when it is visible.
      state('in', style({opacity: 1})),

      // fade in when created. this could also be written as transition('void => *')
      transition(':enter', [
        style({opacity: 0}),
        animate(600 )
      ]),

      // fade out when destroyed. this could also be written as transition('void => *')
      transition(':leave',
        animate(600, style({opacity: 0})))
    ])
  ],
})
export class ToastComponent implements OnInit {

  @Input() toast: Toast;
  ToastStyle = ToastStyle;

  constructor(public toastService: ToastService) { }

  ngOnInit() {}

}
