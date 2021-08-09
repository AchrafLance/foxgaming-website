package com.fox.website.api;

import com.fox.website.models.Product;
import com.fox.website.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        try{
            return ResponseEntity.ok(productService.addProduct(product));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

}
