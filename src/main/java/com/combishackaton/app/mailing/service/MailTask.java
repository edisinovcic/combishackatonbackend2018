package com.combishackaton.app.mailing.service;

import com.combishackaton.app.mailing.model.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTask implements Runnable {

    private final Logger log = LoggerFactory.getLogger(MailTask.class);

    private final Session session;
    private final Mail mail;
    private final String username;

    MailTask(Session session, Mail mail, String username) {
        this.session = session;
        this.mail = mail;
        this.username = username;
    }

    @Override
    public void run() {
        try {
            MimeMessage mimeMessage = createMessage(mail, username);
            Transport.send(mimeMessage);
            log.info("Sent mail to {}", mail.getRecipient());
        } catch(Exception ex) {
            log.error("Could not send mail to {}", mail.getRecipient(), ex);
        }
    }

    private MimeMessage createMessage(Mail mail, String username) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getRecipient()));

        message.setContent(mail.getBody(), "text/html; charset=utf-8");
        message.setSubject(mail.getSubject());
        return message;
    }
}
