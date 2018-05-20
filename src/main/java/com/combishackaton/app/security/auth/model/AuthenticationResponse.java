package com.combishackaton.app.security.auth.model;

import com.combishackaton.app.user.model.UserResponse;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private Token token;
    private UserResponse user;

}
