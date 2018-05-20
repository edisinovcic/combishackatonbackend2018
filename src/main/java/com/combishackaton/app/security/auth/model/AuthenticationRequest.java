package com.combishackaton.app.security.auth.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AuthenticationRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
