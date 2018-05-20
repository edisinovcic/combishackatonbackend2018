package com.combishackaton.app.common.social.oauth;

import com.combishackaton.app.common.exception.EntityNotFoundException;
import com.combishackaton.app.common.social.connection.entity.SocialConnection;
import com.combishackaton.app.common.social.connection.entity.SocialType;
import com.combishackaton.app.common.social.connection.service.SocialConnectionService;
import com.combishackaton.app.security.auth.jwt.JwtTokenFactory;
import com.combishackaton.app.security.auth.model.AuthenticationResponse;
import com.combishackaton.app.security.auth.model.UserPrincipal;
import com.combishackaton.app.user.entity.User;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import com.combishackaton.app.user.model.UserRegistrationRequest;
import com.combishackaton.app.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OAuthServiceImpl implements OAuthService {

    private JwtTokenFactory jwtTokenFactory;
    private SocialConnectionService socialConnectionService;
    private UserService userService;

    @Autowired
    public OAuthServiceImpl(JwtTokenFactory jwtTokenFactory, SocialConnectionService socialConnectionService,
            UserService userService) {
        this.jwtTokenFactory = jwtTokenFactory;
        this.socialConnectionService = socialConnectionService;
        this.userService = userService;
    }

    @Override
    public SocialConnection getSocialConnection(OAuthUserInfo authUserInfo, SocialType socialType) {
        SocialConnection socialConnection = socialConnectionService
                .findBySocialIdAndSocialType(authUserInfo.getId(), socialType);
        return Optional.ofNullable(socialConnection).orElseGet(
                () -> socialConnectionService.create(authUserInfo.getId(), socialType, getUser(authUserInfo)));
    }

    @Override
    public AuthenticationResponse generateToken(User user) throws JsonProcessingException {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtTokenFactory.generate(new UserPrincipal(user), true));
        authenticationResponse.setUser(user.getTransferObject());
        return authenticationResponse;
    }

    private User getUser(OAuthUserInfo oAuthUserInfo) {
        try {
            return userService.findUserByEmail(oAuthUserInfo.getEmail());
        } catch(UserDoesntExistException e) {
            UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
            userRegistrationRequest.setFirstName(oAuthUserInfo.getFirstName());
            userRegistrationRequest.setLastName(oAuthUserInfo.getLastName());
            userRegistrationRequest.setEmail(oAuthUserInfo.getEmail());
            userRegistrationRequest.setPassword(UUID.randomUUID().toString());
            return userService.createUser(userRegistrationRequest);
        }
    }
}
