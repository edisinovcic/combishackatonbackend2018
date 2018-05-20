package com.combishackaton.app.user.exception;


import com.combishackaton.app.common.exception.BaseException;

public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException(String message, String errorCode) {
        super(message, errorCode);
    }

}
