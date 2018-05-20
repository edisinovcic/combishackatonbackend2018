package com.combishackaton.app.security.auth.service;

import com.combishackaton.app.configuration.ConfigurationProvider;
import com.combishackaton.app.configuration.entity.ConfigurationKeys;
import com.combishackaton.app.configuration.exception.ConfigurationException;
import com.combishackaton.app.mailing.service.MailService;
import com.combishackaton.app.security.auth.entity.LoginAttempt;
import com.combishackaton.app.user.entity.User;
import com.combishackaton.app.user.service.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {

    private Logger logger = LoggerFactory.getLogger(LoginAttemptServiceImpl.class);

    private LoginAttemptRepository loginAttemptRepository;
    private UserRepository userRepository;
    private ConfigurationProvider configurationProvider;

    @Autowired
    public LoginAttemptServiceImpl(LoginAttemptRepository loginAttemptRepository, UserRepository userRepository,
            ConfigurationProvider configurationProvider, MailService mailService) {
        this.loginAttemptRepository = loginAttemptRepository;
        this.userRepository = userRepository;
        this.configurationProvider = configurationProvider;
    }

    @Override
    public void updateFailAttempt(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        user.ifPresent(u -> {
            Integer numberOfTries;
            Integer hourOfTries;
            try {
                numberOfTries = configurationProvider.getIntegerProperty(ConfigurationKeys.LOGIN_NUMBER_OF_TRIES);
                hourOfTries = configurationProvider.getIntegerProperty(ConfigurationKeys.LOGIN_TIME_OF_LAST_TRY);
            } catch(ConfigurationException e) {
                logger.warn("Error while fetching configuration", e);
                return;
            }

            Optional<LoginAttempt> attemptOpt = findLoginAttempt(u);
            LoginAttempt attempt = attemptOpt.filter(a -> {
                boolean inHourRange = a.getLastModified().isAfter(LocalDateTime.now().minusHours(hourOfTries));
                if(!inHourRange) {
                    resetFailAttempt(email);
                }
                return inHourRange;
            }).orElse(new LoginAttempt(u));

            attempt.incrementAttempt();
            attempt = loginAttemptRepository.save(attempt);
        });
    }

    @Override
    public void resetFailAttempt(String email) {

        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        user.ifPresent(u -> {
            Optional<LoginAttempt> attempt = loginAttemptRepository.findByUser(u);
            attempt.ifPresent(loginAttempt -> loginAttemptRepository.delete(loginAttempt));
        });

    }

    private Optional<LoginAttempt> findLoginAttempt(User user) {
        return loginAttemptRepository.findByUser(user);
    }
}
