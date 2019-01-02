package com.example.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static java.lang.String.format;

/**
 * Main class
 */
public class Main{

    // Base URI the Grizzly HTTP server will listen on
    static final String BASE_URI;
    private static final String protocol;
    private static final Optional<String> host;
    private static final Optional<String> port;
    static HttpServer httpServer;
    static Jedis jedis;

    static {
        protocol = "http://";
        host = Optional.ofNullable(System.getenv("HOSTNAME"));
        port = Optional.ofNullable(System.getenv("PORT"));
        BASE_URI = protocol + host.orElse("localhost") + ":" + port.orElse("8080") + "/";
    }

    Main() {
        System.out.println("Jersey app starting ...");
        // create a resource config that scans for JAX-RS resources and providers in com.example.rest package
        final ResourceConfig rc = new ResourceConfig().packages("com.example.rest");

        // create and start a new instance of grizzly http server exposing the Jersey application at BASE_URI
        httpServer =  GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        System.out.println(format("Jersey app started with WADL available at %sapplication.wadl", BASE_URI));

//        System.out.println("Connecting to Jedis ...");
//        jedis = new Jedis("redis-master", 6379);
//        System.out.println("Connected to Jedis");
    }


    private void shutdown() {
        System.out.println("Jersey app shutting down now");
        httpServer.shutdownNow();

//        System.out.println("Jedis shutting down now");
//        jedis.shutdown();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        Runtime.getRuntime().addShutdownHook(new Thread(main::shutdown));
    }
}
