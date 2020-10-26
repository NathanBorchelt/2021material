/**
 * Created by dev on 3/9/2017.
 */

import { Injectable } from '@angular/core';

@Injectable({providedIn: 'root'})
export class BrowserInfoService {

  constructor() {
    this._isMobile = this.isMobileDevice();
  }

// Properties
  private _isMobile: boolean = false;
  public get isMobile(): boolean {
    return this._isMobile;
  }

  public isMobileDevice() {
    let devType = navigator.userAgent;
    if (this.isMobileResolution()){
      if (devType.match(/Android/i)) {
        return true;
      }
      else if (devType.match(/iPhone|iPad|iPod/i)) {
        return true;
      }
      else if (devType.match(/Opera Mini/i)) {
        return true;
      }
      else if (devType.match(/BlackBerry/i)) {
        return true;
      }
      else if (devType.match(/IEMobile/i)) {
        return true;
      }
    }
    return false;
  }

  // Utility
  private isMobileResolution(): boolean {
    let windowWidth = window.screen.availWidth;
    return (windowWidth <= 1200); // PX
  }

}
