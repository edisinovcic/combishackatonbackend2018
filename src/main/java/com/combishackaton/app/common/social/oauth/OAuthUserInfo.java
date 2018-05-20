package com.combishackaton.app.common.social.oauth;

import lombok.Data;

@Data
public abstract class OAuthUserInfo {

    private String id;

    private String firstName;

    private String lastName;

    private String email;
}
