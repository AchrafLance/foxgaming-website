import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductInfo } from 'src/app/models/productInfo';
import { ProductService } from 'src/app/services/product-service';
import { WishlistService } from 'src/app/services/wishlist-service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  productList: ProductInfo[];  


  constructor( private route: Router,  
    private productService: ProductService,
    private wishlistService: WishlistService, 
    private toastrSerivce: ToastrService) {  }

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe((data:ProductInfo[]) => {
      if(data){
        this.productList = data; 
        console.log(this.productList);
      }
    })
    console.log(this.productList);
  }

  onVisiteProduct(product:ProductInfo){
    this.route.navigate(['shop/product/', product.productId]); 
  }

  addToWishlist(product: ProductInfo){
    console.log(product);
    this.wishlistService.addItemToWishlist(product).subscribe(data =>{
      if(data){
        this.wishlistService.wishlistItems.push(product);
        this.toastrSerivce.success("item added to wishlist", "Success")
      }
    })

  }

}
