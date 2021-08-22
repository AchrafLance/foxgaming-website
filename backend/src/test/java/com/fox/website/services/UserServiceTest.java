package com.fox.website.services;

import com.fox.website.models.PasswordResetToken;
import com.fox.website.models.User;
import com.fox.website.models.Wishlist;
import com.fox.website.repositories.CartRepository;
import com.fox.website.repositories.PasswordResetTokenRepository;
import com.fox.website.repositories.UserRepository;
import com.fox.website.repositories.WishlistRepository;
import com.fox.website.services.Impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    CartRepository cartRepository;
    @Mock
    WishlistRepository wishlistRepository;
    @Mock
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Mock
    JavaMailSender mailSender;
    @Mock
    MimeMessage mimeMessage;
    @Mock
    MimeMessageHelper mimeMessageHelper;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl underTest;

    @BeforeEach
    void setUp() {

    }
    @AfterEach
    void tearDown() {

    }

    @Test
    void canFindAllUsers() {
        //ARRANGE
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        //ACT
        List<User> expected = underTest.findAllUsers();
        //ASSERT
        verify(userRepository).findAll();
        assertThat(expected).isInstanceOf(List.class);
    }

    @Test
    void canFindUserByEmail() {
        //ARRANGE
        when(userRepository.findByEmail(any(String.class)))
                .thenReturn(Optional.of(new User()));
        //ACT
        User expected = underTest.findUserByEmail("email@mail.com");
        //ASSERT
        verify(userRepository).findByEmail(any(String.class));
        assertThat(expected).isInstanceOf(User.class);
    }

    @Test
    void canCreatePasswordResetTokenForUser() {
        //ARRANGE
        User user = new User();
        when(passwordResetTokenRepository.save(any()))
                .thenReturn(new PasswordResetToken("token"));
        //ACT
        underTest.createPasswordResetTokenForUser(user, "token");
        //ASSERT
        verify(passwordResetTokenRepository).save(any());
    }

    @Test
    void canSendResetTokenEmail() {
        //ARRANGE
        User user = new User();
        user.setEmail("email@mail.com");
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        //ACT
        underTest.sendResetTokenEmail("token", user);
        //ASSERT
        verify(mailSender).send(any(MimeMessage.class));
    }

    @Test
    void canGetUserByPasswordResetToken() {
        //ARRANGE
        when(userRepository.findByPasswordResetToken(any())).thenReturn(
                Optional.of(new User()));
        //ACT
        Optional<User> expected = underTest.getUserByPasswordResetToken("token");
        //ASSERT
        verify(userRepository).findByPasswordResetToken("token");
        assertThat(expected).isNotEmpty();
    }

    @Test
    void canChangeUserPassword() {
        //ARRANGE
        User user = new User();
        when(passwordEncoder.encode(any(String.class))).thenReturn("password_encoded");
        //ACT
        underTest.changeUserPassword(user, "password");
        //ASSERT
        verify(userRepository).save((any(User.class)));
    }
}