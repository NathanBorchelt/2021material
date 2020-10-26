import { Injectable, NgZone } from '@angular/core';
import { Toast, ToastOptions } from './toast/toast';
import { Observable } from 'rxjs';
import { interval } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  toasts: Toast[] = [];

  constructor(private zone: NgZone) { }

  show(toast: Toast) {

    this.toasts.push(toast);

    if (toast.onShow) {
      toast.onShow();
    }

    if (toast.timeout) {
      this.zone.run(() => {
        interval(toast.timeout).take(1)
          .subscribe(() => this.dismiss(toast));
      });
    }
  }

  showToastOptions(toastOptions: ToastOptions) {

    const toast = new Toast(toastOptions);

    this.toasts.push(toast);

    if (toast.onShow) {
      toast.onShow();
    }

    if (toast.timeout) {
      this.zone.run(() => {
        interval(toast.timeout).take(1)
          .subscribe(() => this.dismiss(toast));
      });
    }
  }

  dismiss(toast: Toast) {

      const toastIndexToDismiss = this.toasts.indexOf(toast);
      if (toastIndexToDismiss > -1) {
        this.toasts.splice(toastIndexToDismiss, 1);
      }

    if (toast.onDismiss) {
      toast.onDismiss();
    }
  }
}

