export interface IntegratedDealer {
    RecordId: number;
    DealershipId: number;
    DealershipName: string;
    DMS: string;
    LinkedDmsId: string;
    AllowDeltaTime: boolean;
    Username: string;
    Password: string;
    ExtractsSoldInventory: boolean;
    LastSoldInventoryExtractDate: Date;
    DeletedDate: Date;
    DeletedUser: number;
    InventoryCompany: string;
    ValidatedDate: Date;
}
