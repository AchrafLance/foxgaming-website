import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Order } from 'src/app/models/order';
import { ProductInfo } from 'src/app/models/productInfo';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart-service';
import { WishlistService } from 'src/app/services/wishlist-service';
import { ProductService } from 'src/app/services/product-service';
import { ToastrService } from 'ngx-toastr';
import { NgxSpinnerService } from 'ngx-spinner';
import { UtilService } from '../../../services/util-service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css'],
})
export class ProductDetailsComponent implements OnInit {
  currentProduct!: ProductInfo;

  sizeS = false;
  sizeM = false;
  sizeL = false;
  sizeXL = false;
  size2XL = false;
  selectedSize = 'S';
  selectedQuantity = 1;
  currentRoute: any;
  facebookSharer = 'https://www.facebook.com/sharer/sharer.php?u=';
  twitterSharer = 'https://twitter.com/intent/tweet?text=';
  productId: number;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private cartService: CartService,
    private wishlistService: WishlistService,
    private productService: ProductService,
    private toastrService: ToastrService,
    private utilService: UtilService,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      this.productId = params['id'];
      this.currentProduct = this.utilService.getProductById(this.productId);
    });

    this.productService
      .getOneProduct(this.productId)
      .subscribe((data: ProductInfo) => {
        if (data) {
          this.currentRoute = 'http://www.localhost.com' + this.router.url;
          this.facebookSharer = this.facebookSharer + this.currentRoute;
          this.twitterSharer =
            this.twitterSharer +
            this.currentProduct.productName +
            '\r\n ' +
            this.currentRoute;
        }
      });
  }

  onSelectSizeS() {
    this.sizeS = !this.sizeS;
    this.sizeM = false;
    this.sizeL = false;
    this.sizeXL = false;
    this.size2XL = false;
    this.selectedSize = 'S';
    if (
      !this.size2XL &&
      !this.sizeXL &&
      !this.sizeS &&
      !this.sizeM &&
      !this.sizeL
    ) {
      this.selectedSize = '';
    }
  }

  onSelectSizeM() {
    this.sizeM = !this.sizeM;
    this.sizeS = false;
    this.sizeL = false;
    this.sizeXL = false;
    this.size2XL = false;
    this.selectedSize = 'M';
    if (
      !this.size2XL &&
      !this.sizeXL &&
      !this.sizeS &&
      !this.sizeM &&
      !this.sizeL
    ) {
      this.selectedSize = '';
    }
  }

  onSelectSizeL() {
    this.sizeL = !this.sizeL;
    this.sizeS = false;
    this.sizeM = false;
    this.sizeXL = false;
    this.size2XL = false;
    this.selectedSize = 'L';
    if (
      !this.size2XL &&
      !this.sizeXL &&
      !this.sizeS &&
      !this.sizeM &&
      !this.sizeL
    ) {
      this.selectedSize = '';
    }
  }

  onSelectSizeXL() {
    this.sizeXL = !this.sizeXL;
    this.sizeS = false;
    this.sizeM = false;
    this.sizeL = false;
    this.size2XL = false;
    this.selectedSize = 'XL';
    if (
      !this.size2XL &&
      !this.sizeXL &&
      !this.sizeS &&
      !this.sizeM &&
      !this.sizeL
    ) {
      this.selectedSize = '';
    }
  }

  onSelectSize2XL() {
    this.size2XL = !this.size2XL;
    this.sizeS = false;
    this.sizeM = false;
    this.sizeL = false;
    this.sizeXL = false;
    this.selectedSize = '2XL';
    if (
      !this.size2XL &&
      !this.sizeXL &&
      !this.sizeS &&
      !this.sizeM &&
      !this.sizeL
    ) {
      this.selectedSize = '';
    }
  }

  onDecrementQty() {
    if (this.selectedQuantity >= 2) {
      this.selectedQuantity--;
    }
  }

  onIncrementQty() {
    if (this.selectedQuantity < 20) {
      this.selectedQuantity++;
    }
  }

  onAddToCard(product: ProductInfo) {
    let order = new Order();
    order.quantity = this.selectedQuantity;
    order.size = this.selectedSize;
    order.product = product;
    this.cartService.addItemToCart(order).subscribe((data) => {
      if (data) {
        this.cartService.cartItems.push(order);
        let cartCount = this.cartService.cartCount.value;
        this.cartService.cartCount.next(++cartCount);
        this.toastrService.success('item addedd to cart', 'Success');
      }
    });
  }

  onAddToWishList(product: ProductInfo) {
    this.wishlistService.addItemToWishlist(product).subscribe((data) => {
      if (data) {
        this.wishlistService.wishlistItems.push(product);
        this.toastrService.success('item addedd to wishlist', 'Success');
      }
    });
  }
}
