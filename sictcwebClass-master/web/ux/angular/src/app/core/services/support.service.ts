import { Injectable } from '@angular/core';
import { EndpointsService, CP_UNISON_API } from './endpoints.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SupportRequest } from '../interfaces/SupportRequest';
import { share } from 'rxjs/operators';

@Injectable({providedIn: 'root'})
export class SupportService {

  constructor(private http: HttpClient,
              private endpoints: EndpointsService) { }

  submitSupport(supportRequest: SupportRequest): Observable<any> {
    const url = this.endpoints.build(CP_UNISON_API, '/v1/status/support');
    return this.http.post(url, supportRequest).pipe(share());
  }
}
