package com.combishackaton.app.security.auth.service;

import com.combishackaton.app.common.exception.EntityNotFoundException;
import com.combishackaton.app.security.auth.exception.InvalidRefreshTokenException;
import com.combishackaton.app.security.auth.model.AuthenticationRequest;
import com.combishackaton.app.security.auth.model.AuthenticationResponse;
import com.combishackaton.app.security.auth.model.Token;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws JsonProcessingException,
            EntityNotFoundException, UserDoesntExistException;

    Token refreshToken(String refreshToken) throws IOException, InvalidRefreshTokenException, UserDoesntExistException;
}
