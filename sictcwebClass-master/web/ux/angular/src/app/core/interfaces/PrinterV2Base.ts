import { PrinterV2ShadowBase } from './PrinterV2ShadowBase';

export interface PrinterV2Base {
  IMEI: string;
  DealershipIds: number[];
  OrganizationId: number;
  Shadow: PrinterV2ShadowBase;
}
