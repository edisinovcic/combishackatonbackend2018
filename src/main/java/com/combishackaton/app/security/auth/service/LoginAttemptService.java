package com.combishackaton.app.security.auth.service;

public interface LoginAttemptService {

    void updateFailAttempt(String email);

    void resetFailAttempt(String email);

}
