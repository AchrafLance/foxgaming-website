import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductInfo } from 'src/app/models/productInfo';
import { ProductService } from 'src/app/services/product-service';
import { WishlistService } from 'src/app/services/wishlist-service';
import { ToastrService } from 'ngx-toastr';
import { UtilService } from '../../../services/util-service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
})
export class CardComponent implements OnInit {
  productList!: ProductInfo[];

  constructor(
    private route: Router,
    private productService: ProductService,
    private wishlistService: WishlistService,
    private toastrSerivce: ToastrService,
    private utilService: UtilService,
  ) {}

  ngOnInit(): void {
    this.productList = this.utilService.mockProductList;
    this.productService.getAllProducts().subscribe((data: ProductInfo[]) => {
      if (data) {
        this.productList = data;
      }
    });
  }

  onVisitProduct(product: ProductInfo) {
    this.route.navigate(['shop/product/', product.productId]);
  }

  addToWishlist(product: ProductInfo) {
    this.wishlistService.addItemToWishlist(product).subscribe((data) => {
      if (data) {
        this.wishlistService.wishlistItems.push(product);
        this.toastrSerivce.success('item added to wishlist', 'Success');
      }
    });
  }
}
