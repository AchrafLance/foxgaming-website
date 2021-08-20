import { AfterViewInit,AfterViewChecked, Component, ElementRef, forwardRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Order } from 'src/app/models/order';
import { ProductInfo } from 'src/app/models/productInfo';
import { CartService } from 'src/app/services/cart-service';
import { WishlistService } from 'src/app/services/wishlist-service';
import { NgxSpinnerService } from "ngx-spinner";


declare var paypal; 
const ToEuro = 0.095; 
const ToUSD = 0.11; 

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit   {

  // @ViewChild("paypal", {static: true}) paypalElement: ElementRef; 
  cartList: Order[] = []; 
  wishList : ProductInfo[] =[]; 
  totalToPayInMAD = 0; 
  intervalId; 

  constructor(private cartService: CartService,
    private wishlistService: WishlistService,
    private router: Router,
    private toastrService: ToastrService, 
    private spinnerService: NgxSpinnerService) {
     
  }

  ngOnInit(): void {

    this.spinnerService.show(); 
    this.intervalId = setInterval(() => {
      const elementExists = !!document.getElementById('paypal-btn')
      console.log("elementExist" + elementExists);
      if (elementExists) {
        clearInterval(this.intervalId)
        paypal.Buttons({
          style: {
            color: 'gold',
            size: 'responsive',
            fundingicons: 'true',
            layout: 'horizontal',
            tagline: 'false'

          },
          funding: {
            disallowed: [paypal.FUNDING.CREDIT, paypal.FUNDING.CARD]
          },
          createOrder: (data, actions) => {
            return actions.order.create({
              purchase_units: [{
                description: "FXG Merch",
                amount: {
                  currency_code: "USD",
                  value: this.totalToPayInMAD * ToUSD
                }
              }]
            });
          }, onApprove: async (data, actions) => {
            const order = await actions.order.capture();
            this.toastrService.success("paiment successful")
          }, onError: err => {
            console.log(err);
            this.toastrService.error("something went wrong, please try again")
          }
        }).render("#paypal-btn"); 
        this.spinnerService.hide(); 

      }
    }, 1000)
 

    this.cartService.getAllCartItems().subscribe(data => {
      if(data){
        this.cartList = data; 
        this.cartService.cartCount.next(this.cartList.length); 
        for(let cart of this.cartList){
          this.totalToPayInMAD += cart.product.productPrice*cart.quantity; 
        }
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
         this.totalToPayInMAD -= (order.product.productPrice * order.quantity)
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

  onCheckout(){
    this.toastrService.info("sorry! this feature is not implemented yet", "Info")
    
  }

  onUpdateOrder(order:any){
    this.toastrService.info("sorry! update isn't implemented yet", "info")

  }

}
