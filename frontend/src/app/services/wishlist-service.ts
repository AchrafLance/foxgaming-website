import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { API_URL } from "src/environments/environment";
import { ProductInfo } from "../models/productInfo";
import { AuthService } from "./auth-service";

const WISHLIST_URL = `${API_URL}/wishlist`;  

@Injectable({providedIn: "root"})
export class WishlistService{
    wishlistItems: ProductInfo[] = []; 

    constructor(private authService: AuthService, private http: HttpClient){

    }

    getWishlistItems(){
        return this.http.get<ProductInfo[]>(WISHLIST_URL); 
    }

    addItemToWishlist(product: ProductInfo){
        let url = WISHLIST_URL + `/${product.productId}`; 
        return this.http.post(url, {}); 
    }

    deleteItemFromWishlist(product: ProductInfo){
        let url = WISHLIST_URL + `/${product.productId}`; 
        return this.http.delete(url); 
    }
    
}