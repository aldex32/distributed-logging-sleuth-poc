package com.schubergphilis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@EnableBinding({Source.class, Sink.class})
@IntegrationComponentScan
public class Service1Configuration {

    public static void main(String[] args) {
        SpringApplication.run(Service1Configuration.class, args);
    }

    @Bean
    Sampler sampler() {
        return info -> true;
    }
}
