import { ProductInfo } from "./models/productInfo";
import { Order } from "./models/order";

export const products: ProductInfo[] = [
  {
    productId: 'B0001',
    productName: 'FXG Jersey',
    productPrice: 200.00,
    productStock: 96,
    productDescription: 'FXG main gaming jersey',
    productIcon: 'https://personal-images-store.s3.us-east-2.amazonaws.com/fxg-shirt.jpg',
    productStatus: 0,
    categoryType: 0,
    createTime: '2018-03-10T11:44:25.000+0000',
    updateTime: '2018-03-10T11:44:25.000+0000'
  },
  {
    productId: 'B0002',
    productName: "Akashi Jersey",
    productPrice: 250.00,
    productStock: 195,
    productDescription: "FXG Jersey with Akashi's signature",
    productIcon: 'https://personal-images-store.s3.us-east-2.amazonaws.com/akashi-shirt.jpg',
    productStatus: 0,
    categoryType: 0,
    createTime: '2018-03-10T15:35:43.000+0000',
    updateTime: '2018-03-10T15:35:43.000+0000'
  },
  {
    productId: 'B0001',
    productName: "Akazan's Jersey",
    productPrice: 250.00,
    productStock: 96,
    productDescription: "FXG's Jersey with Akazan's signature",
    productIcon: 'https://personal-images-store.s3.us-east-2.amazonaws.com/akazan-shirt.jpg',
    productStatus: 1,
    categoryType: 0,
    createTime: '2018-03-10T11:44:25.000+0000',
    updateTime: '2018-03-10T11:44:25.000+0000'
  }
]

export const product: ProductInfo = {
  productId: 'B0001',
  productName: 'FXG Jersey',
  productPrice: 200.00,
  productStock: 96,
  productDescription: 'FXG main gaming jersey',
  productIcon: 'https://personal-images-store.s3.us-east-2.amazonaws.com/fxg-shirt.jpg',
  productStatus: 0,
  categoryType: 0,
  createTime: '2018-03-10T11:44:25.000+0000',
  updateTime: '2018-03-10T11:44:25.000+0000'
}

export const order: Order = {
    orderID: 12, 
    quantity: 2, 
    size: "M",
    product: {
      productId: 'B0001',
      productName: 'FXG Jersey',
      productPrice: 200.00,
      productStock: 96,
      productDescription: 'FXG main gaming jersey',
      productIcon: 'https://personal-images-store.s3.us-east-2.amazonaws.com/fxg-shirt.jpg',
      productStatus: 0,
      categoryType: 0,
      createTime: '2018-03-10T11:44:25.000+0000',
      updateTime: '2018-03-10T11:44:25.000+0000'
    }
}