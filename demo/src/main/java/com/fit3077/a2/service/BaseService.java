package com.fit3077.a2.service;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class BaseService {

    protected String myApiKey ="mBTfc6DhJ7JNpdpRwLWM88MRcLnmpD";

    //sending get request
    protected HttpResponse<String> getRequest(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Sending post request
    protected HttpResponse postRequest(String url, JSONObject obj){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(obj.toString()))
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Sending patch request
    protected Boolean patchRequest(String url, JSONObject obj) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .setHeader("Authorization", myApiKey)
                .method("PATCH", HttpRequest.BodyPublishers.ofString(obj.toString()))
                .header("Content-Type","application/json")
                .build();

        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return true;
            } else return false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Sending delete request
    protected Boolean deleteRequest(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .setHeader("Authorization", myApiKey)
                .DELETE()
                .build();

        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 204) {
                return true;
            } else return false;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

}

