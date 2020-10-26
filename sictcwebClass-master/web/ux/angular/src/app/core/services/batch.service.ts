import { CP_BATCH_API, CP_UNISON_API, EndpointsService } from './endpoints.service';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { BatchActivate, BatchAdd, BatchDelete, BatchUpdate, BatchMove, BatchSearchResult, BatchCheckAction, BatchCheckActionResult } from '../interfaces/Batch';
import { CPResponse } from '../interfaces/CPResponse';

import { OrganizationsService } from './organizations.service';
import { map } from 'rxjs/operators';
// import { FilterService } from 'src/app/inventory/filter.service';
import { SimpleMapViewModel } from '../interfaces/SimpleMapViewModel';
// import { FilterItem } from '../../inventory/omni-search/models/omni-search';


@Injectable({providedIn: 'root'})
export class BatchService {
  filteredMapInventory: SimpleMapViewModel[] = [];

  batchActive = false;
  private _batchActive = new BehaviorSubject<boolean>(this.batchActive);
  batchActive$ = this._batchActive.asObservable();

  private selectedInventory: Set<number> = new Set();
  private _selectedInventory = new BehaviorSubject<Set<number>>(this.selectedInventory);
  selectedInventory$ = this._selectedInventory.asObservable();

  private get orgId() { return this.orgsService.currentOrganization.RecordId; }

  constructor(private endpoints: EndpointsService,
              private http: HttpClient,
              private orgsService: OrganizationsService,
              // private filterService: FilterService
              ) {
      // this.filterService.filteredMapInventory$.subscribe(inventory => {
      //   this.filteredMapInventory = inventory.filter(x => !x.ExternalStatus); //cannot select external inventory
      // });

      // this.filterService.filterItems$.subscribe((filterItems: Set<FilterItem>) => {
      //   this.clearSelectedInventory();
      // });
  }

  toggleBatchActive(active: boolean = null) {
    active = active != null ? active : !this.batchActive;
    this.batchActive = active;
    this._batchActive.next(this.batchActive);
  }

  toggleAllSelectedInventory(selected: boolean) {
    if (!selected) {
      this.clearSelectedInventory();
      return;
    }

    this.filteredMapInventory.forEach(x => this.selectedInventory.add(x.InventoryId));
    this._selectedInventory.next(this.selectedInventory);
  }

  clearSelectedInventory() {
    this.selectedInventory.clear();
    this._selectedInventory.next(this.selectedInventory);
  }

  toggleSelectedInventory(inventoryId: number, selected: boolean = null) {
    selected = selected != null ? selected : !this.selectedInventory.has(inventoryId);
    if (selected) {
      this.selectedInventory.add(inventoryId);
    } else {
      this.selectedInventory.delete(inventoryId);
    }
    this._selectedInventory.next(this.selectedInventory);
  }

  isInventorySelected(inventoryId: number) {
    return this.selectedInventory.has(inventoryId);
  }

  batchAdd(batchAdd: BatchAdd): Observable<unknown> {
    batchAdd.OrganizationId = batchAdd.OrganizationId || this.orgId;
    const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
    const url = this.endpoints.buildSecure(CP_BATCH_API, '');
    return this.http.post(url, batchAdd, { headers: headers });
  }

  batchUpdate(batchUpdate: BatchUpdate) {
    batchUpdate.OrganizationId = batchUpdate.OrganizationId || this.orgId;
    const url = this.endpoints.build(CP_UNISON_API, `/v1/inventory/Batch`);
    return this.http.put(url, batchUpdate).pipe(map(x => this.toggleAllSelectedInventory(false)));
  }

  batchMove(batchMove: BatchMove) {
    batchMove.OrganizationId = batchMove.OrganizationId || this.orgId;
    const url = this.endpoints.build(CP_UNISON_API, `/v1/inventory/MoveBatch`);
    return this.http.put(url, batchMove).pipe(map(x => this.toggleAllSelectedInventory(false)));
  }

  batchDelete(batchDelete: BatchDelete): Observable<unknown> {
    batchDelete.OrganizationId = batchDelete.OrganizationId || this.orgId;
    const url = this.endpoints.build(CP_UNISON_API, `/v1/inventory/DeleteBatch`);
    return this.http.post(url, batchDelete).pipe(map(x => this.toggleAllSelectedInventory(false)));
  }

  batchActivate(batchActivate: BatchActivate): Observable<unknown> {
    batchActivate.OrganizationId = batchActivate.OrganizationId || this.orgId;
    const url = this.endpoints.build(CP_UNISON_API, `/v1/inventory/ActivateBatch`);
    return this.http.put(url, batchActivate).pipe(map(x => this.toggleAllSelectedInventory(false)));
  }

  // batchCheckAction(batchCheck: BatchCheckAction): Observable<BatchCheckActionResult> {
  //   const url = this.endpoints.build(CP_UNISON_API, `/v2/inventory/batchcheck`);
  //   return this.http.post(url, batchCheck)
  //     .map((response: CPResponse<BatchCheckActionResult>) => {
  //       const data = response.data;
  //       return data;
  //     });
  // }
}
