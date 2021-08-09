package com.fox.website.services;

import com.fox.website.dtos.CartDTO;
import com.fox.website.dtos.OrderDTO;
import com.fox.website.models.Cart;
import com.fox.website.models.Order;

import java.util.List;

public interface CartService {
     Order addToCart(OrderDTO order);
     Order deleteItem(Long orderId, Long userId);
     List<CartDTO> findAllCarts();
}
