package com.combishackaton.app.common.social.connection.facebook.controller;

import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.common.social.connection.facebook.service.FacebookService;
import com.combishackaton.app.common.social.exception.OAuthException;
import com.combishackaton.app.security.auth.model.AuthenticationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facebook")
public class FacebookController {

    private FacebookService facebookService;

    @Autowired
    public FacebookController(FacebookService facebookService) {
        this.facebookService = facebookService;
    }

    @PostMapping("/oauth")
    public RestResponse<AuthenticationResponse> authenticate(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String
            token) throws JsonProcessingException, OAuthException {
        return new RestResponse<AuthenticationResponse>(true).setData(facebookService.authenticate(token));
    }
}
