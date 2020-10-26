import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { EndpointsService, CP_PRINTER_API, CP_UNISON_API } from './endpoints.service';
import { PrinterV2Generic } from '../interfaces/PrinterV2Generic';
import { Inventory } from '../../core/interfaces/Inventory';
import { InventoryService } from '../../core/services/inventory.service';
import { PrintLabel } from '../interfaces/PrintLabel';
import { DealershipsService } from '../../core/services/dealerships.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { CPResponse } from '../../core/interfaces/CPResponse';
import { map } from 'rxjs/operators';
import { PrinterV2 } from '../interfaces/PrinterV2';
import { GetPrintersV2 } from '../interfaces/GetPrintersV2';
import { PrinterTemplateV2 } from '../interfaces/PrinterTemplateV2';
import { PrinterV2Base } from '../interfaces/PrinterV2Base';
import { PrinterLogV2 } from '../interfaces/PrinterLogV2';
import { CanPerformAction } from '../interfaces/CanPerformAction';
import { PrinterV2Status } from '../interfaces/PrinterV2Status';
import { PrinterV2PrintLabel } from 'src/app/core/interfaces/PrinterV2PrintLabel';
import { PrinterV2PrintLabelBatch } from 'src/app/core/interfaces/PrinterV2PrintLabelBatch';
import { PrintHistory } from '../interfaces/PrintHistory';


@Injectable({ providedIn: 'root' })
export class PrintersService {

  private dataStore: PrinterV2Generic[];
  private _printers: BehaviorSubject<PrinterV2Generic[]>;
  private printers: Observable<PrinterV2Generic[]>;

  constructor(private http: HttpClient,
    private endpoints: EndpointsService,
    private dealershipsService: DealershipsService,
    private inventoryService: InventoryService) {
    this.dataStore = [];
    this._printers = new BehaviorSubject<PrinterV2Generic[]>(this.dataStore);
    this.printers = this._printers.asObservable();
  }

  getPrinterV2(foreignId: string): Observable<PrinterV2> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/Printer/getPrinter/${foreignId}`))
      .pipe(map((response: CPResponse<PrinterV2>) => {
        return response.data;
      }));
  }

  getPrintersV2(orgId: number): Observable<PrinterV2[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/Printer/GetPrintersByUserAndOrg/${orgId}`))
      .pipe(map((response: CPResponse<PrinterV2[]>) => {
        return response.data;
      }));
  }

  getPrinterTemplatesV2(): Observable<PrinterTemplateV2[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, '/v2/Printer/templates'))
      .pipe(map((response: CPResponse<PrinterTemplateV2[]>) => {
        return response.data;
      }));
  }

  // updatePrinterV2(printer: PrinterV2) {
  //   return this.http.put(this.endpoints.build(CP_UNISON_API, `/v2/Printer/updatePrinter`), printer).share();
  // }

  // pushPrinterGraphicsV2(imei: string) {
  //   return this.http.put(this.endpoints.build(CP_UNISON_API, `/v2/Printer/PushGraphics/${imei}`), 0).share();
  // }

  // createPrinterV2(printer: PrinterV2Base): Observable<any> {
  //   return this.http.post(this.endpoints.build(CP_UNISON_API, `/v2/Printer/addPrinter`), printer).share();
  // }

  // deletePrinterV2(printer: PrinterV2): Observable<any> {
  //   return this.http.delete(this.endpoints.build(CP_UNISON_API, `/v2/Printer/deletePrinter/${printer.IMEI}`)).share();
  // }

  printTestLabelV2(printer: PrinterV2): Observable<any> {
    return this.http.put(this.endpoints.build(CP_UNISON_API, `/v2/Printer/testprintjob`), { IMEI: printer.IMEI });
  }

  resetPrinterV2(printer: PrinterV2): Observable<any> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/Printer/resetPrinter/${printer.IMEI}`));
  }

  getPrinterLogsV2(printer: PrinterV2): Observable<PrinterLogV2[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/Printer/logs/${printer.IMEI}`))
      .pipe(map((response: CPResponse<PrinterLogV2[]>) => {
        return response.data;
      }));
  }

  // uploadTemplateV2(template: PrinterTemplateV2): Observable<any> {
  //   return this.http.post(this.endpoints.build(CP_UNISON_API, `/v2/Printer/uploadTemplate?`), template)
  //     .pipe(map((response: CPResponse<PrinterTemplateV2>) => {
  //       return response.data;
  //     })).share();
  // }

  editTemplateV2(template: PrinterTemplateV2): Observable<any> {
    return this.http.put(this.endpoints.build(CP_UNISON_API, `/v2/Printer/updateTemplate`), template);
  }

  canDeleteTemplateV2(template: PrinterTemplateV2): Observable<CanPerformAction> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/Printer/canDeleteTemplate/${template.Id}`))
      .pipe(map((response: CPResponse<CanPerformAction>) => {
        return response.data;
      }));
  }

  // deleteTemplateV2(template: PrinterTemplateV2): Observable<any> {
  //   return this.http.delete(this.endpoints.build(CP_UNISON_API, `/v2/Printer/deleteTemplate/${template.Id}`)).share();
  // }

  getPrinterStatus(printer: PrinterV2): PrinterV2Status {
    if (!printer.Connected) {
      return new PrinterV2Status('OFFLINE', ['Printer disconnected']);
    } else {
      return this.parseZebraHostStatus(printer.Shadow.LastPrintStatus.Errors);
    }
  }

  getPrinterHistory(foreignId: string): Observable<PrintHistory[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/printer/printhistory/${foreignId}`))
      .pipe(map((response: CPResponse<PrintHistory[]>) => {
        return response.data;
      }));
  }

  // printLabelV2(label: PrinterV2PrintLabel) {
  //   return this.http.post(this.endpoints.build(CP_UNISON_API, '/v2/printer/PrintLabel'), label).share();
  // }

  batchPrintLabelV2(model: PrinterV2PrintLabelBatch): Observable<unknown> {
    return this.http.post(this.endpoints.build(CP_UNISON_API, '/v2/printer/BatchPrintLabel'), model)
  }

  getAllPrintersV2(dealershipId: number): Observable<PrinterV2Generic[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/printer/GetAllPrinters/${dealershipId}`), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<PrinterV2Generic[]>>) => {
        this.dataStore = response.body.data;
        this._printers.next(this.dataStore);
        return this.dataStore;
      }));
  }

  parseZebraHostStatus(status: string): PrinterV2Status {


    if (!status) {
      return new PrinterV2Status('ERROR', ['Printer never connected']);
    }

    const radix = 16; // Hex

    const nibbleOneMap: Map<number, string> = new Map();
    nibbleOneMap.set(0, 'No error present');
    nibbleOneMap.set(8, 'Cutter fault');
    nibbleOneMap.set(4, 'Head open');
    nibbleOneMap.set(2, 'Ribbon out');
    nibbleOneMap.set(1, 'Media out');

    const nibbleTwoMap: Map<number, string> = new Map();
    nibbleTwoMap.set(0, 'No error present');
    nibbleTwoMap.set(8, 'Printhead detection error');
    nibbleTwoMap.set(4, 'Bad printhead element');
    nibbleTwoMap.set(2, 'Motor over temperature');
    nibbleTwoMap.set(1, 'Printhead over temperature');

    const nibbleThreeMap: Map<number, string> = new Map();
    nibbleThreeMap.set(0, 'No error present');
    nibbleThreeMap.set(2, 'Printhead thermistor open');
    nibbleThreeMap.set(1, 'Invalid firmware configuration');

    const nibbleFourMap: Map<number, string> = new Map();
    nibbleFourMap.set(0, 'No error present');
    nibbleFourMap.set(8, 'Clear paper path failed');
    nibbleFourMap.set(4, 'Paper feed error');
    nibbleFourMap.set(2, 'Presenter not running');
    nibbleFourMap.set(1, 'Paper jam during retract');

    const nibbleFiveMap: Map<number, string> = new Map();
    nibbleFiveMap.set(0, 'No error present');
    nibbleFiveMap.set(8, 'Black mark not found');
    nibbleFiveMap.set(4, 'Black mark calibrate error');
    nibbleFiveMap.set(2, 'Retract function timed out');
    nibbleFiveMap.set(1, 'Paused');

    const errorMap: Map<number, Map<number, string>> = new Map();

    // Key is the index of the nibble in the error, map is obviously the map
    // above that can parse the error at that nibble's index
    errorMap.set(16, nibbleOneMap);
    errorMap.set(15, nibbleTwoMap);
    errorMap.set(14, nibbleThreeMap);
    errorMap.set(13, nibbleFourMap);
    errorMap.set(12, nibbleFiveMap);

    const outputStatus: PrinterV2Status = new PrinterV2Status('READY', []);

    errorMap.forEach((mapValue: Map<number, string>, nibbleIndex: number) => {
      // Grab the error nibble that we need based on the errorMaps Key, which corresponds to nibbles' index
      const nibbleChar = status.charAt(nibbleIndex);
      const nibble = parseInt(nibbleChar, radix);

      if (nibble !== 0 && mapValue.get(nibble)) {
        outputStatus.status = 'ERROR';
        outputStatus.reasons.push(mapValue.get(nibble));
        return;
      }

      // Represents the sum the of the present error flags
      let errorsSum: number = null;

      mapValue.forEach((errorValue: string, errorKey: number) => {

        // Looks like we have multiple errors, lets try to break it down into its parts
        if (errorKey === 0 || errorsSum === 0) return;

        // Looks like this is the first go around, set the errorsSum to be the value of our nibble
        if (!errorsSum) {
          errorsSum = nibble;
        }

        const errorsSubtract = (errorsSum - errorKey);

        // Did it work? Is it not negative? Great!, let's add it to the list of errors and get outta here!
        if (errorsSubtract > -1) {
          errorsSum = errorsSubtract;
          outputStatus.status = 'ERROR';
          outputStatus.reasons.push(errorValue);
        }
      });
    });
    return outputStatus;
  }


  /// V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1 V1
  submitNetworkPrinterById(id: number): Observable<PrinterV2Generic[]> {
    return this.http.get(this.endpoints.build(CP_PRINTER_API, `/group/${id}/printers`), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<PrinterV2Generic[]>>) => {
        return response.body ? response.body.data : null;
      }));
  }

  // getNetworkPrinterbyId(id: number): Observable<PrinterV2Generic[]> {
  //   return this.submitNetworkPrinterById(id)
  //     .map(printers => {
  //       this.dataStore = printers;
  //       this._printers.next(this.dataStore);
  //       return this.dataStore;
  //     });
  // }

  // printLabel(label: PrintLabel, printerId: number, vehicleId: number) {
  //     label.data.printerId = printerId;
  //     this.http.post(this.endpoints.build(CP_PRINTER_API, '/queue/push/'), label)
  //       .pipe(map((response: HttpResponse<null>) => {
  //         if (response.status > 299) {
  //           throw new Error('Print Failed: ' + response.status);
  //         }
  //         return response;
  //       }))
  //       .subscribe(_ => {
  //         this.inventoryService.updateLastLabelDate(vehicleId);
  //       });
  // }

  /*batchPrintLabel(labels, ids) {
    this.http.post(this.endpoints.build(CP_PRINTER_API, '/queue/batch'), labels, { observe: 'response' })
      .pipe(map((response: HttpResponse<null>) => {
        if (response.status > 299) {
          throw new Error('Print Failed: ' + response.status);
        }
        return response;
      })).subscribe(_ => {
        this.inventoryService.updateLastLabelDateBatch(ids);
      });
  }*/

  /*compilePrinterLabel(inventoryItem: Inventory, pid: number): PrintLabel {
    const x: PrintLabel = {
      data: {
        appId: 1, printerId: pid, Key: '', doc: {
          vehicle: {
            color: '',
            make: '',
            model: '',
            stock: '',
            vin: '',
            year: 0,
            mileage: 0
          }
        }
      }
    };
    x.data.Key = inventoryItem.VIN;
    x.data.doc.vehicle.color = inventoryItem.Color;
    x.data.doc.vehicle.make = inventoryItem.Make;
    x.data.doc.vehicle.model = inventoryItem.Model;
    x.data.doc.vehicle.stock = inventoryItem.Stock;
    x.data.doc.vehicle.vin = inventoryItem.VIN;
    x.data.doc.vehicle.year = inventoryItem.Year;
    x.data.doc.vehicle.mileage = inventoryItem.Mileage;
    return x;
  }*/
}
