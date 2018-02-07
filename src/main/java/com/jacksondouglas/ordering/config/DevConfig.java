package com.jacksondouglas.ordering.config;

import com.jacksondouglas.ordering.service.EmailService;
import com.jacksondouglas.ordering.service.impl.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
