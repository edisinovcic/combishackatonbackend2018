package com.combishackaton.app.mailing.service;


import com.combishackaton.app.mailing.model.Mail;

public interface MailService {

    void sendAsync(Mail mail);
}
