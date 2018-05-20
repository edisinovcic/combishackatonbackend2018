package com.combishackaton.app.user.model;

public class UserConstants {

    private UserConstants() {
    }

    public static final String USER_EMAIL_EXCEPTION_CODE = "user.email.change_email_error";
    public static final String USER_EMAIL_EXCEPTION = "Something went wrong. Could not change email.";

    public static final String VALIDATION_EXCEPTION_CODE = "user.validation";
    public static final String VALIDATION_EXCEPTION = "Validation error occurred";

    public static final String USER_ALREADY_EXISTS_CODE = "user.already_exists";
    public static final String USER_ALREADY_EXISTS = "User with given email already exists";


}
