import { Injectable } from '@angular/core';
import { ProductInfo } from '../models/productInfo';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  clickedProduct: ProductInfo; 

  constructor() { }
}
