import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Order } from '../models/order';
import { ProductInfo } from '../models/productInfo';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  cartCountSource = new BehaviorSubject<number>(0); 
  currentCartCount = this.cartCountSource.asObservable(); 

  

  clickedProduct: ProductInfo; 
  wishlistProducts: ProductInfo[] = []; 
  cardProducts: Order[] = []; 
  
  constructor() { }
  
  changeCartCount(count: number){
    this.cartCountSource.next(count); 
  }

}