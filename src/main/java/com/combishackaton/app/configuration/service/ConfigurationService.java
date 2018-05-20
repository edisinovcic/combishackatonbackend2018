package com.combishackaton.app.configuration.service;

import com.combishackaton.app.configuration.entity.Configuration;

import java.util.Map;

public interface ConfigurationService {

    Map<String, Configuration> findAll();

}
