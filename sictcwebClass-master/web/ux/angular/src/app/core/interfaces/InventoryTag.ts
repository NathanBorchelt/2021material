export interface InventoryTag {
  RecordId: number;
  TagDefinitionId: number;
  InventoryId: number;
  Color: string;
  Label: string;
  Status: TagStatus;
  Type: string;
  State?: number;
}

export enum TagStatus {
  NONE = -1,
  DELETED = 0,
  ACTIVE = 1,
  INACTIVE = 2,
}
