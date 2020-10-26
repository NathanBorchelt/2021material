export interface Organization {
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
