import { ProductInfo } from "./productInfo";

export class Order {
    orderId: number; 
    quantity: number; 
    size: string; 
    product: ProductInfo
}