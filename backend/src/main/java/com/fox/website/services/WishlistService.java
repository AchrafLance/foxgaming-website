package com.fox.website.services;

import com.fox.website.models.Product;
import com.fox.website.models.Wishlist;


import java.security.Principal;
import java.util.Set;

public interface WishlistService {
    Set<Product> getWishlistProducts(Principal principal);
    Wishlist addProductToWishlist(Long productId, Principal principal);
    Wishlist deleteProductFromWishlist(Long productId, Principal principal);
}
