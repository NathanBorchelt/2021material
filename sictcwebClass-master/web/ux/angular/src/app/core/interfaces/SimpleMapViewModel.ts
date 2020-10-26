export interface SimpleMapViewModel {
    InventoryId: number;
    Latitude: number;
    Longitude: number;
    ExternalStatus?: ExternalStatus; //used by cross org search
  }

  export enum ExternalStatus {
    InOtherOrganization = 3,
    UserDoesNotHaveAccess = 7
  }
