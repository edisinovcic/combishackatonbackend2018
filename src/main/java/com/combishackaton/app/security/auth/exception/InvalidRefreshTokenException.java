package com.combishackaton.app.security.auth.exception;

import com.combishackaton.app.common.exception.BaseException;

public class InvalidRefreshTokenException extends BaseException {

    public InvalidRefreshTokenException(String message) {
        super(message);
    }

    public InvalidRefreshTokenException(String message, Throwable ex) {
        super(message, ex);
    }
}
