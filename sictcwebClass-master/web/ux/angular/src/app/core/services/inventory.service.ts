import { map } from 'rxjs/operators';
import { Observable, BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';
import { EndpointsService, CP_UNISON_API } from './endpoints.service';
import { OrganizationsService } from './organizations.service';
import { HttpClient } from '@angular/common/http';
// import { BsModalService, BsModalRef } from 'ngx-bootstrap';
// import { InventoryModalExternalComponent } from 'src/app/inventory/inventory-modal-external/inventory-modal-external.component';
import { Inventory, ExternalStatus } from '../interfaces/Inventory';
import { CPResponse } from '../interfaces/CPResponse';
// import { InventoryView } from '../../inventory/InventoryView';
import { InventoryDelete } from '../interfaces/InventoryDelete';
import { InventoryTag, TagStatus } from '../interfaces/InventoryTag';
import { ScanMetrics } from '../interfaces/ScanMetrics';

export interface InventoryDetailsView {
  InventoryId: number;
  DetailsViewOpen: boolean;
  ExternalStatus: ExternalStatus;
}


@Injectable({providedIn: 'root'})
export class InventoryService {

  inventoryCardView = { name: 'Cards'};
  inventoryTableView = { name: 'Table'};
  inventoryMapView = { name: 'Map'};

  // inventoryViews: InventoryView[] = [
  //   this.inventoryCardView,
  //   this.inventoryTableView,
  //   this.inventoryMapView
  // ];

  // viewChanged$: Observable<InventoryView>;
  resizeVirtualScroll$: Observable<boolean>;

  inventoryDetailsView: BehaviorSubject<InventoryDetailsView> = new BehaviorSubject<InventoryDetailsView>({
    InventoryId: null,
    DetailsViewOpen: false,
    ExternalStatus: null
  });

  private selectedInventory: Inventory = null;
  selectedInventorySubject: BehaviorSubject<Inventory> = new BehaviorSubject(this.selectedInventory);
  selectedInventory$: Observable<Inventory> = this.selectedInventorySubject.asObservable();

  inventoryDetailsView$: Observable<InventoryDetailsView> = this.inventoryDetailsView.asObservable();

  // private viewChangedSubject = new BehaviorSubject<InventoryView>(
  //   this.inventoryCardView
  // ); // Cards view is the default

  constructor(private http: HttpClient,
    private orgService: OrganizationsService,
    private endpoints: EndpointsService,
    // private modalService: BsModalService
    ) {

    // this.viewChanged$ = this.viewChangedSubject.asObservable();

    this.endpoints.isReady$.subscribe(() => {
      this.orgService.currentOrganization$
        .filter(org => org.RecordId !== -1)
        .subscribe((org) => {
          if (org && org.RecordId !== -1) {
            // this.refreshData(true);
          }
        });
    });
  }

  toggleDetails(detailsView: InventoryDetailsView) {
    this.inventoryDetailsView.next(detailsView);
  }

  getInventoryItemById(inventoryId: number): Observable<Inventory> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/inventory/${inventoryId}`))
      .pipe(map((response: CPResponse<Inventory>) => {

        this.selectedInventory = response.data;
        this.selectedInventorySubject.next(this.selectedInventory);

        return response.data;
      })).share();
  }

  getScanMetrics(vehicleId: number): Observable<ScanMetrics> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v2/inventory/scanmetrics/${vehicleId}`))
      .pipe(map((response: CPResponse<ScanMetrics>) => {
        return response.data;
      }));
  }

  updateInventoryItem(inventory: Inventory): Observable<unknown> {
    return this.http.put(this.endpoints.build(CP_UNISON_API, '/v1/inventory'), inventory)
      .pipe(map((response: CPResponse<unknown>) => {
        return response && response.data;
      })).share();
  }

  deleteInventoryItem(inventoryItem: InventoryDelete): Observable<unknown> {
    return this.http.post(this.endpoints.build(CP_UNISON_API, `/v1/inventory/Delete`), inventoryItem)
      .pipe(map((response: CPResponse<unknown>) => {
        return response && response.data;
      }));
  }

  activateInventoryItem(inventory: Inventory, reason: string): Observable<unknown> {
    const activateObject = {
      'InventoryId': inventory.InventoryId,
      'ActivatedReason': reason,
      'OrganizationId': this.orgService.currentOrganization.RecordId
    };
    return this.http.put(this.endpoints.build(CP_UNISON_API, `/v1/inventory/Activate`), activateObject)
      .pipe(map((response: CPResponse<unknown>) => {
        return response.data || null;
      }));
  }

  updateLastLabelDate(inventoryId: number): Observable<unknown> {
    const updateLabelObject = {
      'InventoryId': inventoryId,
      'OrganizationId': this.orgService.currentOrganization.RecordId
    };
    return this.http.put(this.endpoints.build(CP_UNISON_API, `/v1/inventory/UpdateLastLabelDate`), updateLabelObject)
      .pipe(map((response: CPResponse<unknown>) => {
        return response.data || null;
      }));
  }

  moveInventoryItem(inventory: Inventory): Observable<unknown> {
    return this.http.put(this.endpoints.build(CP_UNISON_API, '/v1/inventory/move'), inventory); //no response from this API call
  }


  updateInventoryTagStatus(status: TagStatus, inventoryTag: InventoryTag): Observable<unknown> {
    const updateTagStatusObject = {
      'RecordId': inventoryTag.RecordId,
      'InventoryId': inventoryTag.InventoryId,
      'Status': status,
      'Label': inventoryTag.Label,
      'Color': inventoryTag.Color,
      'OrganizationId': this.orgService.currentOrganization.RecordId
    };
    return this.http.put(this.endpoints.build(CP_UNISON_API, '/v1/tags/UpdateStatus'), updateTagStatusObject);
  }

  syncInventoryDMS(inventoryId: number): Observable<unknown> {
    const syncInventoryObject = {
      'data': inventoryId
    };
    return this.http.put(this.endpoints.build(CP_UNISON_API, '/v1/Integration/SyncDMS'), syncInventoryObject)
      .pipe(map((response: CPResponse<Inventory>) => {
        return response.data || null;
      })).share();
  }

  upsertInventoryDms(obj): Observable<Inventory> {
    return this.http.put(this.endpoints.build(CP_UNISON_API, '/v1/Integration/UpsertDms'), obj)
      .pipe(map((response: CPResponse<Inventory>) => {
        return response.data;
      })).share();
  }

  quickSearchByVIN(vin: string): Observable<Inventory[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/Inventory/SearchByVIN/${vin}/${this.orgService.currentOrganization.RecordId}/${10}`))
      .pipe(map((response: CPResponse<Inventory[]>) => {
        return response.data;
      }));
  }

  quickSearchByStock(stock: string): Observable<Inventory[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/Inventory/SearchByStock/${stock}/${this.orgService.currentOrganization.RecordId}/${10}`))
      .pipe(map((response: CPResponse<Inventory[]>) => {
        return response.data;
      }));
  }

  // changeView(view: InventoryView): Observable<InventoryView> {
  //   this.viewChangedSubject.next(view);
  //   return this.viewChanged$;
  // }

  // showExternalInventoryModal(item: Inventory): BsModalRef {

  //   if (!item.ExternalStatus) {
  //     return null;
  //   }

  //   return this.modalService.show(InventoryModalExternalComponent, {
  //     initialState: { item },
  //   });
  // }
}
