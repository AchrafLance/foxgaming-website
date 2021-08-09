import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/order';
import { ProductInfo } from 'src/app/models/productInfo';
import { ShopService } from 'src/app/services/shop.service';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartList: Order[] = []; 
  wishList : ProductInfo[] =[]; 

  constructor( private shopService: ShopService) { }

  ngOnInit(): void {
    this.cartList = this.shopService.cardProducts; 
    this.wishList = this.shopService.wishlistProducts; 
  }

  onRemoveOrder(order: Order) {
    const index = this.cartList.indexOf(order);
    if (index > -1) {
      this.cartList.splice(index, 1);
    }
  }

 
}
