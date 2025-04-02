import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";
import {API_URL} from "src/environments/environment";
import {Order} from "../models/order";
import {AuthService} from "./auth-service";


@Injectable({
  providedIn: "root"
})
export class CartService {

  CART_URL = `${API_URL}/cart`;
  cartCount: BehaviorSubject<number>;
  cartItems: Order[] = [];

  constructor(private authService: AuthService, private http: HttpClient) {
    this.cartCount = new BehaviorSubject<number>(0);
  }

  getAllCartItems() {
    return this.http.get<Order[]>(this.CART_URL);
  }

  addItemToCart(order: Order) {
    console.log(this.authService.accessToken);
    let requestBody = new Object();
    requestBody["quantity"] = order.quantity;
    requestBody["size"] = order.size;
    requestBody["productId"] = order.product.productId;
    return this.http.post(this.CART_URL, requestBody);

  }

  deleteItemrFromCart(order: Order) {
    let url = this.CART_URL + `/${order.orderId}`;
    return this.http.delete(url);
  }


}

