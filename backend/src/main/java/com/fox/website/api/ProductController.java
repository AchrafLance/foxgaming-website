package com.fox.website.api;

import com.fox.website.models.Product;
import com.fox.website.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;


    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId){
        try{
            return ResponseEntity.ok().body(productService.getProduct(productId));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            return ResponseEntity.ok(productService.getAllProducts());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
