import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { delay } from 'rxjs/operators';
import { AuthService } from 'src/app/services/auth-service';
import { CartService } from 'src/app/services/cart-service';
import { UtilService } from 'src/app/services/util-service';

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
  displaySideBar:boolean; 

  constructor(private cartService: CartService, private authService:AuthService,
    private utilSerivce: UtilService)
 { }

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

  openSidebar(){
    this.displaySideBar = true, 
    document.querySelector('body').classList.toggle('overflow-off');
    this.utilSerivce.carouselBg.next(true); 
  }

  closeSideBar(){
    this.displaySideBar = false; 
    document.querySelector('body').classList.toggle('overflow-off');
    this.utilSerivce.carouselBg.next(false); 

    setTimeout(()=>{
      this.displaySideBar = undefined; 
    }, 500)
  }
  

}
