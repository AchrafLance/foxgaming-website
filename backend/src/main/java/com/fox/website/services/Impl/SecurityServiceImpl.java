package com.fox.website.services.Impl;

import com.fox.website.models.PasswordResetToken;
import com.fox.website.repositories.PasswordResetTokenRepository;
import com.fox.website.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return passToken.getExpiryDate().before(cal.getTime());
    }
}
