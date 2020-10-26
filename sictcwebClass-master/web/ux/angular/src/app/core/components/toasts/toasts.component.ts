import { Component, OnInit } from '@angular/core';
import { ToastService } from './toast.service';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-toasts',
  templateUrl: './toasts.component.html',
  styleUrls: ['./toasts.component.scss'],
  animations: [
    // the fade-in/fade-out animation.
    trigger('fadeIn', [

      // the "in" style determines the "resting" state of the element when it is visible.
      state('in', style({ opacity: 1, transform: 'translateX(0)'})),

      // fade in when created. this could also be written as transition('void => *')
      transition(':enter', [
        style({opacity: 0, transform: 'translateX(50px)'}),
        animate(300 )
      ]),

      // fade out when destroyed. this could also be written as transition('void => *')
      transition(':leave',
        animate(300, style({opacity: 0, transform: 'translateX(50px)'})))
    ])
  ],
})
export class ToastsComponent {

  constructor(public toastService: ToastService) { }

}
