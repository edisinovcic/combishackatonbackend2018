package com.combishackaton.app.common.util;

public class GenericUtils {

    private GenericUtils() {
    }

    public static <T> T getValueOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }
}
