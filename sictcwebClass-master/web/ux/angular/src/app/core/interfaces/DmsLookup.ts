export interface DmsLookupModel {
    FoundInDMS: boolean;
    Vehicle: DMSVehicle;
}

export interface DMSVehicle {
    AdvertisedPrice: number;
    BaseInvoicePrice: number;
    BaseRetailPrice: number;
    Color: string;
    DmsCallStatus: string;
    InventoryAccount: string;
    InventoryCompany: string;
    LocationName: string;
    Make: string;
    Mileage: number;
    Model: string;
    Status: string;
    Stock: string;
    VIN: string;
    Year: number;
}