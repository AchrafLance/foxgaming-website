import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../services/cart-service';
import { UtilService } from '../services/util-service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  carouselBg:boolean; 

  constructor(private activatedRoute: ActivatedRoute,private cartService:CartService, 
    private utilService:UtilService) { }

  ngOnInit(): void {
    this.utilService.carouselBg.subscribe(data => {
      this.carouselBg = data;
      console.log(this.carouselBg)
    })

    this.activatedRoute.data.subscribe((data: any) =>{
      this.cartService.cartItems = data.cartItems; 
      this.cartService.cartCount.next(data.cartItems.length); 
    })

  }

}
