package com.combishackaton.app.common;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Constants class");
    }

    public static final String VALIDATION_EXCEPTION_CODE = "validation.default-message";
    public static final String VALIDATION_EXCEPTION = "Validation error occurred";

    // STANDARD ENVIRONMENT KEYS
    public static final String SERVER_BASE_URL = "server.base-url";
    public static final String SERVER_CONTEXT_PATH = "server.context-path";
    public static final String STORAGE_ROOT_FOLDER = "storage.root-folder";
}
