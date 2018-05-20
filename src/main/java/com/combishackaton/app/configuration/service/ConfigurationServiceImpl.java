package com.combishackaton.app.configuration.service;

import com.combishackaton.app.configuration.entity.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    private ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationServiceImpl(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public Map<String, Configuration> findAll() {
        List<Configuration> configurationList = configurationRepository.findAll();
        return configurationList.stream().collect(Collectors.toMap(Configuration::getKey, Function.identity()));
    }
}
