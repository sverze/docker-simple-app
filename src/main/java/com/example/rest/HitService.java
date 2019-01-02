package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Path("/hits")
public class HitService {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHits() {
        try {
            return new StringBuilder()
                    .append("---Customer Hist---\n")
                    .append("Customer All Hists - ")
                    .append(Main.jedis.get("customer_all_hits"))
                    .append("\n")
                    .append("Customer Individual Hists - ")
                    .append(Main.jedis.get("customer_individual_hits"))
                    .append("\n")
                    .toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }
}