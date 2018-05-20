package com.combishackaton.app.security.auth.service;


import com.combishackaton.app.security.auth.exception.InvalidRefreshTokenException;
import com.combishackaton.app.security.auth.jwt.JwtTokenFactory;
import com.combishackaton.app.security.auth.jwt.JwtTokenUtils;
import com.combishackaton.app.security.auth.model.AuthenticationRequest;
import com.combishackaton.app.security.auth.model.AuthenticationResponse;
import com.combishackaton.app.security.auth.model.Token;
import com.combishackaton.app.security.auth.model.UserPrincipal;
import com.combishackaton.app.user.entity.User;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import com.combishackaton.app.user.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtTokenFactory jwtTokenFactory;
    private JwtTokenUtils jwtTokenUtils;
    private LoginAttemptService loginAttemptService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserService userService,
            JwtTokenFactory jwtTokenFactory, JwtTokenUtils jwtTokenUtils, LoginAttemptService loginAttemptService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenFactory = jwtTokenFactory;
        this.jwtTokenUtils = jwtTokenUtils;
        this.loginAttemptService = loginAttemptService;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws
            UserDoesntExistException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch(BadCredentialsException e) {
            loginAttemptService.updateFailAttempt(authenticationRequest.getEmail());
            throw e;
        }

        loginAttemptService.resetFailAttempt(authenticationRequest.getEmail());
        User user = userService.findUserByEmail(authenticationRequest.getEmail());

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtTokenFactory.generate(new UserPrincipal(user), true));
        authenticationResponse.setUser(user.getTransferObject());
        return authenticationResponse;
    }

    @Override
    public Token refreshToken(String refreshToken) throws InvalidRefreshTokenException, UserDoesntExistException {
        Claims claims = jwtTokenUtils.getClaims(refreshToken);
        User user = userService.findUserById(claims.getSubject());

        if(!jwtTokenUtils.validateRefreshToken(refreshToken, user)) {
            throw new InvalidRefreshTokenException("The refresh token you're using is either expired or invalid");
        }

        Token token = jwtTokenFactory.generate(new UserPrincipal(user), false);
        token.setRefreshToken(refreshToken);
        return token;
    }
}
