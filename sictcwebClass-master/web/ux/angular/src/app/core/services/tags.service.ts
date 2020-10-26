import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { EndpointsService, CP_UNISON_API } from './endpoints.service';
import { TagDefinition, BatchTagDefinition } from '../interfaces/Tag';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, share } from 'rxjs/operators';
import { CPResponse } from '../interfaces/CPResponse';
import { OrganizationsService } from './organizations.service';
import { TagDefinitions } from '../interfaces/TagDefinitions';
import { SimpleTagDefinition } from '../interfaces/SimpleTagDefinition';
import { InventoryTag } from '../interfaces/InventoryTag';

@Injectable({providedIn: 'root'})
export class TagsService {

  constructor(private http: HttpClient,
              private endpoints: EndpointsService,
              private organizationsService: OrganizationsService) {}


  getActiveTagDefinitions(): Observable<TagDefinition[]> {
    const organizationRecordId = this.organizationsService.currentOrganization.RecordId;
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/tags/organization/${organizationRecordId}`))
      .pipe(map((response: CPResponse<TagDefinition[]>) => response.data));
  }

  getAllTagDefinitions(): Observable<TagDefinitions> {
    const organizationRecordId = this.organizationsService.currentOrganization.RecordId;
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/tags/byorg/${organizationRecordId}`))
      .pipe(map((response: CPResponse<TagDefinitions>) => response.data));
  }

  getDeletedTagDefinitions(orgId: number): Observable<TagDefinition[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/tags/deleted/organization/${orgId}`))
      .pipe(map((response: CPResponse<TagDefinition[]>) => response.data));
  }

  createTagDefinition(tagDefinition: SimpleTagDefinition): Observable<unknown> {
    return this.http.post(this.endpoints.build(CP_UNISON_API, '/v1/tags'), tagDefinition)
      .pipe(map((response: CPResponse<TagDefinition>) => response.data), share());
  }

  updateTagDefinition(tagDefinition: SimpleTagDefinition): Observable<unknown> {
    return this.http.put(this.endpoints.build(CP_UNISON_API, '/v1/tags'), tagDefinition)
      .pipe(share());
  }

  deleteTagDefinition(tagDefinition: SimpleTagDefinition): Observable<unknown> {
    return this.http.delete(this.endpoints.build(CP_UNISON_API, `/v1/tags/${tagDefinition.RecordId}`))
      .pipe(share());
  }

  activateTagDefinition(tagDefinition: SimpleTagDefinition): Observable<unknown> {
    return this.http.put(this.endpoints.build(CP_UNISON_API, `/v1/tags/Activate/${tagDefinition.RecordId}`), 0)
      .pipe(share());
  }

  batchUpsertTags(tagDefinitions: BatchTagDefinition[]): Observable<InventoryTag[]> {
    let dataWrap;
    if (tagDefinitions) {
      dataWrap = {
        data: tagDefinitions,
        source: 1
      };
    }
    return this.http.post(this.endpoints.build(CP_UNISON_API, `/v1/tags/UpsertTagsBatch`), dataWrap, { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<InventoryTag[]>>) => {
        if (response.status > 299) {
          throw new Error('Update failed: ' + response.status);
        }
        return response.body.data;
      }));
  }
}
