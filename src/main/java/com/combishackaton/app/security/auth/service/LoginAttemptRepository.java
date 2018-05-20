package com.combishackaton.app.security.auth.service;

import com.combishackaton.app.security.auth.entity.LoginAttempt;
import com.combishackaton.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {

    Optional<LoginAttempt> findByUser(User user);
}
