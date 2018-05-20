package com.combishackaton.app.configuration.controller;


import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.configuration.ConfigurationProvider;
import com.combishackaton.app.configuration.entity.Configuration;
import com.combishackaton.app.configuration.exception.ConfigurationException;
import com.combishackaton.app.configuration.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {

    private ConfigurationProvider configurationProvider;
    private ConfigurationService configurationService;

    @Autowired
    public ConfigurationController(ConfigurationProvider configurationProvider,
            ConfigurationService configurationService) {
        this.configurationProvider = configurationProvider;
        this.configurationService = configurationService;
    }

    @GetMapping
    public RestResponse<Map<String, Configuration>> fetchConfiguration() {
        Map<String, Configuration> configurationMap = configurationService.findAll();
        return new RestResponse<Map<String, Configuration>>(true).setData(configurationMap);
    }

    @GetMapping("/{key}")
    public RestResponse<Configuration> fetchConfiguration(@PathVariable String key) throws ConfigurationException {
        if(!configurationProvider.hasProperty(key)) {
            throw new ConfigurationException(String.format("The provided key %s doesn't exist in configuration", key));
        }
        return new RestResponse<Configuration>(true).setData(configurationProvider.getConfiguration(key));
    }

    @PostMapping("/reload")
    @PreAuthorize("hasAuthority('MASTER_ADMIN')")
    public RestResponse reloadConfigurations() {
        configurationProvider.loadConfigurations();
        return new RestResponse(true);
    }
}
