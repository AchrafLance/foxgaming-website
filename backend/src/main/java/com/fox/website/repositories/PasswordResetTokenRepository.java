package com.fox.website.repositories;

import com.fox.website.models.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    @Query("SELECT u FROM PasswordResetToken u WHERE u.token = :token")
    PasswordResetToken findByToken(@Param("token") String token);
}
