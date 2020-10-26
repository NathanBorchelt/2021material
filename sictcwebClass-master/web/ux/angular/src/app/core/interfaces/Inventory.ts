import { InventoryTag } from './InventoryTag';

export interface Inventory {
  Classification: string;
  Color: string;
  StandardColor: string;
  Cost: number;
  DealershipId: number;
  DealershipName: string;
  GpsAccuracy: number;
  InventoryId: number;
  LocationId?: number;
  LocationName: string;
  Make: string;
  Mileage: number;
  Model: string;
  Price: number;
  Stock: string;
  VIN: string;
  Year: number;
  Latitude: number;
  Longitude: number;
  CardState: string;
  OrganizationName: string;
  ScannedDate: string;
  Tags: InventoryTag[];
  VerificationStatus: number;
  IsIntegrated: boolean;
  IntegratedDealerId: number;
  ExternalStatus?: ExternalStatus;
  Notes: string;
  TrimLevel: string;
  Shorthand: string;
}

export enum ExternalStatus {
  InOtherOrganization = 3,
  UserDoesNotHaveAccess = 7
}