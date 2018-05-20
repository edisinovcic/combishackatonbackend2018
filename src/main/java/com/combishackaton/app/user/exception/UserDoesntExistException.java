package com.combishackaton.app.user.exception;

import com.combishackaton.app.common.exception.BaseException;

public class UserDoesntExistException extends BaseException {
    public UserDoesntExistException(String message) {
        super(message);
    }
}
