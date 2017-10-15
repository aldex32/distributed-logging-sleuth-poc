package com.schubergphilis.rest;

import com.schubergphilis.api.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello() {
        LOGGER.info("Service4 called");

        return "Hello from service-4";
    }
}
