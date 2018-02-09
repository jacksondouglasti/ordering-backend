package com.jacksondouglas.ordering.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockIEmailService extends AbstractIEmailService {

    private static final Logger log = LoggerFactory.getLogger(MockIEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage mail) {
        log.info("Simulating sending email...");
        log.info(mail.toString());
        log.info("email has been sent.");
    }
}
