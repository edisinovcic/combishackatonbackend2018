package com.combishackaton.app.mailing.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ConfirmationMail extends Mail {

    private String recipient;
    private String confirmationUrl;

    public ConfirmationMail(String recipient, String confirmationUrl) {
        this.recipient = recipient;
        this.confirmationUrl = confirmationUrl;
    }

    @Override
    public String getRecipient() {
        return recipient;
    }

    @Override
    public String getSubject() {
        return getLocalizedMessageProperty("confirmation.subject");
    }

    @Override
    public String getBody() {
        org.thymeleaf.context.Context context = new org.thymeleaf.context.Context(getLocale());
        context.setVariable("confirmationUrl", confirmationUrl);
        context.setVariable("leadingText", getLocalizedMessageProperty("confirmation.leading-text"));
        context.setVariable("confirmButtonText", getLocalizedMessageProperty("confirmation.confirm-button"));

        return super.getTemplateEngine().process("confirmation-mail", context);
    }
}
