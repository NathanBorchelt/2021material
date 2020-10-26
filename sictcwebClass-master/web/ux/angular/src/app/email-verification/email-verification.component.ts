import { Component, OnInit } from '@angular/core';
import { User } from '../core/interfaces/User';
import { UsersService } from '../core/services/users.service';
import { TopbarStateManager, TopbarConfig } from '../topbar/topbar.component';

export enum VERIFICATION_STATE {
  QUESTION,
  UPDATE,
  COMPLETE
}

@Component({
  selector: 'app-email-verification',
  templateUrl: './email-verification.component.html',
  styleUrls: ['./email-verification.component.scss']
})
export class EmailVerificationComponent implements OnInit, TopbarStateManager {

  user: User;

  VERIFICATION_STATE = VERIFICATION_STATE;
  state: VERIFICATION_STATE = VERIFICATION_STATE.QUESTION;
  get() {
    return this.state;
  }
  set(state: VERIFICATION_STATE) {
    this.state = state;
  }

  constructor(private usersService: UsersService) { }

  ngOnInit() {
    this.usersService.getProfile().subscribe((user: User) => {
      this.user = user;
    });
  }

  setTopbarState(currentConfig: TopbarConfig) {
    currentConfig.showTopbar = false;
  }
}
