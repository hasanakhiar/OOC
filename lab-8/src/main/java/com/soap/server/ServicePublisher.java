package com.soap.server;

import javax.xml.ws.Endpoint;

public class ServicePublisher {
    public static void main(String[] args) {
        String url = "http://localhost:8080/ws/user";
        Endpoint.publish(url, new UserServiceImpl());
        System.out.println("Service is published at: " + url);
    }
}