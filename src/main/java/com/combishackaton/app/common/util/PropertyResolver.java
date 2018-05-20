package com.combishackaton.app.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SuppressWarnings("all")
@Component
public class PropertyResolver {

    @Autowired
    private StandardEnvironment standardEnvironment;

    private Logger log = LoggerFactory.getLogger(PropertyResolver.class);

    private static PropertyResolver instance = null;

    /**
     * Method to create one instance of that class per application.
     * @return
     */
    public static PropertyResolver getInstance() {
        return instance;
    }

    /**
     * Constructor for PropertyResolver class.
     */
    protected PropertyResolver() {
        log.info("Initializing PropertyResolver");
    }

    /**
     * Initialize instance field after constructing an object.
     */
    @PostConstruct
    private void init() {
        instance = this;
    }

    public <T> T getProperty(String key) {
        return (T)instance.standardEnvironment.getProperty(key);
    }
}
