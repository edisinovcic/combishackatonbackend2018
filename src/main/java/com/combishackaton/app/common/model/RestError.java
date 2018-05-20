package com.combishackaton.app.common.model;

import com.combishackaton.app.common.exception.ValidationException;
import com.combishackaton.app.common.util.ErrorUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("squid:S1948")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class RestError implements Serializable {

    private static final long serialVersionUID = 242385203498395L;

    @Getter
    private String message;

    @Getter
    private String type;

    @Getter
    private List<RestFieldError> fieldErrors;

    @Getter
    private LocalDateTime timestamp;

    public RestError() {
        timestamp = LocalDateTime.now();
    }

    public RestError setMessage(String message) {
        this.message = message;
        return this;
    }

    public RestError setException(Throwable throwable) {
        this.type = throwable.getClass().getSimpleName();
        if(message == null) {
            this.message = throwable.getLocalizedMessage();
        }
        if(throwable instanceof ValidationException) {
            ValidationException ex = (ValidationException) throwable;
            Optional.ofNullable(ex.getFieldErrors()).ifPresent(fe -> fieldErrors = ErrorUtils.mapErrors(fe));
        }
        return this;
    }
}
