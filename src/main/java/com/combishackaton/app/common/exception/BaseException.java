package com.combishackaton.app.common.exception;

import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ResourceBundle;

public abstract class BaseException extends Exception {

    private static final String ERROR_RESOURCE_BUNDLE = "ErrorBundle";

    @Getter
    private final String message;

    @Getter
    private final Throwable exception;

    @Getter
    private final String code;

    public BaseException(String message) {
        super(message);
        this.message = message;
        this.exception = null;
        this.code = null;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.exception = cause;
        this.code = null;
    }

    public BaseException(String message, String errorCode) {
        super(message);
        this.message = message;
        this.exception = null;
        this.code = errorCode;
    }

    public BaseException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.exception = cause;
        this.code = errorCode;
    }

    @Override
    public String getLocalizedMessage() {
        if(code == null) {
            return this.message;
        } else {
            return ResourceBundle.getBundle(ERROR_RESOURCE_BUNDLE, LocaleContextHolder.getLocale()).getString(code);
        }
    }
}
