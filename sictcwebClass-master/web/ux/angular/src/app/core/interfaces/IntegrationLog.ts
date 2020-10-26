export interface IntegrationLog {
    RecordId: number;
    DealershipId: number;
    UserId: number;
    RequestType: string;
    RequestDate: string;
    RequestFileName: string;
    ResponseFileName: string;
    Status: string;
    VIN: string;
    Stock: string;
    IntegratedDealerId: string;
    DealershipName: string;
}
