package com.combishackaton.app.common.social.oauth;

import com.combishackaton.app.common.social.connection.entity.SocialConnection;
import com.combishackaton.app.common.social.connection.entity.SocialType;
import com.combishackaton.app.security.auth.model.AuthenticationResponse;
import com.combishackaton.app.user.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OAuthService {

    SocialConnection getSocialConnection(OAuthUserInfo authUserInfo, SocialType socialType);

    AuthenticationResponse generateToken(User user) throws JsonProcessingException;
}
