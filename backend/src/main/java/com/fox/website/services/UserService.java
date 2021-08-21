package com.fox.website.services;

import com.fox.website.models.User;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    User findUserByEmail(String email);
    void createPasswordResetTokenForUser(User user, String token);
    void sendResetTokenEmail( String token, User user);
    Optional<User> getUserByPasswordResetToken(String token);
    void changeUserPassword(User user, String password);
}
