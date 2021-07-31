import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductInfo } from 'src/app/models/productInfo';
import { products } from "../../../mockdata"; 
import { ShopService } from 'src/app/services/shop.service';
@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  mockProducts: ProductInfo[];  


  constructor( private route: Router,  
    private shopService: ShopService) {  }

  ngOnInit(): void {
    this.mockProducts = products; 
    console.log(this.mockProducts);
  }

  onVisiteProduct(product:ProductInfo){
    this.route.navigate(['shop/product/', product.productId ]); 
    this.shopService.clickedProduct = product; 

  }

}
