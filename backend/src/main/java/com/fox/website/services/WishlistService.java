package com.fox.website.services;

import com.fox.website.models.Product;
import com.fox.website.models.Wishlist;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface WishlistService {
    Set<Product> getWishlistProducts(Long userId);
    Wishlist addProductToWishlist(Long productId, Long userId);
    Product deleteProductFromWishlist(Long product, Long userId);
    List<Wishlist> getAllWishlists();
}
