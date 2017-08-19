package com.schubergphilis.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.client.RestTemplate;

@MessageEndpoint
public class MessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Source source;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void onMessage(Message<String> msg) {
        LOGGER.info("Message received from topic1: {}", msg.getPayload());
        LOGGER.info("Headers received from topic1: {}", msg.getHeaders());

        LOGGER.info("Invoking service-3");
        final String response = restTemplate.getForObject("http://localhost:8084", String.class);

        LOGGER.info("Response from service-3: {}", response);

        LOGGER.info("Sending message to topic2: {}", response);

        Message<String> message = MessageBuilder.withPayload(response).build();
        source.output().send(message);
    }
}
