package com.combishackaton.app.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> implements Serializable {

    private static final long serialVersionUID = 2384707091841123L;

    @Getter
    private Boolean success;

    @Getter
    private RestError error;

    @Getter
    private transient T data;

    public RestResponse(Boolean success) {
        this.success = success;
    }

    public RestResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public RestResponse<T> setError(RestError error) {
        this.error = error;
        return this;
    }
}
