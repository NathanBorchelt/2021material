export interface User {
    RecordId: number;
    Role: string;
    Username: string;
    FirstName: string;
    LastName: string;
    Email: string;
    Phone: string;
    TimeZoneOffset: number;
    Extension: number;
    UserDealershipId: number;
    IsScannerUser: string;
    Abilities: number;
    CanViewBeta: boolean;
    CanRescan: boolean;
    BouncedEmail: boolean;
    CanAccessDMS: boolean;
    Dealerships: string[];
    OrganizationId: number;
    CanExportAI: boolean;
    IsEmailVerified: boolean;
    IsBlocked: boolean;
}
