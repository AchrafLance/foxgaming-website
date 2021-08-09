package com.fox.website.services.Impl;

import com.fox.website.models.Product;
import com.fox.website.repositories.ProductRepository;
import com.fox.website.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save((product));

    }
}
