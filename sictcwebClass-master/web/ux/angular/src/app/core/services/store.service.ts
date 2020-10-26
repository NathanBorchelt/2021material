import { Injectable } from '@angular/core';
import { Product } from '../../core/interfaces/Product';
import { Observable, Observer } from 'rxjs';
import * as _ from 'lodash';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { EndpointsService, CP_UNISON_API, CP_STORE_EMAIL_API } from './endpoints.service';
import { map } from 'rxjs/operators';
import { CPResponse } from '../interfaces/CPResponse';
import { Order, CreateOrderRequest, OrderAddress, CreateOrderRespone, ConfirmOrderResponse } from '../interfaces/Order';
import { TaxesRequest, TaxesResponse } from '../interfaces/Taxes';
import { APIResult } from '../interfaces/APIResult';
import { ProductImage } from '../../core/interfaces/ProductImage';
import { SavedCard } from '../../core/interfaces/SavedCard';

@Injectable({ providedIn: 'root' })
export class StoreService {

  productImages: ProductImage[] = [];

  constructor(private http: HttpClient,
    private endpoints: EndpointsService) {
  }

  getProducts(orgId: number): Observable<Product[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/WebStore/ProductsByOrg/${orgId}`), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<Product[]>>) => {
        return response.body.data;
      }));
  }

  getOrders(): Observable<Order[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, '/v1/WebStore/Orders'), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<Order[]>>) => {
        return response.body.data;
      }));
  }

  getShippingAddresses(): Observable<OrderAddress[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, '/v1/WebStore/ShippingAddresses'), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<OrderAddress[]>>) => {
        return response.body.data;
      }));
  }

  createOrder(request: CreateOrderRequest): Observable<CreateOrderRespone> {
    return this.http.post(this.endpoints.build(CP_UNISON_API, '/v1/WebStore/Orders'), request, { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<APIResult<CreateOrderRespone>>>) => {
        return response.body.data.result;
      }));
  }

  confirmOrder(paymentIntentId: string): Observable<Order> {
    return this.http.patch(this.endpoints.build(CP_UNISON_API, `/v1/WebStore/Orders/Confirm/${paymentIntentId}`),
      { observe: 'response' })
      .pipe(map((response: CPResponse<APIResult<ConfirmOrderResponse>>) => {
        return response.data.result.Order;
      }));
  }

  getTaxes(request: TaxesRequest) {
    return this.http.post(this.endpoints.build(CP_UNISON_API, `/v1/WebStore/Taxes/`), request,
      { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<APIResult<TaxesResponse>>>) => {
        return response.body.data.result;
      }));
  }

  sendCustomerEmail(order: Order) {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
    const url = this.endpoints.buildSecure(CP_STORE_EMAIL_API, '');
    return this.http.post(url, order, { headers: headers });
  }

  isZipCodeValid(zipCode: string) {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/WebStore/ZipCodeValid/${zipCode}`), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<boolean>>) => {
        return response.body.data;
      }));
  }

  getSavedCards(): Observable<SavedCard[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/WebStore/SavedCards`), { observe: 'response' })
    .pipe(map((response: HttpResponse<CPResponse<SavedCard[]>>) => {
      return response.body.data;
    }));
  }

  deleteSavedCard(paymentMethodId: string) {
    return this.http.delete(this.endpoints.build(CP_UNISON_API, `/v1/WebStore/SavedCards/${paymentMethodId}`), { observe: 'response' });
  }

  getProductImage(key: string) {
      const idx = this.productImages.findIndex((productImage: ProductImage) => productImage.Key === `${key}.png`);
      return `data:image/png;base64,${this.productImages[idx].Data}`;
  }
}

