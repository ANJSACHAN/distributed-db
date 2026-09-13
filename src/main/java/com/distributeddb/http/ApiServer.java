package com.distributeddb.http;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApiServer {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(
            new InetSocketAddress(8080),
            0
    );
    
    server.createContext("/data", exchange -> {
    
                String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        String[] parts = path.split("/");
        String key = parts[2];

        System.out.println("Method: " + method);
        System.out.println("Path: " + path);
        System.out.println("Key: " + key);
    });
    
    server.start();

    
        System.out.println("HTTP API server started on port 8080");
    }
}