import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs";
import { Order } from "src/app/models/order";
import { CartService } from "../cart-service";

@Injectable({providedIn:"root"})
export class CartResolver {
    constructor(private cartService: CartService){}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Order[]> | Promise<Order[]> | Order[] {
        return this.cartService.getAllCartItems(); 
}
}