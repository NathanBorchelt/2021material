import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { EndpointsService, CP_SOCK_ROOT, CP_SOCK_PATH, CP_SOCK_NS } from './endpoints.service';
import { OrganizationsService } from './organizations.service';
import { AuthService } from './auth.service';
import { Message } from '../interfaces/Message';
import * as io from 'socket.io-client';
import { UtilService } from './util.service';


@Injectable({providedIn: 'root'})
export class SocketService {
  private socket;
  private messageSubject = new Subject<Message>();
  message$ = this.messageSubject.asObservable();

  constructor(private authService: AuthService,
    private organizationsService: OrganizationsService,
    private endpoints: EndpointsService,
    private utilService: UtilService) {
    this.init();
  }

  private init() {
    //re-connect whenever org changes
    let lastOrgId = -1;
    this.organizationsService.currentOrganization$.subscribe(org => {
      const orgChanged = org.RecordId !== lastOrgId;
      if (!orgChanged) return; //do nothing if no real change
      lastOrgId = org.RecordId;

      if (org.RecordId !== -1) {
        this.connect();
      } else {
        this.disconnect();
      }
    });
  }

  private connect() {
    this.disconnect();

    const url = `${window.location.protocol}//${this.endpoints.values[CP_SOCK_ROOT]}${this.endpoints.values[CP_SOCK_NS]}`;
    this.socket = io(url, {
      path: this.endpoints.values[CP_SOCK_PATH]
    });

    this.socket.on('connect', () => {
      if (this.testAuth()) {
        this.login();
      }
    });

    this.socket.on('inventory', (data) => {
      if (this.testAuth()) {
        this.messageSubject.next(data);
      }
    });

    this.utilService.logDebug('connected to socket.io');
  }

  private disconnect() {
    if (!this.socket) return;

    this.socket.disconnect();
    this.socket = null;
    
    this.utilService.logDebug('disconnected from socket.io');
  }

  private testAuth(): boolean {
    if (!this.authService.isAuthenticated(true)) {
      this.disconnect();
      return false;
    }
    return true;
  }

  private login() {
    let login: any = {};
    //login.uid = <number>p["uid"]; // no longer used/required
    login.name = this.authService.userName;
    login.oid = this.organizationsService.currentOrganization.RecordId;
    this.socket.emit('login', login);
  }
}
