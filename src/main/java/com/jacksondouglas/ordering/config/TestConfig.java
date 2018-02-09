package com.jacksondouglas.ordering.config;

import com.jacksondouglas.ordering.service.IEmailService;
import com.jacksondouglas.ordering.service.impl.MockIEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public IEmailService emailService() {
        return new MockIEmailService();
    }
}
