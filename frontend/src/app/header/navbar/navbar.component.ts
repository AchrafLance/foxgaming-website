import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth-service';
import { CartService } from 'src/app/services/cart-service';
import { ShopService } from 'src/app/services/shop.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnDestroy {
  itemsInCart: number; 
  showTeamsModal: boolean =false; 
  showShopModal: boolean = false; 
  showMediaModal: boolean = false; 
  showCompanyModal: boolean = false; 
  loggedIn: boolean; 
  subscription1: Subscription; 
  subscription2: Subscription; 

  constructor(private cartService: CartService, private authService:AuthService) { }

  ngOnInit(): void {
    this.subscription1 = this.cartService.cartCount.subscribe(data => {
      this.itemsInCart = data;   
      })

     this.subscription2 = this.authService.loggedIn.subscribe(data => {
       this.loggedIn = data; 
     });
    }
  

  teamsModal(){
    this.showTeamsModal = !this.showTeamsModal; 
    console.log(this.showTeamsModal);
  }

  logout(){
   this.authService.logout(); 
   this.cartService.cartCount.next(0); 

  }

  ngOnDestroy(){
    this.subscription1.unsubscribe(); 
    this.subscription2.unsubscribe(); 

  }
  

}
