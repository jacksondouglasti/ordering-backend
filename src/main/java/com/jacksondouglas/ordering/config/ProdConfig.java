package com.jacksondouglas.ordering.config;

import com.jacksondouglas.ordering.service.IEmailService;
import com.jacksondouglas.ordering.service.impl.SmtpIEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    @Bean
    public IEmailService emailService() {
        return new SmtpIEmailService();
    }
}
