import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../services/cart-service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute,private cartService:CartService) { }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe((data: any) =>{
      this.cartService.cartItems = data.cartItems; 
      this.cartService.cartCount.next(data.cartItems.length); 
    })

  }

}
