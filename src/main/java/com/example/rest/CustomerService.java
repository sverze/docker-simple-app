package com.example.rest;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customers")
public class CustomerService {

    private final CopyOnWriteArrayList<Customer> cList = CustomerList.getInstance();

    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllCustomers() {
//        Main.jedis.incr("customer_all_hits");
        return "---Customer List---\n"
                + cList.stream()
                .map(Customer::toString)
                .collect(Collectors.joining("\n"));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCustomer(@PathParam("id") long id) {
//        Main.jedis.incr("customer_individual_hits");
        Optional<Customer> match = cList.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        return match.map(customer -> "---Customer---\n" + customer.toString()).orElse("Customer not found");
    }
}