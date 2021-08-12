import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Order } from '../models/order';
import { ProductInfo } from '../models/productInfo';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  cartCountSource: BehaviorSubject<number>;  
  currentCartCount: Observable<number>;  

  

  clickedProduct: ProductInfo; 
  wishlistProducts: ProductInfo[] = []; 
  cardProducts: Order[] = []; 
  
  constructor() {
    this.cartCountSource = new BehaviorSubject<number>(0); 
    this.currentCartCount= this.cartCountSource.asObservable(); 
   }
  
  changeCartCount(count: number){
    this.cartCountSource.next(count); 
  }

}