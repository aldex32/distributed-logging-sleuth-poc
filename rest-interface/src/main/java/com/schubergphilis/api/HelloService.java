package com.schubergphilis.api;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sayHello")
@Service
public interface HelloService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String hello();

}
