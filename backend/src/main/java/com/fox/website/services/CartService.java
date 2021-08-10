package com.fox.website.services;


import com.fox.website.dtos.OrderDTO;
import com.fox.website.models.Cart;
import com.fox.website.models.Order;

import java.util.Set;

public interface CartService {
     Cart addOrderToCart(Long userId, Long productId, OrderDTO orderDTO);
     Cart deleteOrderFromCart(Long userId, Long orderId);
     Set<Order> findCartOrders(Long userId);
}
