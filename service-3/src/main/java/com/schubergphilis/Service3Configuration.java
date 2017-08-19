package com.schubergphilis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Service3Configuration {

    public static void main(String[] args) {
        SpringApplication.run(Service3Configuration.class, args);
    }

    @Bean
    Sampler sampler() {
        return info -> true;
    }
}
