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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;


    @Override
    public Set<Order> findCartOrders(Principal principal) {
        Optional<User> user =  userRepository.findByUsername(principal.getName());
         if(user.isPresent()){
             return user.get().getCart().getOrders();
         }
         else return null;
    }

    @Transactional
    public Cart addOrderToCart(Principal principal, OrderDTO orderDTO) {
        Cart cart = userRepository.findByUsername(principal.getName()).get().getCart();
        Product product = productRepository.findById(orderDTO.getProductId()).get();
        Order order = new Order(orderDTO.getQuantity(), orderDTO.getSize(),cart, product);
        Order savedOrder = orderRepository.save(order);
        cart.addOrder(savedOrder);
        return cartRepository.save(cart);
    }

    @Override
    public Cart deleteOrderFromCart(Principal principal, Long orderId) {
        Cart cart = userRepository.findByUsername(principal.getName()).get().getCart();
        Order order = orderRepository.findById(orderId).get();
        cart.removeOrder(order);
        return cartRepository.save(cart);
        }


}

