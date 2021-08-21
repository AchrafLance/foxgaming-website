package com.fox.website.services;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendTemplateBasedMessage(String to, String subject, String text);
}
