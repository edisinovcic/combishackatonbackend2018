package com.combishackaton.app.mailing.service;

import com.combishackaton.app.mailing.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private String port;

    @Value("${mail.smtp.username}")
    private String username;

    @Value("${mail.smtp.password:}")
    private String password;

    @Value("${mail.smtp.auth:false}")
    private String useAuthentication;

    @Value("${mail.smtp.starttls.enable:false}")
    private String useSecureProtocol;

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private Session session;

    @Autowired
    public MailServiceImpl(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    @PostConstruct
    private void init() {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", port);
        props.setProperty("mail.smtp.auth", useAuthentication);
        props.setProperty("mail.smtp.starttls.enable", useSecureProtocol);

        if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        } else {
            session = Session.getDefaultInstance(props);
        }
    }

    public void sendAsync(Mail mail) {
        threadPoolTaskExecutor.execute(new MailTask(session, mail, username));
    }
}
