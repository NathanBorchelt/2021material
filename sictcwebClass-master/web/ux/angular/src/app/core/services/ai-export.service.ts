import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { EndpointsService, CP_UNISON_API, CP_PRINTER_API, CP_SCANNER_API } from './endpoints.service';
import { OrganizationsService } from './organizations.service';
import { HttpClient, HttpResponse, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { CPResponse } from '../interfaces/CPResponse';
import { AiExportFilter, AiExportDealership, AiExportInventory } from '../interfaces/AiExport';
import * as moment from 'moment';

@Injectable({ providedIn: 'root' })
export class AiExportService {

    constructor(
        private http: HttpClient,
        private orgService: OrganizationsService,
        private endpoints: EndpointsService) {
    }

    getDealerships(): Observable<AiExportDealership[]> {
        const orgId = this.orgService.currentOrganization.RecordId;
        return this.http.get(this.endpoints.build(CP_UNISON_API, '/v2/dealerships/ForAIExport/' + orgId), { observe: 'response' })
            .pipe(map((response: HttpResponse<CPResponse<any>>) => {
                if (response.status > 299) {
                    throw new Error('Service failed: ' + response.status);
                }
                return <AiExportDealership[]>response.body.data;
            }));
    }

    getInventory(dealershipId: number): Observable<AiExportInventory[]> {
        return this.http.get(this.endpoints.build(CP_UNISON_API, '/v2/inventory/forAIExport/' + dealershipId), { observe: 'response' })
            .pipe(map((response: HttpResponse<CPResponse<any>>) => {
                if (response.status > 299) {
                    throw new Error('Service failed: ' + response.status);
                }

                const results = <AiExportInventory[]>response.body.data;
                if (results) {
                    results.forEach(x => {
                        x.DealershipId = dealershipId;
                        x.ScannedDateParsed = moment.utc(x.ScannedDate).local();
                    });
                }

                return results;
            }));
    }
    
    exportInventory(body: AiExportFilter): Observable<boolean> {
        const headers = new HttpHeaders().set('Content-Type', 'application/json');
        return this.http.post(this.endpoints.build(CP_UNISON_API, '/v2/inventory/ExportToAI'), body, { observe: 'response', headers })
            .pipe(map((response: HttpResponse<CPResponse<any>>) => {
                if (response.status > 299) {
                    throw new Error('Service failed: ' + response.status);
                }
                return true;
            }), catchError((err: HttpErrorResponse) => {
                console.log('Export Inventory Error', err);
                let message = err.error && err.error.data && err.error.data.Message;
                message = message || 'Service failed: ' + err.message;
                return throwError(new Error(message));
            }));
    }
}