package com.combishackaton.app.logging.service;

import com.combishackaton.app.logging.entity.ExceptionLog;
import com.combishackaton.app.security.auth.model.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {

    private Logger log = LoggerFactory.getLogger(ExceptionLogService.class);
    private ExceptionLogRepository exceptionLogRepository;

    @Autowired
    public ExceptionLogServiceImpl(ExceptionLogRepository exceptionLogRepository) {
        this.exceptionLogRepository = exceptionLogRepository;
    }

    @Override
    public ExceptionLog findById(Long logId) {
        return Optional.ofNullable(exceptionLogRepository.findById(logId))
                       .orElseThrow(() -> new EntityNotFoundException("Could not find log with given id"));
    }

    @Override
    public Page<ExceptionLog> findExceptionLogs(Pageable pageable) {
        return exceptionLogRepository.findAll(pageable);
    }

    @Override
    public void save(Throwable throwable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = "Guest";
        if(authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            userName = userPrincipal.getEmail();
        }

        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));

        String stackTrace = sw.toString();
        ExceptionLog exceptionLog = ExceptionLog.builder().exceptionName(throwable.getClass().getSimpleName())
                                                .timestamp(LocalDateTime.now()).stackTrace(stackTrace).user(userName)
                                                .build();

        try {
            exceptionLogRepository.save(exceptionLog);
        } catch(Exception ex) {
            log.error("Could not save exception log", ex);
        }
    }
}
