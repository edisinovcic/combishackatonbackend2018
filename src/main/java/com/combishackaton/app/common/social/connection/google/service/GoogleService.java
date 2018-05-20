package com.combishackaton.app.common.social.connection.google.service;

import com.combishackaton.app.common.social.connection.google.model.GoogleUserInfo;
import com.combishackaton.app.common.social.exception.OAuthException;
import com.combishackaton.app.security.auth.model.AuthenticationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface GoogleService {

    AuthenticationResponse authenticate(String accessToken) throws JsonProcessingException, OAuthException;

    GoogleUserInfo getUserInfo(String accessToken) throws OAuthException;
}
