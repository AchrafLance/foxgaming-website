package com.fox.website.api;

import com.fox.website.models.Product;
import com.fox.website.models.Wishlist;
import com.fox.website.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("{productId}/{userId}")
    public ResponseEntity<Wishlist> addProductToWishlist(@PathVariable Long productId, @PathVariable Long userId){
        try{
            return ResponseEntity.ok(wishlistService.addProductToWishlist(productId, userId));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{userId}")
    public ResponseEntity<Set<Product>> allUserWishlistProducts(@PathVariable Long userId){
        try{
            return ResponseEntity.ok(wishlistService.getWishlistProducts(userId));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Wishlist>> getAllWishlists(){
        try{
            return ResponseEntity.ok(wishlistService.getAllWishlists());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
