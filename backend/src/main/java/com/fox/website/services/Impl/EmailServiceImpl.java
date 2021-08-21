package com.fox.website.services.Impl;

import com.fox.website.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@adservio.ma");
        message.setSubject(subject);
        message.setTo(to);
        message.setText(text);

        mailSender.send(message);
    }

    @Override
    public void sendTemplateBasedMessage(String to, String subject, String text) {
        MimeMessage mimeMessage =mailSender.createMimeMessage();
        String mailContent = "<h1>Hello world</h1>";
        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom("achraf.elkhandouli@gmail.com");
            mimeMessageHelper.setTo("achraf.contacts@gmail.com");
            mimeMessageHelper.setText(mailContent, true);
            mailSender.send(mimeMessage);

        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

}
