import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { DOCUMENT } from "@angular/common";
import { TopbarStateManager, TopbarComponent } from './topbar/topbar.component';
import * as _ from 'lodash';
// import { Angulartics2GoogleTagManager } from 'angulartics2/gtm';
import { NavigationEnd, Router } from '@angular/router';
// import { UsersService } from './core/services/users.service';
// import { NgxFreshChatService } from 'ngx-freshchat';
// import { User } from './core/interfaces/User';
import { setTheme } from 'ngx-bootstrap/utils';
// import { FilterService } from './inventory/filter.service';
// import { OrganizationsService } from './core/services/organizations.service';
// import { BatchService } from './core/services/batch.service';
// import { AuthService } from './core/services/auth.service';
// import { InventoryService } from './core/services/inventory.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent implements OnInit {

  @ViewChild('topbar', { static: false }) topbar: TopbarComponent;

  private doc: Document;
  freshChatInitialized = false;

  constructor(
    // public angulartics2GoogleTagManager: Angulartics2GoogleTagManager,
    @Inject(DOCUMENT) private document: any,
    private router: Router,
    // private usersService: UsersService,
    // private freshchat: NgxFreshChatService,
    // private organizationsService: OrganizationsService,
    // private filterService: FilterService,
    // private batchService: BatchService,
    // private authService: AuthService,
    // private inventoryService: InventoryService
    ) {
    this.doc = document;
    setTheme('bs3'); //inform ngx-bootstrap we are using v3, not v4

    // Reset data on org switch, I located the code here due to circular dependancy issues
    // this.organizationsService.currentOrganization$.subscribe(organization => {
    //   if (this.authService.isAuthenticated && organization.RecordId !== -1) {
    //     this.filterService.clearFilters();
    //     this.batchService.clearSelectedInventory();
    //   }
    // });
  }

  ngOnInit() {
    // Feels a little janky and non-Angular, but it works well and only
    // happens on startup.  Disregard if we can't find the element,
    // for instance, if we're in a testing environment.
    const el = this.doc.getElementsByClassName('preload')[0];
    if (el) {
      el.parentElement.removeChild(el);
    }
  }

  // When the user changes the active page
  onRouterOutletActivate(component: TopbarStateManager) {
    if (typeof component['setTopbarState'] === 'function') {
      this.topbar.config = _.cloneDeep(this.topbar.defaultConfig);
      component.setTopbarState(this.topbar.config);
    } else {
      this.topbar.config = _.cloneDeep(this.topbar.defaultConfig);
    }
    // Hide Topbar on /login route
    this.router.events.subscribe((nav) => {
      if (nav instanceof NavigationEnd) {
        if (this.router.url !== '/login' && this.router.url !== '/password-reset') {
          // this.startFreshChat();
        } else {
          // this.stopFreshChat();
        }
        if (this.router.url !== '/inventory') {
          // this.batchService.clearSelectedInventory();
          // this.batchService.toggleBatchActive(false);
          // this.inventoryService.toggleDetails({ InventoryId: null, DetailsViewOpen: false, ExternalStatus: null });
        }
      }
    });
  }

  // startFreshChat() {
  //   if (this.freshChatInitialized || !this.authService.isAuthenticated()) return;

  //   this.usersService.getProfile().subscribe((user: User) => {
  //     this.freshchat.init({
  //       token: '1ea1be64-91ac-46e6-97a9-c34ccc05cadd',
  //       host: 'https://wchat.freshchat.com',
  //       externalId: `${user.RecordId}`,
  //       firstName: user.FirstName,
  //       lastName: user.LastName,
  //       email: user.Email,
  //       phone: user.Phone,
  //       phoneCountryCode: '+1',
  //     }).take(1).subscribe(() => {
  //       this.freshChatInitialized = true;
  //     });
  //   });
  // }

  // stopFreshChat() {
  //   if (this.freshChatInitialized) {
  //     this.freshchat.destroy();
  //     this.freshChatInitialized = false;
  //   }
  // }
}

