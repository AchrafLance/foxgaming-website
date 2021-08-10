package com.fox.website.services.Impl;

import com.fox.website.models.Product;
import com.fox.website.models.User;
import com.fox.website.models.Wishlist;
import com.fox.website.repositories.ProductRepository;
import com.fox.website.repositories.UserRepository;
import com.fox.website.repositories.WishlistRepository;
import com.fox.website.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {
    private WishlistRepository wishlistRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Set<Product> getWishlistProducts(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Set<Product> products = null;
        if(user.isPresent()){
            products = user.get().getWishlist().getProducts();
        }
        return products;
    }

    @Override
    public Wishlist addProductToWishlist(Long productId, Long userId) {
        Product product = productRepository.findById(productId).get();
        User user = userRepository.findById(userId).get();
        Wishlist wishlist = user.getWishlist();
        wishlist.addProduct(product);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist deleteProductFromWishlist(Long productId, Long userId) {
        Product product = productRepository.findById(productId).get();
        Wishlist wishlist = userRepository.findById(userId).get().getWishlist();
        wishlist.removeProduct(product);
        return wishlistRepository.save(wishlist);
    }

}
