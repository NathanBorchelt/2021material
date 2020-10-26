export interface TagDefinition {
    RecordId: number;
    OrganizationId: number;
    DealershipId: number;
    Color: string;
    Label: string;
    DefaultStatus: number;
    Type: string;
    CreatedDate: Date;
    CreatedUser: number;
    DeletedDate: Date;
    DeletedUser: number;
    LastUpdatedDate: Date;
    LastUpdatedUser: number;
    //-------------------------------------------------------------------------------
    // LOCAL VARIABLES FOR UI
    //-------------------------------------------------------------------------------
    //when adding tags prevents users from re-adding/removing tags that are already active
    Checked?: boolean;
    AlreadyActive?: boolean;
}

export interface BatchTagDefinition {
    TagDefinitionId: number;
    InventoryId: number;
    Status: number;
    OrganizationId: number;
}
  
