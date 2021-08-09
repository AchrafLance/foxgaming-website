package com.fox.website.services.Impl;

import com.fox.website.dtos.CartDTO;
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
    public List<CartDTO> findAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (Cart cart : carts) {
            cartDTOList.add(new CartDTO(cart.getCartId(), cart.getUser().getUserID()));
        }
        return cartDTOList;

    }

    @Transactional
    public Order addToCart(OrderDTO orderDto) {
        try{
            Order savedOrder = null;
            Optional<Product> product = productRepository.findById(orderDto.getProductId());
            Optional<User> user = userRepository.findById(orderDto.getUserId());
            if(product.isPresent() && user.isPresent()){
                    Cart cart = user.get().getCart();
                    Order order = new Order(orderDto.getSize(), orderDto.getQuantity(), product.get(), cart);
                    savedOrder = orderRepository.save(order);
                }
            return savedOrder;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    @Transactional
    public Order deleteItem(Long orderId, Long userId) {
        Optional<Order> order = orderRepository.findById(orderId);
          orderRepository.delete(order.get());
          return order.get();
        }


}

