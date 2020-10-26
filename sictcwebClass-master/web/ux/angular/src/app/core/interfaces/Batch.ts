import { Inventory } from './Inventory';


export enum BatchAddClassification {
  New = 'N',
  Used = 'U',
  Loaner = 'L'
}

export interface BatchAdd {
    Classification: string;
    DealershipId: number;
    LocationId: number;
    UserEmail: string;
    OrganizationId?: number;
    BatchVehicles: BatchAddInventory[];
}

export interface BatchAddInventory {
    VIN: string;
    Stock: string;
    Color: string;
    StandardColor: string;
    ValidVIN?: boolean;
    ValidStock?: boolean;
}

export interface BatchUpdate {
    InventoryIds: number[];
    Classification: 'N' | 'U' | 'L';
    Year: string;
    Make: string;
    Model: string;
    StandardColor: string;
    Color: string;
    Reason: string;
    OrganizationId?: number;
}

export interface BatchMove {
    InventoryIds: number[];
    DealershipId: number;
    LocationId: number;
    OrganizationId?: number;
}

export interface BatchDelete {
    InventoryIds: number[];
    Reason: string;
    OrganizationId?: number;
}

export interface BatchActivate {
    InventoryIds: number[],
    Reason: string,
    OrganizationId?: number
}

export interface BatchSearchByStocks {
    Stocks: string[];
    OrganizationId?: number;
}

export interface BatchSearchByVIN {
    VINs: string[];
    OrganizationId?: number;
}

export interface BatchSearchResult {
    found: Inventory[];
    notFound: string[];
}

export interface BatchCheckAction {
    InventoryIds: number[];
    Action: BatchAction;
}

export interface BatchCheckActionResult {
    CanPerformAction: boolean;
    Reason: string;
}

export enum BatchAction {
  Edit = 'edit',
  Move = 'move',
  Print = 'print'
}
