package com.combishackaton.app.logging.service;

import com.combishackaton.app.logging.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void addLog(Log log) {
        logRepository.save(log);
    }
}
