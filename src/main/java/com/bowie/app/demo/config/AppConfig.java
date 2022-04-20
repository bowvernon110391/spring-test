package com.bowie.app.demo.config;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${api-key:<undefined>}")
    private String apiKey;

//    some beans
    @Bean
    Faker getFaker() {
        return new Faker();
    }

    @Override
    public String toString() {
        return String.format("===> API KEY: %s\n", apiKey);
    }
}
