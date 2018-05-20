package com.combishackaton.app.security.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    private String accessToken;

    private String refreshToken;

    public Token setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public Token setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
