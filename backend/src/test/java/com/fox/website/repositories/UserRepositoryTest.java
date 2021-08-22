package com.fox.website.repositories;

import com.fox.website.exceptions.AppException;
import com.fox.website.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    WishlistRepository wishlistRepository;

    private User savedUser;


    @BeforeEach
    void setUp() {
        User user = new User(Long.valueOf(1),"achraf", "luddensekko", "mail@mail.com", "open");
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));
        Cart savedCart = cartRepository.save(new Cart());
        Wishlist savedWishlist = wishlistRepository.save(new Wishlist());

        user.setCart(savedCart);
        user.setWishlist(savedWishlist);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        savedUser = userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
        cartRepository.deleteAll();
        wishlistRepository.deleteAll();
        userRepository.deleteAll();
        savedUser = null;
    }



    @Test
    void itShouldFindByPasswordResetToken() {
        //given
        String token = "token123";
        passwordResetTokenRepository.save(new PasswordResetToken(token, savedUser));

        //when
        Optional<User> result = userRepository.findByPasswordResetToken(token);

        //then
        assertThat(result.get().getId()).isEqualTo(savedUser.getId());
    }

}