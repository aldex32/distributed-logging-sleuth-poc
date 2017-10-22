package com.schubergphilis.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

@MessageEndpoint
public class MessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

    @Value("${service3.url:http://localhost:8084/hello}")
    private String service3Url;

    @Autowired
    private Source source;

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void onMessage(Message<String> msg) {
        LOGGER.info("Message received from topic1: {}", msg.getPayload());
        LOGGER.info("Headers received from topic1: {}", msg.getHeaders());

        LOGGER.info("Invoking service-3 asynchronously");
        sendMessage("test");
//        final ListenableFuture<ResponseEntity<String>> futureResponse = asyncRestTemplate.getForEntity(service3Url, String.class);
//
//        futureResponse.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
//
//            @Override
//            public void onSuccess(ResponseEntity<String> result) {
//                final String body = result.getBody();
//                LOGGER.info("Response from service-3: {}", body);
//
//                sendMessage(body);
//            }
//
//            @Override
//            public void onFailure(Throwable ex) {
//
//            }
//        });
    }

    private void sendMessage(String message) {
        LOGGER.info("Sending message to topic2: {}", message);

        Message<String> payload = MessageBuilder.withPayload(message).build();
        source.output().send(payload);
    }
}
