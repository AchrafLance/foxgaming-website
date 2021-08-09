package com.fox.website.api;

import com.fox.website.dtos.CartDTO;
import com.fox.website.dtos.OrderDTO;
import com.fox.website.models.Cart;
import com.fox.website.models.Order;
import com.fox.website.services.CartService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;


    @GetMapping("")
    public ResponseEntity<List<CartDTO>> getall(){
        try{
            return ResponseEntity.ok(cartService.findAllCarts());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

     @PostMapping("/addToCart")
    public ResponseEntity<Order> addToCart(@RequestBody OrderDTO orderDto){
         try{
             return ResponseEntity.ok(cartService.addToCart(orderDto));
         }
         catch(Exception e){
             return ResponseEntity.badRequest().build();
         }
     }

     @DeleteMapping("/{orderId}/{userId}")
    public ResponseEntity<Order> deleteItem(@PathVariable("orderId") Long orderId,
                                            @PathVariable("userId") Long userId){
        try{
            return ResponseEntity.ok(cartService.deleteItem(orderId, userId));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

     }
}
