package com.fox.website.api;

import com.fox.website.dtos.OrderDTO;
import com.fox.website.models.Cart;
import com.fox.website.models.Order;
import com.fox.website.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;


    @GetMapping("{userId}")
    public ResponseEntity<Set<Order>> getCartOrders(@PathVariable Long userId){
        try{
            return ResponseEntity.ok(cartService.findCartOrders(userId));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

     @PostMapping("{userId}/product/{productId}")
    public ResponseEntity<Cart> addOrderToCart(@PathVariable Long userId, @PathVariable Long productId, @RequestBody OrderDTO orderDTO){
         try{
             return ResponseEntity.ok(cartService.addOrderToCart(userId, productId, orderDTO));
         }
         catch(Exception e){
             return ResponseEntity.badRequest().build();
         }
     }

     @DeleteMapping("/{userId}/order/{orderId}")
    public ResponseEntity<Cart> deleteItem(@PathVariable("orderId") Long orderId,
                                            @PathVariable("userId") Long userId){
        try{
            return ResponseEntity.ok(cartService.deleteOrderFromCart(userId, orderId));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

     }
}
