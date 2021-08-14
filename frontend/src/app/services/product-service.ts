import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { API_URL } from "src/environments/environment";

const PRODUCT_URL = API_URL + "/product"; 

@Injectable({providedIn: "root"})
export class ProductService{
    constructor(private http: HttpClient){

    }

    getOneProduct(productId: number){
        let url = `${PRODUCT_URL}/${productId}`; 
        return this.http.get(url); 

    }

    getAllProducts(){
        return this.http.get(PRODUCT_URL); 
    }

}