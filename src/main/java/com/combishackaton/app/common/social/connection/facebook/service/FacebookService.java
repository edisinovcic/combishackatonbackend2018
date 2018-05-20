package com.combishackaton.app.common.social.connection.facebook.service;

import com.combishackaton.app.common.social.connection.facebook.model.FacebookUserInfo;
import com.combishackaton.app.common.social.exception.OAuthException;
import com.combishackaton.app.security.auth.model.AuthenticationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface FacebookService {

    AuthenticationResponse authenticate(String accessToken) throws JsonProcessingException, OAuthException;

    FacebookUserInfo getUserInfo(String accessToken) throws OAuthException;
}
