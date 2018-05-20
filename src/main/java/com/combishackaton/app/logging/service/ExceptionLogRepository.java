package com.combishackaton.app.logging.service;

import com.combishackaton.app.logging.entity.ExceptionLog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExceptionLogRepository extends PagingAndSortingRepository<ExceptionLog, Long> {

    ExceptionLog findById(Long id);
}
