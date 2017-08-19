package com.schubergphilis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableBinding({Sink.class, Source.class})
@IntegrationComponentScan
public class Service2Configuration {

    public static void main(String[] args) {
        SpringApplication.run(Service2Configuration.class, args);
    }

    @Bean
    Sampler sampler() {
        return info -> true;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
