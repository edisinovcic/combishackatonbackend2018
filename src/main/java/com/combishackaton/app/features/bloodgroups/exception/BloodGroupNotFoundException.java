package com.combishackaton.app.features.bloodgroups.exception;

import com.combishackaton.app.common.exception.BaseException;

public class BloodGroupNotFoundException extends BaseException {
    public BloodGroupNotFoundException(String message) {
        super(message);
    }
}
