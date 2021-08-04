import { Injectable } from '@angular/core';
import { Order } from '../models/order';
import { ProductInfo } from '../models/productInfo';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  clickedProduct: ProductInfo; 
  wishlistProducts: ProductInfo[] = []; 
  cardProducts: Order[] = []; 
  
  constructor() { }
}
