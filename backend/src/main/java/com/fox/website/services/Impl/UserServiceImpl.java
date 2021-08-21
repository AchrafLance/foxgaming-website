package com.fox.website.services.Impl;

import com.fox.website.models.*;
import com.fox.website.repositories.CartRepository;
import com.fox.website.repositories.PasswordResetTokenRepository;
import com.fox.website.repositories.UserRepository;
import com.fox.website.repositories.WishlistRepository;
import com.fox.website.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    CartRepository cartRepository;
    WishlistRepository wishlistRepository;
    PasswordResetTokenRepository passwordResetTokenRepository;
    JavaMailSender mailSender;
    PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, CartRepository cartRepository,
                           WishlistRepository wishlistRepository,
                           PasswordResetTokenRepository passwordResetTokenRepository,
                           JavaMailSender mailSender,
                           PasswordEncoder passwordEncoder

    ) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.wishlistRepository = wishlistRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email).get();
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user, new Date());
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public void sendResetTokenEmail(String token, User user) {
        String url ="http://localhost:8080/api/auth/changePassword?token=" + token;
        String message = "click <a href='" + url + "'>here</a> to reset password";
        constructAndSendEmail("Reset Password", message, user);
     }


    public void constructAndSendEmail(String subject, String body, User user){
         MimeMessage mimeMessage =mailSender.createMimeMessage();
         try{
             MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
             mimeMessageHelper.setSubject(subject);
             mimeMessageHelper.setFrom("support@mail.com");
             mimeMessageHelper.setTo(user.getEmail());
             mimeMessageHelper.setText(body, true);
             mailSender.send(mimeMessage);
         }
         catch(Exception e){
             e.printStackTrace();
         }
     }
    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return userRepository.findByPasswordResetToken(token);
//        return null;
    }

    @Override
    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }


}
