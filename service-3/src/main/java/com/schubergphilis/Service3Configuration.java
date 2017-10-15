package com.schubergphilis;

import com.schubergphilis.api.HelloService;
import org.apache.cxf.jaxrs.client.spring.JaxRsProxyClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class Service3Configuration {

    public static void main(String[] args) {
        SpringApplication.run(Service3Configuration.class, args);
    }

    @Bean
    Sampler sampler() {
        return info -> true;
    }

    @Configuration
    static class HelloServiceClientConfiguration extends JaxRsProxyClientConfiguration {

        @Override
        protected Class<?> getServiceClass() {
            return HelloService.class;
        }
    }
}
