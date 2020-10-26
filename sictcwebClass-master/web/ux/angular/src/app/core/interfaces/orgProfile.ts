export interface Data {
    Org: Org;
    DealerList: DealerList[];
}

export interface Org {
    RecordId: number;
    Name: string;
    Type: string;
    SharedLabel: string;
    Address: string;
    City: string;
    State: string;
    Zip: string;
    Phone: string;
    CreatedDate: Date;
    CreatedUser: number;
    DeletedDate?: any;
    DeletedUser?: any;
    LastUpdatedDate: Date;
    LastUpdatedUser: number;
    ScannerOrganizationId: number;
    PrinterOrganizationId: number;
}

export interface DealerList {
    RecordId: number;
    Name: string;
    OrganizationId: number;
    PrinterGroupId?: number;
    ScannerGroupId?: any;
    Address: string;
    City: string;
    State: string;
    Zip: string;
    Phone: string;
    CreatedDate: Date;
    CreatedUser: number;
    DeletedDate?: any;
    DeletedUser?: any;
    LastUpdatedDate: Date;
    LastUpdatedUser: number;
}


