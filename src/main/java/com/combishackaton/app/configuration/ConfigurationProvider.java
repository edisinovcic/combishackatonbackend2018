package com.combishackaton.app.configuration;

import com.combishackaton.app.configuration.entity.Configuration;
import com.combishackaton.app.configuration.entity.ConfigurationType;
import com.combishackaton.app.configuration.exception.ConfigurationException;
import com.combishackaton.app.configuration.service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConfigurationProvider {

    private Logger logger = LoggerFactory.getLogger(ConfigurationProvider.class);

    private ConfigurationService configurationService;
    private static Map<String, Configuration> configurationMap = new HashMap<>();
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    @Autowired
    protected ConfigurationProvider(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PostConstruct
    public void loadConfigurations() {

        ConfigurationProvider.configurationMap = configurationService.findAll();

        if(!ConfigurationProvider.configurationMap.isEmpty()) {
            logger.info("Loaded {} configuration key/value pairs from database", configurationMap.size());
        }
    }

    public Configuration getConfiguration(String key) {
        return ConfigurationProvider.configurationMap.get(key);
    }

    public String getProperty(String key) {
        return getConfiguration(key).getValue();
    }

    public Integer getIntegerProperty(String key) throws ConfigurationException {
        if(!isOfType(ConfigurationType.INTEGER, key)) {
            throw new ConfigurationException("Configuration property is not of type INTEGER");
        }

        return Integer.valueOf(getProperty(key));
    }

    public LocalDateTime getDateTimeProperty(String key) throws ConfigurationException {

        if(!isOfType(ConfigurationType.DATETIME, key)) {
            throw new ConfigurationException("Configuration property is not of type DATETIME");
        }
        return LocalDateTime.parse(getProperty(key), ISO_FORMATTER);
    }

    public Boolean getBooleanProperty(String key) throws ConfigurationException {

        if(!isOfType(ConfigurationType.BOOLEAN, key)) {
            throw new ConfigurationException("Configuration property is not of type BOOLEAN");
        }
        return Boolean.valueOf(getProperty(key));
    }

    public boolean hasProperty(String key) {
        return ConfigurationProvider.configurationMap.containsKey(key);
    }

    private boolean isOfType(ConfigurationType type, String key) {
        return ConfigurationProvider.configurationMap.get(key).getType().equals(type);
    }

}
