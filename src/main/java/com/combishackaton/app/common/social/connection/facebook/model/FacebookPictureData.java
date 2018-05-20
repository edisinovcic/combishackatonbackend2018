package com.combishackaton.app.common.social.connection.facebook.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FacebookPictureData {

    @JsonProperty(value = "height")
    private Long height;

    @JsonProperty(value = "width")
    private Long width;

    @JsonProperty(value = "url")
    private String url;
}
