package com.combishackaton.app.common.exception;

import com.combishackaton.app.common.model.RestError;
import com.combishackaton.app.common.model.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private GlobalExceptionHandler() {
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public RestResponse handleThrowable(final Throwable ex) {
        log.info("GlobalExceptionHandler [{}] ({}:{}): {}", ex.getClass().getSimpleName(),
                ex.getStackTrace()[0].getFileName(), ex.getStackTrace()[0].getLineNumber(), ex.getMessage());

        return new RestResponse(false).setError(new RestError().setException(ex));
    }
}
