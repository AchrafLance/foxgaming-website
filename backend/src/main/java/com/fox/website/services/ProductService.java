package com.fox.website.services;

import com.fox.website.models.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(Long productId);
    List<Product> getAllProducts();
}
