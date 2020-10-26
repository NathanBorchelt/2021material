import { Injectable } from "@angular/core";
import { Product } from "../../core/interfaces/Product";
import { Observable, BehaviorSubject } from "rxjs";
import * as _ from "lodash";


@Injectable({ providedIn: "root" })
export class CartService {
  private cart: Product[] = [];
  private cartSubject: BehaviorSubject<Product[]> = new BehaviorSubject<
    Product[]
  >(this.cart);
  cart$: Observable<Product[]> = this.cartSubject.asObservable();

  constructor() {
    this.cart = this.getLocalCart();
    this.cartSubject.next(this.cart);
  }

  getCartItem(id: number) {
    const index = this.cart.findIndex((x) => x.RecordId === id);
    const value = this.cart[index];
    return index !== -1 ? { index: index, value: value } : null;
  }

  getCartOptionItem(id: number) {
    const index = this.cart.findIndex((x) => x.OptionId === id);
    const value = this.cart[index];
    return index !== -1 ? { index: index, value: value } : null;
  }

  upsertCartItem(item: Product, cumulative: boolean = false) {
    item = _.cloneDeep(item);

    if (item.ProductOptions.length > 0) {
      let optionIndex = item.ProductOptions.findIndex(
        (x) => x.RecordId === item.OptionId
      );
      item.Name = item.ProductOptions[optionIndex].Name;
      item.PictureURL = item.ProductOptions[optionIndex].PictureURL;
      item.Price = item.ProductOptions[optionIndex].Price;
      item.Subtitle = item.ProductOptions[optionIndex].SKU;
      const existing = this.getCartOptionItem(item.OptionId);

      if (existing) {
        item.Quantity += cumulative ? existing.value.Quantity : 0;
        this.cart[existing.index] = item;
      } else {
        this.cart.push(item);
      }
    } else {
      const existing = this.getCartItem(item.RecordId);

      if (existing) {
        item.Quantity += cumulative ? existing.value.Quantity : 0;
        this.cart[existing.index] = item;
      } else {
        this.cart.push(item);
      }
    }

    this.setLocalCart(this.cart);
    this.cartSubject.next(this.cart);
  }

  deleteCartItem(id: number) {
    const existing = this.getCartItem(id);

    if (existing) {
      this.cart.splice(existing.index, 1);
    }

    this.setLocalCart(this.cart);
    this.cartSubject.next(this.cart);
  }

  clearCartItems() {
    this.cart = [];

    this.setLocalCart(this.cart);
    this.cartSubject.next(this.cart);
  }

  getLocalCart(): Product[] {
    return JSON.parse(localStorage.getItem("store_cart")) || [];
  }

  setLocalCart(cart: Product[]) {
    localStorage.setItem("store_cart", JSON.stringify(cart));
  }
}
