package com.combishackaton.app.common.social.connection.google.service;

import com.combishackaton.app.common.social.connection.entity.SocialConnection;
import com.combishackaton.app.common.social.connection.entity.SocialType;
import com.combishackaton.app.common.social.connection.facebook.service.FacebookServiceImpl;
import com.combishackaton.app.common.social.connection.google.model.GoogleUserInfo;
import com.combishackaton.app.common.social.exception.OAuthException;
import com.combishackaton.app.common.social.oauth.OAuthService;
import com.combishackaton.app.security.auth.model.AuthenticationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleServiceImpl implements GoogleService {

    private Logger log = LoggerFactory.getLogger(FacebookServiceImpl.class);
    private RestTemplate restTemplate;
    private OAuthService oAuthService;

    @Autowired
    public GoogleServiceImpl(OAuthService oAuthService, RestTemplate restTemplate) {
        this.oAuthService = oAuthService;
        this.restTemplate = restTemplate;
    }

    @Override
    public AuthenticationResponse authenticate(String accessToken) throws JsonProcessingException, OAuthException {
        if(StringUtils.isEmpty(accessToken)) {
            throw new OAuthException("Invalid or missing access token");
        }

        GoogleUserInfo googleUserInfo = getUserInfo(accessToken);
        SocialConnection socialConnection = oAuthService.getSocialConnection(googleUserInfo, SocialType.GOOGLE);
        return oAuthService.generateToken(socialConnection.getUser());
    }

    @Override
    public GoogleUserInfo getUserInfo(String accessToken) throws OAuthException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
            return restTemplate.exchange("https://www.googleapis.com/userinfo/v2/me", HttpMethod.GET,
                    new HttpEntity<>(null, headers), GoogleUserInfo.class).getBody();
        } catch(HttpStatusCodeException exception) {
            log.error("Google authentication failed ({}): {}", exception.getStatusCode(),
                    exception.getResponseBodyAsString());
            throw new OAuthException(String.format("The access token you're using is either expired or invalid (%s)",
                    String.valueOf(exception.getStatusCode())), exception);
        }
    }
}
