package com.combishackaton.app.common.social.connection.facebook.model;

import com.combishackaton.app.common.social.oauth.OAuthUserInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookUserInfo extends OAuthUserInfo {

    @JsonSetter(value = "first_name")
    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @JsonSetter(value = "last_name")
    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @JsonProperty(value = "picture")
    private FacebookPicture picture;
}
