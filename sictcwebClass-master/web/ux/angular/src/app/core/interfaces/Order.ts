import { Product } from './Product';
import { SimpleDealership } from './SimpleDealership';

export interface Order {
    RecordId: number;
    UserId: number;
    InvoiceNumber: string;
    Email: string;
    MobilePhone: string;
    DealershipName: string;
    ShippingAttention: string;
    ShippingAddress1: string;
    ShippingAddress2: string;
    ShippingCity: string;
    ShippingState: string;
    ShippingZIP: string;
    ShippingCountry: string;
    ShippingMethod: string;
    ShippingCost: number;
    ShippingNotes: string;
    BillingAddress1: string;
    BillingAddress2: string;
    BillingCity: string;
    BillingState: string;
    BillingZIP: string;
    BillingCountry: string;
    Subtotal: number;
    ZipTaxId: string;
    TaxRate: number;
    Taxes: number;
    Total: number;
    CreatedDate: string;
    ConfirmedDate: string;
    StripePaymentIntentId: string;
    OrderProducts: OrderProduct[];
    OrderFulfillers: OrderFulfiller[];
    OrderComments: OrderComment[];
    OrderJournals: OrderJournal[];
    Dealership: SimpleDealership;
    OrderTimeline?: OrderTimelineWrapper[];
}

export interface OrderAdminView {
    RecordId: number;
    InvoiceNumber: string;
    ConfirmedDate: string;
    DealershipId: number;
    DealershipName: string;
    FulfillmentStatus: string;
    Total: number;
}

export interface OrderProduct {
    RecordId: number;
    OrderId: number;
    ProductId: number;
    Quantity: number;
    Price: number;
    Product: Product;
}

export interface OrderFulfiller {
    RecordId: number;
    OrderId: number;
    FulfillerId: number;
    Status: string;
    Fulfiller: Fulfiller;
}

export interface OrderTimelineWrapper {
    CreatedDate: string;
    Data: (OrderJournal|OrderComment)[];
}

export interface OrderComment {
    RecordId: number;
    OrderId: number;
    UserId: number;
    Comment: string;
    CreatedDate: string;
    UserEmail: string;
    OrganizationId: number;
    OrganizationName: string;
    TrackingLinks: TrackingLink[];
}

export interface TrackingLink {
    URL: string;
    TrackingNumber: string;
}
export interface OrderJournal {
    RecordId: number;
    OrderId: number;
    UserId: number;
    Class: string;
    Note: string;
    Data: any;
    CreatedDate: string;
    UserEmail: string;
    OrganizationId: number;
    OrganizationName: string;
}

export interface Fulfiller {
    RecordId: number;
    Name: string;
    SharedLabel: string;
}

export interface CreateOrderRequest {
    ExpectedTotal: number;
    Email: string;
    MobilePhone: string;
    DealershipId: number;
    ShippingAttention: string;
    ShippingAddress1: string;
    ShippingAddress2: string;
    ShippingCity: string;
    ShippingState: string;
    ShippingZIP: string;
    ShippingCountry: string;
    ShippingNotes: string;
    BillingAddress1: string;
    BillingAddress2: string;
    BillingCity: string;
    BillingState: string;
    BillingZIP: string;
    BillingCountry: string;
    Products: CreateOrderProductRequest[];
}

export interface CreateOrderRespone {
    StripeIntentSecret: string;
    Order: Order;
}

export interface OrderAddress {
    DealershipName: string;
    Attention: string;
    Address1: string;
    Address2: string;
    City: string;
    State: string;
    ZIP: string;
    Country: string;
}

export interface CreateOrderComment {
    OrderId: number;
    Comment: string;

}

export interface CreateOrderProductRequest {
    ProductId: number;
    Quantity: number;
    OptionId: number;
}

export interface ConfirmOrderResponse {
    Order: Order;
}

export interface OrdersReportRequest {
    DealershipIds: number[];
    StartDate: Date;
    EndDate: Date;
}
