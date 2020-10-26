import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { EndpointsService, CP_PRINTER_API, CP_UNISON_API } from './endpoints.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { CPResponse } from '../../core/interfaces/CPResponse';
import { map } from 'rxjs/operators';
import { OrganizationsService } from './organizations.service';
import { SimpleLabelModel } from '../interfaces/SimpleLabelModel';
import { PrinterLabelModel } from '../../core/interfaces/PrinterLabelModel';
import { SimpleDealership } from '../interfaces/SimpleDealership';
// import { LabelsComponent } from 'src/app/maintenance/labels/labels.component';
// import { LabelsModule } from 'src/app/maintenance/labels/labels.module';
import { Printer } from '../interfaces/Printer';


@Injectable({
  providedIn: 'root'
})


export class LabelsService {

  constructor(private http: HttpClient,
    private orgService: OrganizationsService,
    private endpoints: EndpointsService) {
    }

  getLabels(): Observable<SimpleLabelModel[]> {
    const orgId = this.orgService.currentOrganization.RecordId;
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/Printer/labels/${orgId}`))
      .pipe(map((response: CPResponse<SimpleLabelModel[]>) => {
        return response.data
      }));
  }

  getExtendedLabel(labelId: number): Observable<PrinterLabelModel> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/Printer/label/${labelId}`))
      .pipe(map((response: CPResponse<PrinterLabelModel>) => {
        return response.data
      }));
  }

  createNewLabel(newLabel: PrinterLabelModel): Observable<PrinterLabelModel> {
    return this.http.post(this.endpoints.build(CP_UNISON_API, '/v2/Printer/label'), newLabel)
      .pipe(map((response: CPResponse<PrinterLabelModel>) => {
        return response.data;
      })).share();
  }

  saveLabel(newLabel: PrinterLabelModel) {
    return this.http.put(this.endpoints.build(CP_UNISON_API, `/v2/Printer/label`), newLabel).share();
  }

  deleteLabel(labelId: number) {
    return this.http.delete(this.endpoints.build(CP_UNISON_API, `/v2/Printer/label/${labelId}`)).share();
  }
}
