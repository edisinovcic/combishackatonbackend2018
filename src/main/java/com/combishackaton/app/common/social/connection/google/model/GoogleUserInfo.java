package com.combishackaton.app.common.social.connection.google.model;

import com.combishackaton.app.common.social.oauth.OAuthUserInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleUserInfo extends OAuthUserInfo {

    @JsonSetter(value = "given_name")
    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @JsonSetter(value = "family_name")
    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }
}
