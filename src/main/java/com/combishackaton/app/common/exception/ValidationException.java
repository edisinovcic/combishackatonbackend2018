package com.combishackaton.app.common.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@SuppressWarnings("squid:S1165")
public class ValidationException extends BaseException {

    @Getter
    private List<FieldError> fieldErrors;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, String errorCode) {
        super(message, errorCode);
    }

    public ValidationException setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
        return this;
    }
}
