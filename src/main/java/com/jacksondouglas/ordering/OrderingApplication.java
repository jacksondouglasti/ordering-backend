package com.jacksondouglas.ordering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class OrderingApplication {

    /**
     * Mode all application*.yml to /config/ordering/ folder.
     * It's to avoid committing the configuration files with real credentials.
     * @param args
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder(OrderingApplication.class).properties("spring.config.location:/config/ordering/").build().run(args);
    }
}
