package com.combishackaton.app.common.exception;

public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public EntityNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
