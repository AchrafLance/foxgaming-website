package com.fox.website.services;

import com.fox.website.models.Product;
import com.fox.website.models.Wishlist;


import java.util.Set;

public interface WishlistService {
    Set<Product> getWishlistProducts(Long userId);
    Wishlist addProductToWishlist(Long productId, Long userId);
    Wishlist deleteProductFromWishlist(Long productId, Long userId);
}
