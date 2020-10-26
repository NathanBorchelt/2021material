import { Component, Input, OnInit } from '@angular/core';
import { User } from '../../core/interfaces/User';

@Component({
  selector: 'app-email-verification-complete',
  templateUrl: './email-verification-complete.component.html',
  styleUrls: ['./email-verification-complete.component.scss']
})
export class EmailVerificationCompleteComponent implements OnInit {

  @Input() user: User;

  constructor() { }

  ngOnInit() {
  }

}
