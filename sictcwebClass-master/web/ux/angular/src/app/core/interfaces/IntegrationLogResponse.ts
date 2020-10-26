import { IntegrationLog } from './IntegrationLog';

export interface IntegrationLogResponseWrapper {
    data: IntegrationLogResponse;
}

export interface IntegrationLogResponse {
    TotalCount: number;
    Logs: IntegrationLog[];
}
