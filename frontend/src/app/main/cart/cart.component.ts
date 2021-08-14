import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from 'src/app/models/order';
import { ProductInfo } from 'src/app/models/productInfo';
import { CartService } from 'src/app/services/cart-service';
import { ShopService } from 'src/app/services/shop.service';
import { WishlistService } from 'src/app/services/wishlist-service';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartList: Order[] = []; 
  wishList : ProductInfo[] =[]; 

  constructor( private shopService: ShopService, 
    private cartService:CartService, 
    private wishlistService: WishlistService,
    private router: Router) { }

  ngOnInit(): void {
    // this.cartList = this.cartService.cartItems;
    this.cartService.getAllCartItems().subscribe(data => {
      if(data){
        this.cartList = data; 
        this.cartService.cartCount.next(this.cartList.length); 
      }
    })
   
    
    this.wishlistService.getWishlistItems().subscribe(data => {
      if(data){
        this.wishList = data; 
      }
    })
  }


  onRemoveOrder(order: Order) {
    this.cartService.deleteItemrFromCart(order).subscribe(data => {
      if(data){
        const index = this.cartList.indexOf(order);
         if (index > -1) {
         this.cartList.splice(index, 1);
         this.cartService.cartCount.next(this.cartList.length); 
    }}}) 
  }

  onRemoveItem(product: ProductInfo){
    this.wishlistService.deleteItemFromWishlist(product).subscribe(data => {
      if(data){
        const index = this.wishList.indexOf(product);
        if (index > -1) {
        this.wishList.splice(index, 1);
      }
    }})
  }

  addToCart(productId: number){
    this.router.navigate(["/shop/product/", productId])
  }

}
