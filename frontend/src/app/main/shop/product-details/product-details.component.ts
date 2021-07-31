import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductInfo } from 'src/app/models/productInfo';
import { ShopService } from 'src/app/services/shop.service';
import { product } from "../../../mockdata";
@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  currentProduct: ProductInfo; 

  constructor( private route: ActivatedRoute, 
    private shopService: ShopService) { }

  ngOnInit(): void {
    // this.currentProduct = this.shopService.clickedProduct
    this.currentProduct =product; 

    };
  

}
