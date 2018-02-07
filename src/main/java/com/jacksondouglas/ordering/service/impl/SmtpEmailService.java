package com.jacksondouglas.ordering.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {

    private static final Logger log = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendEmail(SimpleMailMessage mail) {
        log.info("Trying to send the email...");
        mailSender.send(mail);
        log.info("Email has been sent.");
    }
}
