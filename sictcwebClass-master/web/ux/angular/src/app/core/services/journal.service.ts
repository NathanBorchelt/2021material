import { CP_UNISON_API, EndpointsService } from './endpoints.service';
import { Injectable } from '@angular/core';
import { Journal } from '../interfaces/Journal';
import { HttpClient } from '@angular/common/http';
import { CPResponse } from '../interfaces/CPResponse';
import * as moment from 'moment';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class JournalService {
  
  constructor(private http: HttpClient, private endpoints: EndpointsService) {
  }

  getJournal(inventoryId: number): Observable<Journal[]> {
    return this.http.get<CPResponse<Journal[]>>(this.endpoints.build(CP_UNISON_API, `/v1/journal/ByInventory/${inventoryId}`))
      .map(response => {
        var data = response.data;

        // Unison api is returning Data as a string not an object
        data.forEach(journal => {
          journal.CreatedDate += 'Z'; //API does not include timezone offset, fix it
          journal.CreatedMoment = moment.utc(journal.CreatedDate).local();
          journal.Data = JSON.parse(journal.Data);
        });

        return data;
      });
  }
}
