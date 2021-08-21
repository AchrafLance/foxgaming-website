package com.fox.website.repositories;

import com.fox.website.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.passwordResetToken.token = :token")
    Optional<User> findByPasswordResetToken(@Param("token") String token);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
