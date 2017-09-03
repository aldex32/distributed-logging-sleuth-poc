package com.schubergphilis.rest;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Service
public class HelloService3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloService3.class);

    @Value("${service4.url:http://localhost:8085}")
    private String service4Url;

    @Autowired
    private WebClient webClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String home() {
        LOGGER.info("Service3 called");

        callService4();

        return "Hello from Service3";
    }

    private void callService4() {
        LOGGER.info("Calling Service4");

        final String response = webClient.get().readEntity(String.class);

        LOGGER.info("Service4 response: {}", response);
    }
}
