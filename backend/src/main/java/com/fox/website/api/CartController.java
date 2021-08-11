package com.fox.website.api;

import com.fox.website.dtos.OrderDTO;
import com.fox.website.models.Cart;
import com.fox.website.models.Order;
import com.fox.website.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;


    @GetMapping
    public ResponseEntity<Set<Order>> getCartOrders(Principal principal){
        try{
            return ResponseEntity.ok(cartService.findCartOrders(principal));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

     @PostMapping
    public ResponseEntity<Cart> addOrderToCart( Principal principal,@RequestBody OrderDTO orderDTO){
         try{
             return ResponseEntity.ok(cartService.addOrderToCart(principal, orderDTO));
         }
         catch(Exception e){
             return ResponseEntity.badRequest().build();
         }
     }

     @DeleteMapping("{orderId}")
    public ResponseEntity<Cart> deleteItem(@PathVariable("orderId") Long orderId, Principal principal){
        try{
            return ResponseEntity.ok(cartService.deleteOrderFromCart(principal, orderId));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

     }
}
