package com.fox.website.services;

import com.fox.website.models.PasswordResetToken;
import com.fox.website.repositories.PasswordResetTokenRepository;
import com.fox.website.services.Impl.SecurityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityServiceTest {

    @Mock
    PasswordResetTokenRepository passwordResetTokenRepository;

    @InjectMocks
    SecurityServiceImpl underTest;

    @Test
    void validatePasswordResetToken_whenTokenValid() {
        //ARRANGE
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken("token");
        passwordResetToken.setExpiryDate(new Date());
        when(passwordResetTokenRepository.findByToken(any(String.class)))
                .thenReturn(passwordResetToken);

        //ACT
        String expected = underTest.validatePasswordResetToken("token");
        //ASSERT
        verify(passwordResetTokenRepository).findByToken(any(String.class));
        assertThat(expected).isNull();

    }

    @Test
    void validatePasswordResetToken_whenTokenInvalid() {
        //ARRANGE
        when(passwordResetTokenRepository.findByToken(any(String.class)))
                .thenReturn(null);

        //ACT
        String expected = underTest.validatePasswordResetToken("token");
        //ASSERT
        verify(passwordResetTokenRepository).findByToken(any(String.class));
        assertThat(expected).isEqualTo("invalidToken");
    }

    @Test
    void validatePasswordResetToken_whenTokenIsExpired() {
        //ARRANGE
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken("token");
        Date currentDate = new Date();
        Long twoDaysInMillisec = Long.valueOf( 2*24*60*60*1000);
        Date expDate = new Date(currentDate.getTime() - twoDaysInMillisec);
        passwordResetToken.setExpiryDate(expDate);
        when(passwordResetTokenRepository.findByToken(any(String.class)))
                .thenReturn(passwordResetToken);

        //ACT
        String expected = underTest.validatePasswordResetToken("token");
        //ASSERT
        verify(passwordResetTokenRepository).findByToken(any(String.class));
        assertThat(expected).isEqualTo("expired");

    }
}