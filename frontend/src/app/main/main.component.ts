import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../services/cart-service';
import { NgxSpinnerService } from 'ngx-spinner';
import { OwlOptions } from 'ngx-owl-carousel-o';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {
  constructor(
    private activatedRoute: ActivatedRoute,
    private cartService: CartService,
    private ngxSpinner: NgxSpinnerService,
  ) {}

  carouselOptions: OwlOptions = {
    loop: true,
    mouseDrag: true,
    touchDrag: true,
    pullDrag: true,
    navSpeed: 600,
  };

  ngOnInit(): void {
    // this.activatedRoute.data.subscribe((data: any) => {
    //   if (data) {
    //     this.ngxSpinner.show();
    //     this.cartService.cartItems = data.cartItems;
    //     this.cartService.cartCount.next(data.cartItems.length);
    //   }
    // });
  }
}
