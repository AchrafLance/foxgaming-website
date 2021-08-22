package com.fox.website.services;

import com.fox.website.dtos.OrderDTO;
import com.fox.website.exceptions.AppException;
import com.fox.website.models.*;
import com.fox.website.repositories.CartRepository;
import com.fox.website.repositories.OrderRepository;
import com.fox.website.repositories.ProductRepository;
import com.fox.website.repositories.UserRepository;
import com.fox.website.services.Impl.CartServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;
import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock OrderRepository orderRepository;
    @Mock UserRepository userRepository;
    @Mock ProductRepository productRepository;
    @Mock CartRepository cartRepository;
    @Mock Principal principal;

    private String nameStub = "Ashraf";
    private User userStub;
    private Cart cartStub;
    private Wishlist wishlistStub;
    private Product productStub;
    private Order orderStub;
    private OrderDTO orderDTO;

    @InjectMocks
    CartServiceImpl underTest;

    @BeforeEach
    void setUp() {

         cartStub = new Cart(Long.valueOf(1), new HashSet<>());
         wishlistStub = new Wishlist(Long.valueOf(1), new HashSet<>());
         userStub = new User(Long.valueOf(1),"achraf", "luddensekko", "mail@mail.com", "open");
         userStub.setCart(cartStub);
         userStub.setWishlist(wishlistStub);

         productStub = new Product(Long.valueOf(1), "jersey", 200, "desc",
                 "icon", new Date(), new Date(), new HashSet<Wishlist>(), new HashSet<Order>());

         orderStub = new Order(Long.valueOf(1), "M", 1, cartStub, productStub);
         orderDTO = new OrderDTO(1, "M", Long.valueOf(1));
         when(principal.getName()).thenReturn(nameStub);

    }

    @AfterEach
    void tearDown() {
        userStub = null;
        cartStub = null;
        wishlistStub = null;
        productStub = null;
        orderStub = null;
        orderDTO = null;
    }

    @Test
    void canReturnCartOrders_whenUserIsFound() {
        //arrange
        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.of(userStub));
        //act
        Set<Order> orders = underTest.findCartOrders(principal);
        //assert
        verify(userRepository).findByUsername(principal.getName());
        assertThat(orders).isNotNull();
    }

    @Test
    void canNotReturnCartOrders_whenUserNotFound(){
        //arrange
        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.empty());
        //act
        Set<Order> orders = underTest.findCartOrders(principal);
        //assert
        verify(userRepository).findByUsername(principal.getName());
        assertThat(orders).isNull();

    }

    @Test
    void canAddOrderToCart(){
        //arrange
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(userStub));
        when(productRepository.findById(any())).thenReturn(Optional.of(productStub));
        when(orderRepository.save(any())).thenReturn(orderStub);
        when(cartRepository.save(any())).thenReturn(cartStub);

        //act
        Cart expected = underTest.addOrderToCart(principal, orderDTO);

        //assert
        verify(userRepository).findByUsername(any(String.class));
        verify(productRepository).findById(any());
        verify(orderRepository).save(any());
        verify(cartRepository).save(any());

        assertThat(expected).isInstanceOf(Cart.class);
    }


    @Test
    void canDeleteOrderFromCart() {
        //arrange
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(userStub));
        when(orderRepository.findById(any())).thenReturn(Optional.of(orderStub));
        when(cartRepository.save(any())).thenReturn(cartStub);

        //act
        Cart expected = underTest.deleteOrderFromCart(principal, Long.valueOf(1));

        //assert
        verify(userRepository).findByUsername(any());
        verify(orderRepository).findById(any());
        verify(cartRepository).save(any());

        assertThat(expected).isInstanceOf(Cart.class);
    }

}