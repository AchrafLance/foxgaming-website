package com.fox.website.api;

import com.fox.website.models.Product;
import com.fox.website.models.Wishlist;
import com.fox.website.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/{userId}/product/{productId}")
    public ResponseEntity<Wishlist> addProductToWishlist(@PathVariable Long userId, @PathVariable Long productId){
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

    @DeleteMapping("{userId}/product/{productId}")
    public ResponseEntity<Wishlist> deleteProductFromWishlist(@PathVariable Long userId, @PathVariable Long productId){
        try{
            return ResponseEntity.ok(wishlistService.deleteProductFromWishlist(productId, userId));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
