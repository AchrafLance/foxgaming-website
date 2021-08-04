import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Order } from 'src/app/models/order';
import { ProductInfo } from 'src/app/models/productInfo';
import { ShopService } from 'src/app/services/shop.service';
import { product, products } from "../../../mockdata";
import { Router} from "@angular/router"
@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  currentProduct: ProductInfo; 

  sizeS = false; 
  sizeM = false; 
  sizeL = false; 
  sizeXL = false; 
  size2XL = false; 
  selectedSize: string; 
  selectedQuantity = 1;
  currentRoute:any; 
  facebookSharer = "https://www.facebook.com/sharer/sharer.php?u="
  twitterSharer = "https://twitter.com/intent/tweet?text="

  constructor( private route: ActivatedRoute, 
    private shopService: ShopService,
    private router: Router) { }

  ngOnInit(): void {
    // this.currentProduct = this.shopService.clickedProduct
    this.currentProduct =product; 
    this.currentRoute = "http://www.localhost.com" + this.router.url; 
    this.facebookSharer =  this.facebookSharer +   this.currentRoute 
    this.twitterSharer = this.twitterSharer + product.productName + "\r\n " + this.currentRoute; 
    
    console.log(this.facebookSharer)

    };

    onSelectSizeS(){
    this.sizeS = !this.sizeS;
    this.sizeM = false;
    this.sizeL = false;
    this.sizeXL = false;
    this.size2XL = false;
    this.selectedSize = "S"; 
    if(!this.size2XL && !this.sizeXL && !this.sizeS && !this.sizeM && !this.sizeL){
      this.selectedSize = ""; 
    }
  }

  onSelectSizeM() {
    this.sizeM = !this.sizeM;
    this.sizeS = false;
    this.sizeL = false;
    this.sizeXL = false;
    this.size2XL = false;
    this.selectedSize = "M"
    if(!this.size2XL && !this.sizeXL && !this.sizeS && !this.sizeM && !this.sizeL){
      this.selectedSize = ""; 
    }
  }

  onSelectSizeL() {
    this.sizeL = !this.sizeL;
    this.sizeS = false;
    this.sizeM = false;
    this.sizeXL = false;
    this.size2XL = false;
    this.selectedSize = "L"
    if(!this.size2XL && !this.sizeXL && !this.sizeS && !this.sizeM && !this.sizeL){
      this.selectedSize = ""; 
    }
  }

  onSelectSizeXL() {
    this.sizeXL = !this.sizeXL;
    this.sizeS = false;
    this.sizeM = false;
    this.sizeL = false;
    this.size2XL = false;
    this.selectedSize = "XL"
    if(!this.size2XL && !this.sizeXL && !this.sizeS && !this.sizeM && !this.sizeL){
      this.selectedSize = ""; 
    }
  }

  onSelectSize2XL() {
    this.size2XL = !this.size2XL
    this.sizeS = false;
    this.sizeM = false;
    this.sizeL = false;
    this.sizeXL = false;
    this.selectedSize = "2XL"
    if(!this.size2XL && !this.sizeXL && !this.sizeS && !this.sizeM && !this.sizeL){
      this.selectedSize = ""; 
    }
  }

  onDecrementQty(){
    if(this.selectedQuantity >= 2){
      this.selectedQuantity--; 
    }
  }

  onIncrementQty(){
    if(this.selectedQuantity < 20){
    this.selectedQuantity++; 
    }
  }

  onAddToCard(product: ProductInfo){
    let order = new Order(); 
    order.orderID = 54; 
    order.quantity = this.selectedQuantity; 
    order.size = this.selectedSize;
    order.product = product; 
    this.shopService.cardProducts.push(order);
    console.log(order);
  }

  onAddToWishList(product: ProductInfo){
    this.shopService.wishlistProducts.push(product);
  }

}
