import { Fulfiller, OrderFulfiller, CreateOrderProductRequest } from './Order';

export interface Product {
    RecordId: number;
    SKU: string;
    Name: string;
    Subtitle: string;
    Info: string;
    Price: number;
    PictureURL: string;
    CreatedDate: Date;
    DeletedDate: Date;
    OptionId: number;
    Quantity?: number;
    IsKit: boolean;
    KitProducts: KitProduct[];
    KitFulfillers?: KitFulfiller[];
    FulfillerId: number;
    Fulfiller: Fulfiller;
    ProductOptions:ProductOption[];
  }

  export interface KitProduct {
    RecordId: number;
    KitId: number;
    ProductId: number;
    Quantity: number;
    Product: Product;
  }



  export interface ProductOption {
    RecordId: number,
    Name: string,
    SKU: string,
    PictureURL: string,
    FulfillerId: number,
    Price: number,
    HexValue: string,
    ProductId: number
  }

export interface KitFulfiller {
  Fulfiller: Fulfiller;
  KitProducts: KitProduct[];
}