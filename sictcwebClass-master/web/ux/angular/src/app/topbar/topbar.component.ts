import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import * as _ from 'lodash';
import { InventoryService } from '../core/services/inventory.service';
import { AuthService } from '../core/services/auth.service';
import { Router } from '@angular/router';
// import { FilterService } from '../inventory/filter.service';
import { User } from '../core/interfaces/User';
import { OrganizationsService } from '../core/services/organizations.service';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent implements OnInit, OnDestroy {

  defaultConfig: TopbarConfig = {
    showTopbar: true,
    showUserDropdown: true,
    showGetHelp: true,
    showHomeButton: true,
    showOrderSuppliesButton: true
  };

  @Input() config: TopbarConfig = _.cloneDeep(this.defaultConfig);


  userProfile: User;
  helpRedirectVisible = false;
  subscriptions: Subscription[] = [];

  isFulfiller: boolean = false;

  constructor(private router: Router,
              public inventoryService: InventoryService,
              public authService: AuthService,
              // public filterService: FilterService,
              private orgService: OrganizationsService
              ) { }

  ngOnInit() {
    this.subscriptions.push(this.orgService.currentOrganization$.subscribe(organization => {
      this.isFulfiller = this.orgService.isFulfiller(organization);
    }));

    this.subscriptions.push(this.authService.cpProfile$.subscribe((userProfile: User) => {
      this.userProfile = this.authService.isAuthenticated() ? userProfile : null;
    }));
  }

  ngOnDestroy() {
    this.subscriptions.forEach(x => x.unsubscribe());
  }

  logOut() {
    this.router.navigate(['/login']);
    // this.filterService.clearAll();
  }

  openGetHelp() {
    // this.helpRedirectVisible = true;
    /*const freshchat = (<any>window).fcWidget;
    if (freshchat && !freshchat.isOpen()) {
      freshchat.open();
      freshchat.show();
    }*/
  }
}

export interface TopbarConfig {
  showTopbar: boolean;
  showHomeButton: boolean;
  showGetHelp: boolean;
  showUserDropdown: boolean;
  showOrderSuppliesButton: boolean;
}

export interface TopbarStateManager {
  setTopbarState: (currentConfig: TopbarConfig) => void;
}
