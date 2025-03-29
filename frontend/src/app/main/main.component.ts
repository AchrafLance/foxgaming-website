import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartService } from '../services/cart-service';
import { UtilService } from '../services/util-service';
import { NgxSpinnerService } from "ngx-spinner";
import {OwlOptions} from "ngx-owl-carousel-o";


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  carouselBg:boolean;

  constructor(private activatedRoute: ActivatedRoute,private cartService:CartService,
    private utilService:UtilService, private router:Router,
    private ngxSpinner:NgxSpinnerService) { }

  customOptions: OwlOptions = {
    loop: true,
    mouseDrag: false,
    touchDrag: false,
    pullDrag: false,
    dots: false,
    navSpeed: 700,
    navText: ['', ''],
    responsive: {
      0: {
        items: 1
      },
      400: {
        items: 2
      },
      740: {
        items: 3
      },
      940: {
        items: 4
      }
    },
    nav: true
  };

  ngOnInit(): void {
    this.ngxSpinner.show();


    this.utilService.carouselBg.subscribe(data => {
      this.carouselBg = data;
      console.log(this.carouselBg)
    })

    this.activatedRoute.data.subscribe((data: any) =>{
      if(data){
        this.ngxSpinner.show();
        this.cartService.cartItems = data.cartItems;
        this.cartService.cartCount.next(data.cartItems.length);
      }

    }, error =>{
      console.log(error)
    })

    setInterval(()=> {
      this.ngxSpinner.hide();
    }, 1000)

  }

  onShowTeams(){
    this.router.navigate(["/squads"])

  }

}
