package com.combishackaton.app.logging.service;

import com.combishackaton.app.logging.entity.ExceptionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExceptionLogService {

    ExceptionLog findById(Long logId);

    Page<ExceptionLog> findExceptionLogs(Pageable pageable);

    void save(Throwable ex);
}
