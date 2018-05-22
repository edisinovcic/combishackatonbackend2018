package com.combishackaton.app.config;

import com.combishackaton.app.security.auth.model.UserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistenceConfig {

    @Bean
    AuditorAware<Integer> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
                return ((UserPrincipal) authentication.getPrincipal()).getId();
            }
            return null;
        };
    }
}
