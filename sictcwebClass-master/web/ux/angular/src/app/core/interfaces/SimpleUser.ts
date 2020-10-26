export interface VerySimpleUser {
  RecordId: number;
  FirstName: string;
  LastName: string;
}

export interface SimpleUser extends VerySimpleUser {
  Email: string;
  Phone: string;
  Extension: string;
  Role: string;
  IsScannerUser: string;
  CanViewBeta: boolean;
  CanRescan: boolean;
  BouncedEmail: boolean;
  CanAccessDMS: boolean;
  IsEmailVerified: boolean;
  IsBlocked: boolean;
  CanExportAI: boolean;
}

export interface SimpleUserView extends SimpleUser {
  DealershipIds: number[];
}

export interface UpdateSimpleUser extends SimpleUser {
  AddedDealershipIds: number[];
  DeletedDealershipIds: number[];
}

export interface AddSimpleUser extends SimpleUserView {
  OrganizationId: number;
}
