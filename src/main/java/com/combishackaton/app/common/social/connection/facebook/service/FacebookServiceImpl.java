package com.combishackaton.app.common.social.connection.facebook.service;

import com.combishackaton.app.common.social.connection.entity.SocialConnection;
import com.combishackaton.app.common.social.connection.entity.SocialType;
import com.combishackaton.app.common.social.connection.facebook.model.FacebookUserInfo;
import com.combishackaton.app.common.social.exception.OAuthException;
import com.combishackaton.app.common.social.oauth.OAuthService;
import com.combishackaton.app.security.auth.model.AuthenticationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacebookServiceImpl implements FacebookService {

    private Logger log = LoggerFactory.getLogger(FacebookServiceImpl.class);
    private RestTemplate restTemplate;
    private OAuthService oAuthService;

    @Autowired
    public FacebookServiceImpl(OAuthService oAuthService, RestTemplate restTemplate) {
        this.oAuthService = oAuthService;
        this.restTemplate = restTemplate;
    }

    @Override
    public AuthenticationResponse authenticate(String accessToken) throws JsonProcessingException, OAuthException {
        if(StringUtils.isEmpty(accessToken)) {
            throw new OAuthException("Invalid or missing access token");
        }

        FacebookUserInfo facebookUserInfo = getUserInfo(accessToken);
        SocialConnection socialConnection = oAuthService.getSocialConnection(facebookUserInfo, SocialType.FACEBOOK);
        return oAuthService.generateToken(socialConnection.getUser());
    }

    @Override
    public FacebookUserInfo getUserInfo(String accessToken) throws OAuthException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("access_token", accessToken);
        urlParams.put("fields", "id,first_name,last_name,email,picture.type(large)");

        try {
            return restTemplate
                    .getForObject("https://graph.facebook.com/v2.12/me/?access_token={access_token}&fields={fields}",
                            FacebookUserInfo.class, urlParams);
        } catch(HttpStatusCodeException exception) {
            log.error("Facebook authentication failed ({}): {}", exception.getStatusCode(),
                    exception.getResponseBodyAsString());
            throw new OAuthException(String.format("The access token you're using is either expired or invalid (%s)",
                    String.valueOf(exception.getStatusCode())), exception);
        }
    }
}
