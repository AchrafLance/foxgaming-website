package com.fox.website.services;

public interface SecurityService {
    String validatePasswordResetToken(String token);
}
