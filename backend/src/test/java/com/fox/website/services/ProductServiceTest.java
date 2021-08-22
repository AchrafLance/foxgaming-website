package com.fox.website.services;

import com.fox.website.models.Product;
import com.fox.website.repositories.ProductRepository;
import com.fox.website.services.Impl.ProductServiceImpl;
import com.fox.website.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl underTest;

    @Test
    void canGetProduct() {
        // ARRANGE
        when(productRepository.findById(any(Long.class)))
                        .thenReturn(Optional.of(new Product()));
        // ACT
        Product expected = underTest.getProduct(Long.valueOf(1));

        // ASSERT
        verify(productRepository).findById(any(Long.class));
        assertThat(expected).isInstanceOf(Product.class);
    }

    @Test
    void canGetAllProducts() {
        // ARRANGE
        when(productRepository.findAll()).thenReturn(new ArrayList<Product>());

        // ACT
        List<Product> expected = underTest.getAllProducts();

        // ASSERT
        verify(productRepository).findAll();
        assertThat(expected).isInstanceOf(List.class);
    }
}