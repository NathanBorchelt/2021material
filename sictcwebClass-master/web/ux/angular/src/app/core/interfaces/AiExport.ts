import { Moment } from 'moment';

export interface AiExportFilter {
    DealershipId: number;
    ScanStartDate: string;
    ScanEndDate: string;
    IncludeMobileScans: boolean;
    ScannerUserIds: number[];
}

export interface AiExportInventory {
    DealershipId: number;
    VIN: string;
    ScannedDate: string;
    ScannedDateParsed: Moment;
    Latitude: number;
    Longitude: number;
    ScanGrade: number;
    ScannerUserId: number;
    ScannerUserFirstName: string;
    ScannerUserLastName: string;
}

export interface AiExportDealership {
    RecordId: number;
    Name: string;
}

export interface AiScannerUser {
    RecordId: number;
    FullName: string;
    ScanCount: number;
    Label: string;
}