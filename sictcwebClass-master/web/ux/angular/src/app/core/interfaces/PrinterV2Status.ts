
export type PrinterV2InternalStatus = 'READY'|'ERROR'|'OFFLINE';

export class PrinterV2Status {
  status: PrinterV2InternalStatus;
  reasons: string[];

  constructor(status: PrinterV2InternalStatus, reasons: string[]) {
    this.status = status;
    this.reasons = reasons;
  }

  isReady(): boolean {
    return this.status === 'READY';
  }
}
