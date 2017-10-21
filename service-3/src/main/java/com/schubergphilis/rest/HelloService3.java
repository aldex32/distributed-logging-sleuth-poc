package com.schubergphilis.rest;

import com.schubergphilis.api.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Service
public class HelloService3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloService3.class);

    @Autowired @Lazy
    private HelloService helloService4;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String home() {
        LOGGER.info("Service3 called");

        callService4();

        return "Hello from Service3";
    }

    private void callService4() {
        LOGGER.info("Calling Service4");

        final String response = helloService4.hello();

        LOGGER.info("Service4 response: {}", response);
    }
}