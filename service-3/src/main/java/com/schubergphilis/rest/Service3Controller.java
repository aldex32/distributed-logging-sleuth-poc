package com.schubergphilis.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service3Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Service3Controller.class);

    @RequestMapping(value = "/")
    public String home() {

        LOGGER.info("Service3 called");

        return "Service3 called";
    }
}
