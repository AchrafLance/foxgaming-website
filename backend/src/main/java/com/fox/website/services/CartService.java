package com.fox.website.services;


import com.fox.website.dtos.OrderDTO;
import com.fox.website.models.Cart;
import com.fox.website.models.Order;

import java.security.Principal;
import java.util.Set;

public interface CartService {
     Cart addOrderToCart(Principal principal, OrderDTO orderDTO);
     Cart deleteOrderFromCart(Principal principal, Long orderId);
     Set<Order> findCartOrders(Principal principal);
}
