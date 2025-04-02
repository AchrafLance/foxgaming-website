import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ProductInfo } from '../models/productInfo';

@Injectable({
  providedIn: 'root',
})
export class UtilService {
  mockProductList: ProductInfo[] = [
    {
      productId: 1,
      productName: 'FXG SHIRT',
      productPrice: 200,
      productDescription: 'FXG Main Jersey gaming shirt',
      productIcon: 'assets/fxg-shirt.jpg',
    },
    {
      productId: 2,
      productName: 'FXG Akashi',
      productPrice: 200,
      productDescription: 'FXG Akashi Jersey gaming shirt',
      productIcon: 'assets/akashi-shirt.jpg',
    },
    {
      productId: 3,
      productName: 'FXG Akazan',
      productPrice: 200,
      productDescription: 'FXG Akazan Jersey gaming shirt',
      productIcon: 'assets/akazan-shirt.jpg',
    },
  ];

  carouselBg: BehaviorSubject<boolean>; // index-1 for carousel when sidebar opens
  constructor() {
    this.carouselBg = new BehaviorSubject(false);
  }

  getProductById(id: number): ProductInfo | undefined {
    return this.mockProductList.find((product) => product.productId == id);
  }
}
