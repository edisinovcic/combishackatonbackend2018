package com.combishackaton.app.common.social.connection.facebook.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FacebookPicture {

    @JsonProperty(value = "data")
    private FacebookPictureData data;
}
