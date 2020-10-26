import { SimpleDealership } from './SimpleDealership';

export interface EditDealership extends SimpleDealership {
  Address: string;
  City: string;
  State: string;
  Zip: string;
  Phone: string;
  DealerCode: string;
  PrinterGroupId: number;
  TimeZone: string;
  UsesAdvancedTags: boolean;
  AddedUserIds: number[];
  DeletedUserIds: number[];
}
