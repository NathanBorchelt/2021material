import { SimpleDealership } from './SimpleDealership';

export interface DealershipAdminView extends SimpleDealership {
  OrganizationId: number;
  Address: string;
  City: string;
  State: string;
  Zip: string;
  Phone: string;
  DealerCode: string;
  PrinterGroupId: number;
  TimeZone: string;
  UsesAdvancedTags: boolean;
  Locations: string[];
  UserIds: number[];
  CanBeDeleted: boolean;
  Latitude?: number;
  Longitude?: number;
  Radius?: number;
}
