package com.hit.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hit.client.Request;
import com.hit.client.Response;
import com.hit.client.Restaurant;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyModel {
    public Gson gson;
    public PrintWriter writer;
    public Response response;
    public Request request;
    public Scanner reader;
    public Socket toServer;
    public int port;


    public MyModel(int port){
        gson = new GsonBuilder().create();
        this.port = port;
    }


    public List<Restaurant> getByCategory(String category) throws IOException {
        String action = "GetCategory";
        response = sendRequest(action, category);
        System.out.println(response.rest);
        return response.rest;
    }

    public  Response sendRequest(String action, String body) throws IOException {
        toServer = new Socket("localhost", port);
        writer = new PrintWriter(toServer.getOutputStream());
        reader = new Scanner(toServer.getInputStream());
        Map <String, String> headers = new HashMap <>();
        headers.put("action", action);
        Request request = new Request(headers, body);
        writer.println(gson.toJson(request));
        writer.flush();
        Type type = new TypeToken<Response>(){}.getType();
        return  gson.fromJson(reader.next(), type);
    }


    public List<Restaurant> getByName(String name) throws IOException {

        String action = "GetName";
        response = sendRequest(action, name);
        System.out.println(response.rest);
        return response.rest;

    }
}
