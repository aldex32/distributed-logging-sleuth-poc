package com.schubergphilis.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service1Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Service1Controller.class);

    @Autowired
    private Source source;

    @RequestMapping(value = "/")
    public String home() {

        LOGGER.info("Service1 called");

        Message<String> message = MessageBuilder.withPayload("message1").build();

        LOGGER.info("Sending message '{}' to topic1");

        source.output().send(message);

        return "Service1 called";
    }
}
