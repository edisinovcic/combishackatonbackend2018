package com.combishackaton.app.security.auth.controller;

import com.combishackaton.app.common.Constants;
import com.combishackaton.app.common.exception.EntityNotFoundException;
import com.combishackaton.app.common.exception.ValidationException;
import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.logging.annotation.Loggable;
import com.combishackaton.app.security.auth.exception.InvalidRefreshTokenException;
import com.combishackaton.app.security.auth.model.AuthenticationRequest;
import com.combishackaton.app.security.auth.model.AuthenticationResponse;
import com.combishackaton.app.security.auth.model.Token;
import com.combishackaton.app.security.auth.service.AuthenticationService;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Loggable(action = "User tries to login")
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public RestResponse<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest authenticationRequest, BindingResult bindingResult) throws
            ValidationException, JsonProcessingException, UserDoesntExistException, EntityNotFoundException {

        if(bindingResult.hasFieldErrors()) {
            throw new ValidationException(Constants.VALIDATION_EXCEPTION, Constants.VALIDATION_EXCEPTION_CODE)
                    .setFieldErrors(bindingResult.getFieldErrors());
        }

        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);
        return new RestResponse<AuthenticationResponse>(true).setData(response);
    }


    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public RestResponse<Token> refresh(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String refreshToken,
            HttpServletRequest request) throws IOException, InvalidRefreshTokenException, UserDoesntExistException {
        Token token = authenticationService.refreshToken(refreshToken);
        return new RestResponse<Token>(true).setData(token);
    }
}
