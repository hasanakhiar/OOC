package com.soap.client;

import java.io.*;
import java.net.*;

public class ManualClient {

    public static void main(String[] args) {
        String url = "http://localhost:8080/ws/user";

        try {
            System.out.println("1. Creating User");
            sendSoapRequest(url, getCreateUserXml(101, "Alice", "pass123"), "createUser");

            System.out.println("2. Retrieving User\n");
            sendSoapRequest(url, getRetrieveUserXml(101), "retrieveUser");

            System.out.println("3. Updating User\n");
            sendSoapRequest(url, getUpdateUserXml(101, "AliceUpdated", "newpass"), "updateUser");

            System.out.println("4. Retrieving User (Verify Update)\n");
            sendSoapRequest(url, getRetrieveUserXml(101), "retrieveUser");

            System.out.println("5. Deleting User\n");
            sendSoapRequest(url, getDeleteUserXml(101), "deleteUser");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendSoapRequest(String urlString, String xmlInput, String method) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.setDoOutput(true);

        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.writeBytes(xmlInput);
            wr.flush();
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        }

        System.out.println("Response Raw: " + response.toString());
    }


    private static String getCreateUserXml(int id, String username, String password) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://server.soap.com/\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<ser:createUser>" +
                "<arg0>" + id + "</arg0>" +
                "<arg1>" + username + "</arg1>" +
                "<arg2>" + password + "</arg2>" +
                "</ser:createUser>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";
    }

    private static String getRetrieveUserXml(int id) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://server.soap.com/\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<ser:retrieveUser>" +
                "<arg0>" + id + "</arg0>" +
                "</ser:retrieveUser>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";
    }

    private static String getUpdateUserXml(int id, String username, String password) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://server.soap.com/\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<ser:updateUser>" +
                "<arg0>" + id + "</arg0>" +
                "<arg1>" + username + "</arg1>" +
                "<arg2>" + password + "</arg2>" +
                "</ser:updateUser>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";
    }

    private static String getDeleteUserXml(int id) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://server.soap.com/\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<ser:deleteUser>" +
                "<arg0>" + id + "</arg0>" +
                "</ser:deleteUser>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";
    }
}