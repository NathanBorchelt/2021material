export interface IntegrationLogRequest {
    DealershipId: number;
    StartRange: number;
    EndRange: number;
    ShowSoldExtractLogs: boolean;
    FailedOnlyLogs: boolean;
    VIN: string;
}
