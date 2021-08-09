package com.fox.website.services.Impl;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
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
import java.util.Collection;
import java.util.List;
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
        Optional<Product> product = productRepository.findById(productId);
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent() && product.isPresent()){
//            List<Wishlist> wishlists = wishlistRepository.findAll();
//            Long id = wishlists.get(0).getWishlistId();
//            Long wishlistId = user.get().getWishlist().getWishlistId();
//           Optional<Wishlist> wishlist =
//                   wishlistRepository.findAll().stream().filter(e -> e.getWishlistId().equals(wishlistId)).findFirst();
//
//            if(wishlist.isPresent()){
//                wishlist.get().getProducts().add(product.get());
////                product.get().getWishlists().add(wishlist.get());
//                Product savedProduct = productRepository.save(product.get());
//                wishlistRepository.save(wishlist.get());
            Wishlist wishlist = user.get().getWishlist();
            product.get().getWishlists().add(wishlist);
             productRepository.save(product.get());
             return  wishlist;

//                return savedProduct;
            }
            else{
                return null;
            }

        }
//        else{
//            return null;
//        }
//    }

    @Override
    public Product deleteProductFromWishlist(Long product, Long userId) {
        return null;
    }

    @Override
    public List<Wishlist> getAllWishlists() {
        List<Wishlist> list = wishlistRepository.findAll();
        return list;
    }
}
