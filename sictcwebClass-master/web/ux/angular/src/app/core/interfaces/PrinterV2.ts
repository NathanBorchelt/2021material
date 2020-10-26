import { PrinterV2Base } from './PrinterV2Base';
import { PrinterV2ShadowBase } from './PrinterV2ShadowBase';

export interface PrinterV2 extends PrinterV2Base {
  IMEI: string;
  FriendlyName: string;
  DealershipIds: number[];
  OrganizationId: number;
  Shadow: PrinterV2Shadow;
  Connected: boolean;
}

export interface PrinterV2Shadow extends PrinterV2ShadowBase {
  LastPrintStatus: PrinterV2ShadowStatus;
  ActiveTopics: string[];
  MacPrinted: boolean;
  Firmware: number;
  FriendlyName: string;
  Claimed: boolean;
  TemplateId: string;
}

export interface PrinterV2ShadowStatus {
  Errors: string;
  Warnings: string;
}


export interface SimplePrinterV2 {
  IMEI: string;
  FriendlyName: string;
}