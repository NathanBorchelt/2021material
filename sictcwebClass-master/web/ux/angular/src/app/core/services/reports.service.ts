import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { CP_UNISON_API, EndpointsService } from './endpoints.service';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { CPResponse } from '../interfaces/CPResponse';
import { OrdersReportRequest } from '../interfaces/Order';


@Injectable({providedIn: 'root'})
export class ReportsService {

  constructor(private httpClient: HttpClient, private endpointsService: EndpointsService) {}


  getVinpointBillingData(startDate: string, endDate: string): Observable<BillingModel[]> {
    const startDateURI = escape(startDate);
    const endDateURI = escape(endDate);
    return this.httpClient.get(this.endpointsService.build(CP_UNISON_API, `/v1/billing/overview/${startDateURI}/${endDateURI}`), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<BillingModel[]>>) => {
        return response.body.data;
      }));
  }

  getOrdersReportCSV(request: OrdersReportRequest) {
    return this.httpClient.post(this.endpointsService.build(CP_UNISON_API, `/v1/WebStore/GetOrdersAdminReportCSV`), request, { responseType: 'text' });
  }
}

export interface BillingModel {
  OrganizationName: string;
  DealershipId: number;
  DealershipName: string;
  ScanCount: number;
  RescanCount: number;
  IntegrationCount: number;
}
