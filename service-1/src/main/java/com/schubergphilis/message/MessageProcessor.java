package com.schubergphilis.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
public class MessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void onMessage(Message<String> msg) {
        LOGGER.info("Message received from topic2: {}", msg.getPayload());
        LOGGER.info("Headers received from topic2: {}", msg.getHeaders());
    }
}
