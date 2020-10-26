import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ToastService } from '../components/toasts/toast.service';
import { Toast, ToastOptions, ToastStyle, ToastTimeout } from '../components/toasts/toast/toast';

export const EMAIL_VALID_REGEX = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]{2,63})$/;

@Injectable({providedIn: 'root'})
export class UtilService {

  constructor(private toastService: ToastService) { }

  getVinCheckDigit(VIN: string) {
    const transliterate = x => '0123456789.ABCDEFGH..JKLMN.P.R..STUVWXYZ'.indexOf(x) % 10;
    const map = '0123456789X';
    const weights = '8765432X098765432';
    let sum = 0;
    for (let i = 0; i < 17; ++i) {
      sum += transliterate(VIN[i]) * map.indexOf(weights[i]);
    }
    return map[sum % 11];
  }

  isValidVin(VIN: string) {
    if (VIN.length !== 17) { return false; }
    return this.getVinCheckDigit(VIN) === VIN[8];
  }

  isValidStock(stock: string) {
    const stockRegex = /^[-a-zA-Z0-9]{3,16}$/g;
    return stockRegex.test(stock);
  }

  logDebug(...args: any[]) {
    if (environment.logDebug) {
      const log = console.debug || console.log;
      log('DEBUG:', ...args);
    }
  }

  copyToClipboard(value: string) {
    document.addEventListener('copy', (e: ClipboardEvent) => {
      e.clipboardData.setData('text/plain', (value));
      e.preventDefault();
      document.removeEventListener('copy', null);
    });
    document.execCommand('copy');

    const toastOptions: ToastOptions = <ToastOptions>{};
    toastOptions.timeout = ToastTimeout.SHORT;
    toastOptions.title = 'Success!';
    toastOptions.body = `${value} copied to clipboard.`;
    toastOptions.style = ToastStyle.SUCCESS;
    const toast = new Toast(toastOptions);
    this.toastService.show(toast);
  }

  //   var letters = [{ k: "A", v: 1 }, { k: "B", v: 2 }, { k: "C", v: 3 },
  //   { k: "D", v: 4 }, { k: "E", v: 5 }, { k: "F", v: 6 }, { k: "G", v: 7 },
  //   { k: "H", v: 8 }, { k: "J", v: 1 }, { k: "K", v: 2 }, { k: "L", v: 3 },
  //   { k: "M", v: 4 }, { k: "N", v: 5 }, { k: "P", v: 7 }, { k: "R", v: 9 },
  //   { k: "S", v: 2 }, { k: "T", v: 3 }, { k: "U", v: 4 }, { k: "V", v: 5 },
  //   { k: "W", v: 6 }, { k: "X", v: 7 }, { k: "Y", v: 8 }, { k: "Z", v: 9 }];
  //   var weights = [8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2];
  //   var exclude = ["I", "O", "Q"];
  //   var val = 0;
  //   for (var idx = 0; idx < vin.length; idx++) {
  //       var item = vin.charAt(idx).toUpperCase();
  //       if (exclude.indexOf(item) != -1) { return false; }
  //       var pos = (item.match("^[0-9]+$") != null) ? parseInt(item) : letters.filter(x => x.k == item)[0].v;
  //       val += (pos * weights[idx]);
  //   }
  //   var checksum = (val % 11);
  //   var checksumStr = checksum < 10 ? checksum.toString() : "X";
  //   var result = vin.charAt(8) == checksumStr;
  //   return result;
  // }

  formatCurrency(value: number, minFractionDigits: number = 0) {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: minFractionDigits
    }).format(value);
  }


  formatMileage(value: number) {
    return `${new Intl.NumberFormat().format(value)}`;
  }
}
