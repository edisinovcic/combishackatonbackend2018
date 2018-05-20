package com.combishackaton.app.logging.aspect;

import com.combishackaton.app.logging.annotation.Loggable;
import com.combishackaton.app.logging.entity.Log;
import com.combishackaton.app.logging.service.LogService;
import com.combishackaton.app.security.auth.model.UserPrincipal;
import com.combishackaton.app.user.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Aspect to log user action after returning success or throwing an exception.
 * To trigger add @Loggable annotation to controller method.
 */
@Aspect
@Component
public class LoggableAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggableAspect.class);

    private UserService userService;
    private LogService logService;
    private HttpServletRequest request;

    @Autowired
    public LoggableAspect(LogService logService, UserService userService, HttpServletRequest request) {
        this.logService = logService;
        this.userService = userService;
        this.request = request;
    }

    @AfterReturning(value = "execution(* *(..)) && @annotation(loggable)")
    public void afterExecutionTag(JoinPoint point, Loggable loggable) {
        logUserAction(true, loggable, null);
    }

    @AfterThrowing(value = "execution(* *(..)) && @annotation(loggable)", throwing = "e")
    public void afterThrowingLog(JoinPoint point, Loggable loggable, Exception e) {
        logUserAction(false, loggable, e.getMessage());
    }

    private void logUserAction(Boolean success, Loggable loggable, String errorMessage) {
        try {
            Log log = new Log();
            log.setAction(loggable.action());
            log.setDevice(request.getHeader(HttpHeaders.USER_AGENT));
            log.setTime(LocalDateTime.now());
            log.setSuccess(success);

            if(!success) {
                log.setError(errorMessage);
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
                log.setUser(userService.getAuthenticatedUser());
            }

            logService.addLog(log);
        } catch(Exception ex) {
            logger.warn("Exception occurred while logging user action: ", ex);
        }
    }
}
