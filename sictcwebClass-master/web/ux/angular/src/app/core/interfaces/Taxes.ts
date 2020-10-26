export interface TaxesRequest {
    ZIP: string;
    City: string;
    Subtotal: number;
    ShippingCost: number;
}

export interface TaxesResponse {
    Subtotal: number;
    ShippingCost: number;
    SalesTax: number;
    Total: number;
}
