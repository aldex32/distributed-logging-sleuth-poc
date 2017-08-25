package com.schubergphilis;

import com.schubergphilis.rest.HelloService;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Service4Configuration {

    public static void main(String[] args) {
        SpringApplication.run(Service4Configuration.class, args);
    }

    @Bean
    Sampler sampler() {
        return info -> true;
    }

    @Autowired
    private Bus bus;

    @Bean
    public Server rsServer() {
        final JAXRSServerFactoryBean serverFactory = new JAXRSServerFactoryBean();
        serverFactory.setBus(bus);
        serverFactory.setAddress("/");
        serverFactory.setServiceBeans(Arrays.asList(new HelloService()));

        return serverFactory.create();
    }
}
