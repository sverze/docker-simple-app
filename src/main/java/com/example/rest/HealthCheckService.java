package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/health")
public class HealthCheckService {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHealthStatus() {
        return "Healthy!";
    }
}