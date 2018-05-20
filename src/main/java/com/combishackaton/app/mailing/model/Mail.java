package com.combishackaton.app.mailing.model;

import com.combishackaton.app.common.util.SpringContext;
import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.util.Locale;

@Data
public abstract class Mail {

    protected MessageSource messageSource;
    protected SpringTemplateEngine templateEngine;
    private Locale locale;

    private String recipient;
    private String subject;
    private String body;

    protected Mail() {
        messageSource = SpringContext.getBean(MessageSource.class);
        templateEngine = SpringContext.getBean(SpringTemplateEngine.class);
        locale = LocaleContextHolder.getLocale();
    }

    protected String getLocalizedMessageProperty(String key) {
        return messageSource.getMessage(key, null, locale);
    }
}
