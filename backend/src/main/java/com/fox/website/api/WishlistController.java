package com.fox.website.api;

import com.fox.website.models.Product;
import com.fox.website.models.Wishlist;
import com.fox.website.security.CurrentUser;
import com.fox.website.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.management.loading.PrivateClassLoader;
import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("{productId}")
    public ResponseEntity<Wishlist> addProductToWishlist(@PathVariable Long productId, Principal principal ){
        try{
            return ResponseEntity.ok(wishlistService.addProductToWishlist(productId, principal));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<Set<Product>> allUserWishlistProducts(Principal principal){
        try{
            return ResponseEntity.ok(wishlistService.getWishlistProducts(principal));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Wishlist> deleteProductFromWishlist( Principal principal, @PathVariable Long productId){
        try{
            return ResponseEntity.ok(wishlistService.deleteProductFromWishlist(productId, principal));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
