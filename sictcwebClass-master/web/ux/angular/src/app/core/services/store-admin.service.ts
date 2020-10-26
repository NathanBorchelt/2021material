import { Injectable } from '@angular/core';
import { Observable, Observer, BehaviorSubject, Subject, ReplaySubject } from 'rxjs';
import * as _ from 'lodash';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { EndpointsService, CP_UNISON_API } from './endpoints.service';
import { map } from 'rxjs/operators';
import { CPResponse } from '../interfaces/CPResponse';
import {
  Order, OrderAdminView, OrderTimelineWrapper, OrderComment, OrderJournal,
  OrderProduct, OrderFulfiller, CreateOrderComment
} from '../interfaces/Order';
import { KitFulfiller } from '../interfaces/Product';
import * as moment from 'moment';
import * as fuzzysort from 'fuzzysort';

export interface OrderSortColumnOption {
  FriendlyName: string;
  Key: 'InvoiceNumber' | 'ConfirmedDate' | 'FulfillmentStatus';
}

export interface OrderSort {
  SortColumn: OrderSortColumnOption;
  SortDirection: 'ASC' | 'DESC';
}

@Injectable({ providedIn: 'root' })
export class StoreAdminService {

  orders: OrderAdminView[] = [];
  filteredOrders: OrderAdminView[] = [];
  filteredOrdersSubject: Subject<OrderAdminView[]> = new Subject<OrderAdminView[]>();

  orderSortOptions: OrderSortColumnOption[] = [
    { FriendlyName: 'Order Number', Key: 'InvoiceNumber' },
    { FriendlyName: 'Date', Key: 'ConfirmedDate' },
    { FriendlyName: 'Status', Key: 'FulfillmentStatus' }
  ];

  private orderSort: OrderSort = { SortColumn: this.orderSortOptions[0], SortDirection: 'DESC' };
  private orderSortSubject: BehaviorSubject<OrderSort> = new BehaviorSubject(this.orderSort);

  constructor(private http: HttpClient,
    private endpoints: EndpointsService) {
  }

  getOrders(refresh?: boolean): Observable<any> {
    if (this.orders.length === 0 || refresh) {
      return this.http.get(this.endpoints.build(CP_UNISON_API, '/v1/WebStoreAdmin/Orders'), { observe: 'response' })
        .pipe(map((response: HttpResponse<CPResponse<OrderAdminView[]>>) => {
          this.orders = _.cloneDeep(response.body.data);
          this.filterOrders('');
        }));
    } else {
      return new Observable(() => {
        setTimeout(() => { this.filteredOrdersSubject.next(this.filteredOrders); }, 0);
      });
    }
  }

  convertToCSV(objArray) {
    const array = typeof objArray !== 'object' ? JSON.parse(objArray) : objArray;
    let str = '';

    for (let i = 0; i < array.length; i++) {
      let line = '';
      for (var index in array[i]) {
        if (line != '') line += ','
        line += array[i][index];
      }

      str += `${line}\r\n`;
    }

    return str;
  }

  exportCSVFile(headers: OrderCSVFormat, items: OrderCSVFormat[], fileTitle: string) {
    if (headers) {
      items.unshift(headers);
    }

    // Convert Object to JSON
    const jsonObject = JSON.stringify(items);

    const csv = this.convertToCSV(jsonObject);

    const exportedFilenmae = fileTitle + '.csv' || 'export.csv';

    const blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
    if (navigator.msSaveBlob) { // IE 10+
      navigator.msSaveBlob(blob, exportedFilenmae);
    } else {
      const link = document.createElement('a');
      if (link.download !== undefined) { // feature detection
        // Browsers that support HTML5 download attribute
        const url = URL.createObjectURL(blob);
        link.setAttribute('href', url);
        link.setAttribute('download', exportedFilenmae);
        link.style.visibility = 'hidden';
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
      }
    }
  }


  compareValues(key: string, order: string) {
    return function innerSort(a, b) {
      if (!a.hasOwnProperty(key) || !b.hasOwnProperty(key)) {
        // property doesn't exist on either object
        return 0;
      }

      const varA = (typeof a[key] === 'string')
        ? a[key].toUpperCase() : a[key];
      const varB = (typeof b[key] === 'string')
        ? b[key].toUpperCase() : b[key];

      let comparison = 0;
      if (varA > varB) {
        comparison = 1;
      } else if (varA < varB) {
        comparison = -1;
      }
      return (
        (order === 'DESC') ? (comparison * -1) : comparison
      );
    };
  }

  getOrderSort(): Observable<OrderSort> {
    return this.orderSortSubject.asObservable();
  }


  setOrderSort(orderSort: OrderSort) {
    this.orderSort = orderSort;
    this.orderSortSubject.next(orderSort);

    this.filteredOrders.sort(this.compareValues(orderSort.SortColumn.Key, orderSort.SortDirection));
    this.filteredOrdersSubject.next(this.filteredOrders);
  }


  filterOrders(queryString: string) {
    if (queryString) {
      this.filteredOrders = fuzzysort.go(queryString, this.orders,
        {
          keys: ['InvoiceNumber', 'DealershipName', 'FulfillmentStatus'],
          allowTypo: false, threshold: -1000
        }).map((a: Fuzzysort.KeysResult<OrderAdminView>) => a.obj);
    } else {
      this.filteredOrders = this.orders;
    }
    this.filteredOrdersSubject.next(this.filteredOrders);
  }

  private canGoBack(currentIndex: number) {
    if (currentIndex === 0) {
      return false;
    }
    return true;
  }

  private canGoForward(currentIndex: number, orders: OrderAdminView[]) {
    if (currentIndex + 1 === orders.length) {
      return false;
    }
    return true;
  }

  getCurrentOrderNavigation(orderId: number) {

    const index = this.filteredOrders.findIndex((order: OrderAdminView) => order.RecordId === orderId);

    return {
      IdToRouteTo: orderId,
      CanGoBack: this.canGoBack(index),
      CanGoForward: this.canGoForward(index, this.filteredOrders)
    };

  }

  getNextOrderNavigation(orderId: number, value: number) {

    const orderNavigator: OrderNavigator = {
      IdToRouteTo: -1,
      CanGoBack: true,
      CanGoForward: true
    };

    if (orderId) {
      const index = this.filteredOrders.findIndex((order: OrderAdminView) => order.RecordId === orderId);
      const newIndex = index + value;

      if (newIndex > -1) {
        orderNavigator.IdToRouteTo = this.orders[newIndex].RecordId;
        orderNavigator.CanGoBack = this.canGoBack(newIndex);
        orderNavigator.CanGoForward = this.canGoForward(newIndex, this.filteredOrders);
      }
    }

    return orderNavigator;
  }

  getOrder(id: number): Observable<Order> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/WebStoreAdmin/Orders/${id}`), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<Order>>) => {
        const order = response.body.data;

        order.OrderTimeline = this.processTimeline(order);
        this.processProducts(order);

        return order;
      }));
  }

  processTimeline(order: Order, localRefresh?: boolean) {
    order.OrderComments.forEach((comment: OrderComment) => {
      comment['CreatedDateParsed'] = moment(comment.CreatedDate);
    });

    if (!localRefresh) {
      order.OrderJournals.forEach((journal: OrderJournal) => {
        journal['CreatedDateParsed'] = moment(journal.CreatedDate);
        journal.Data = journal.Data ? JSON.parse(journal.Data) : null;
      });
    }


    const timeline = [...order.OrderComments, ...order.OrderJournals];
    timeline.sort((a, b) => (<moment.Moment>b['CreatedDateParsed']).diff(a['CreatedDateParsed']));

    const timelineData = timeline.reduce((timelineData, timelineItem) => {
      const date = timelineItem.CreatedDate.split('T')[0];
      if (!timelineData[date]) {
        timelineData[date] = [];
      }
      timelineData[date].push(timelineItem);
      return timelineData;
    }, {});

    // Edit: to add it in the array format instead
    const orderTimeLineWrapper: OrderTimelineWrapper[] = Object.keys(timelineData).map((CreatedDate: string) => {
      return {
        CreatedDate: moment(CreatedDate).format('MMMM D'),
        Data: timelineData[CreatedDate]
      };
    });

    return orderTimeLineWrapper;
  }

  private processProducts(order: Order) {
    order.OrderProducts.forEach((orderProduct: OrderProduct) => {

      const product = orderProduct.Product;

      const orderFulfiller = order.OrderFulfillers.find((fulfiller: OrderFulfiller) => fulfiller.FulfillerId === product.FulfillerId);
      product.Fulfiller = orderFulfiller && orderFulfiller.Fulfiller;

      const grouped = _.groupBy(product.KitProducts, x => x.Product.FulfillerId);
      product.KitFulfillers = Object.keys(grouped).map((fulfillerId) => {
        const _orderFulfiller = order.OrderFulfillers.find((x: OrderFulfiller) => x.FulfillerId === parseInt(fulfillerId));
        return <KitFulfiller>{
          Fulfiller: _orderFulfiller && _orderFulfiller.Fulfiller,
          KitProducts: grouped[fulfillerId]
        };
      });
    });
  }

  setFulfillerStatus(fulfillerId: number, status: string) {
    return this.http.patch(this.endpoints.build(CP_UNISON_API, `/v1/WebStoreAdmin/OrderFulfillers/${fulfillerId}/SetStatus/${status}`),
      { observe: 'response' });
  }

  postComment(request: CreateOrderComment): Observable<OrderComment> {
    return this.http.post(this.endpoints.build(CP_UNISON_API, `/v1/WebStoreAdmin/Comments/`), request, { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<OrderComment>>) => {
        return response.body.data;
      }));
  }

  deleteComment(commentId: number) {
    return this.http.delete(this.endpoints.build(CP_UNISON_API, `/v1/WebStoreAdmin/Comments/${commentId}`))
      .pipe(map((response: HttpResponse<CPResponse<any>>) => {
      }));
  }
}

export interface OrderCSVFormat {
  InvoiceNumber: string;
  ConfirmedDate: string;
  DealershipName: string;
  FulfillmentStatus: string;
  Total: number | string;
}

export interface OrderNavigator {
  CanGoBack: boolean;
  CanGoForward: boolean;
  IdToRouteTo: number;
}
