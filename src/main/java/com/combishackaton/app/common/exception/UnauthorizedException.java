package com.combishackaton.app.common.exception;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable ex) {
        super(message, ex);
    }
}
