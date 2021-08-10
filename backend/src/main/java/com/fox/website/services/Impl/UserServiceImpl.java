package com.fox.website.services.Impl;

import com.fox.website.models.*;
import com.fox.website.repositories.CartRepository;
import com.fox.website.repositories.UserRepository;
import com.fox.website.repositories.WishlistRepository;
import com.fox.website.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    CartRepository cartRepository;
    WishlistRepository wishlistRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CartRepository cartRepository, WishlistRepository wishlistRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public User register(User user) {
        User savedUser= userRepository.save(user);
        Cart savedCart =  cartRepository.save(new Cart(savedUser, new HashSet<Order>()));
        Wishlist savedWishlist = wishlistRepository.save(new Wishlist(savedUser));
        savedUser.setCart(savedCart);
        savedUser.setWishlist(savedWishlist);
        User savedUser2 = userRepository.save(savedUser);
        return savedUser2;

    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
