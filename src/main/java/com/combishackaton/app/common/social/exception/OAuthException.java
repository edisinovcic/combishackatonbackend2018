package com.combishackaton.app.common.social.exception;

import com.combishackaton.app.common.exception.BaseException;

public class OAuthException extends BaseException {

    public OAuthException(String message) {
        super(message);
    }

    public OAuthException(String message, Throwable ex) {
        super(message, ex);
    }
}
