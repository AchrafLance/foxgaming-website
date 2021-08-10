package com.fox.website.services.Impl;


import com.fox.website.dtos.OrderDTO;
import com.fox.website.models.Cart;
import com.fox.website.models.Order;
import com.fox.website.models.Product;
import com.fox.website.models.User;
import com.fox.website.repositories.CartRepository;
import com.fox.website.repositories.OrderRepository;
import com.fox.website.repositories.ProductRepository;
import com.fox.website.repositories.UserRepository;
import com.fox.website.services.CartService;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Set<Order> findCartOrders(Long userId) {
        return userRepository.findById(userId).get().getCart().getOrders();
    }

    @Transactional
    public Cart addOrderToCart(Long userId, Long productId, OrderDTO orderDTO) {
        Cart cart = userRepository.findById(userId).get().getCart();
        Product product = productRepository.findById(productId).get();
        Order order = new Order(orderDTO.quantity, orderDTO.size,cart, product);
        Order savedOrder = orderRepository.save(order);
        cart.addOrder(savedOrder);
        return cartRepository.save(cart);
    }

    @Override
    public Cart deleteOrderFromCart(Long userId, Long orderId) {
        Cart cart = userRepository.findById(userId).get().getCart();
        Order order = orderRepository.findById(orderId).get();
        cart.removeOrder(order);
        return cartRepository.save(cart);
        }


}

