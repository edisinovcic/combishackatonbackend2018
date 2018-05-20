package com.combishackaton.app.mailing.model;

import lombok.EqualsAndHashCode;
import org.thymeleaf.context.Context;

@EqualsAndHashCode(callSuper = true)
public class LoginAttemptMail extends Mail {

    private String recipient;
    private String resetUrl;

    public LoginAttemptMail(String recipient, String resetUrl) {
        this.recipient = recipient;
        this.resetUrl = resetUrl;
    }

    @Override
    public String getRecipient() {
        return recipient;
    }

    @Override
    public String getSubject() {
        return getLocalizedMessageProperty("login-attempt.subject");
    }

    @Override
    public String getBody() {
        Context context = new Context(getLocale());
        context.setVariable("resetUrl", resetUrl);
        context.setVariable("leadingText", getLocalizedMessageProperty("login-attempt.leading-text"));
        context.setVariable("confirmButtonText", getLocalizedMessageProperty("login-attempt.confirm-button"));

        return getTemplateEngine().process("login-attempt-mail", context);
    }

}
