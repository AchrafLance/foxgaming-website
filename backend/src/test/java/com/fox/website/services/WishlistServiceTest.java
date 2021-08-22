package com.fox.website.services;

import com.fox.website.models.Product;
import com.fox.website.models.User;
import com.fox.website.models.Wishlist;
import com.fox.website.repositories.ProductRepository;
import com.fox.website.repositories.UserRepository;
import com.fox.website.repositories.WishlistRepository;
import com.fox.website.services.Impl.WishlistServiceImpl;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class WishlistServiceTest {

    @Mock
    WishlistRepository wishlistRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    Principal principal;
    private String nameStub = "Ashraf";
    private Product productStub;
    private User userStub;
    private Wishlist wishlistStub;

    @InjectMocks
    WishlistServiceImpl underTest;

    @BeforeEach
    void setUp() {
        when(principal.getName()).thenReturn(nameStub);
         productStub = new Product();
         userStub = new User();
         wishlistStub = new Wishlist();
    }

    @AfterEach
    void tearDown(){
        productStub = null;
        userStub = null;
        wishlistStub = null;
    }

    @Test
    void canGetWishlistProducts() {
        //ARRANGE
        wishlistStub.setProducts(new HashSet<>());
        userStub.setWishlist(wishlistStub);
        when(userRepository.findByUsername(any(String.class)))
                .thenReturn(Optional.of(userStub));
        //ACT
        Set<Product> expected = underTest.getWishlistProducts(principal);
        //ASSERT
        verify(userRepository).findByUsername(any(String.class));
        assertThat(expected).isNotNull();
    }

    @Test
    void canAddProductToWishlist() {
        //ARRANGE
        wishlistStub.addProduct(productStub);
        userStub.setWishlist(wishlistStub);

        when(productRepository.findById(any()))
                .thenReturn(Optional.of(productStub));
        when(userRepository.findByUsername(any()))
                .thenReturn(Optional.of(userStub));
        when(wishlistRepository.save(any()))
                .thenReturn(wishlistStub);
        //ACT
        Wishlist expected = underTest.addProductToWishlist(
                Long.valueOf(1), principal);

        //ASSERT
        verify(productRepository).findById(any());
        verify(userRepository).findByUsername(any());
        verify(wishlistRepository).save(any());

        assertThat(expected).isNotNull();
    }

    @Test
    void canDeleteProductFromWishlist() {
        //ARRANGE
        wishlistStub.addProduct(productStub);
        userStub.setWishlist(wishlistStub);
        when(productRepository.findById(any()))
                .thenReturn(Optional.of(productStub));
        when(userRepository.findByUsername(any()))
                .thenReturn(Optional.of(userStub));
        when(wishlistRepository.save(any()))
                .thenReturn(wishlistStub);
        //ACT
        Wishlist expected = underTest.deleteProductFromWishlist(Long.valueOf(1), principal);

        //ASSERT
        verify(productRepository).findById(any());
        verify(userRepository).findByUsername(any());
        verify(wishlistRepository).save(any());
    }
}