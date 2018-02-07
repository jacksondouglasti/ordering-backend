package com.jacksondouglas.ordering.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacksondouglas.ordering.domain.PaymentWithBoleto;
import com.jacksondouglas.ordering.domain.PaymentWithCard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    // https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PaymentWithBoleto.class);
                objectMapper.registerSubtypes(PaymentWithCard.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}