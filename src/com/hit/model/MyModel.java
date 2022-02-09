package com.hit.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hit.client.Request;
import com.hit.client.Response;
import com.hit.client.Restaurant;

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


    public List<Restaurant> getByCategory(String category)  {
        String action = "GetCategory";
        Map <String, String> headers = new HashMap <>();
        headers.put("action", action);
        response = sendRequest(headers, category);
        return response.rest;
    }


    public List<Restaurant> getByName(String name){

        String action = "GetName";
        Map <String, String> headers = new HashMap <>();
        headers.put("action", action);
        response = sendRequest(headers, name);
        return response.rest;
    }

    public String addUpdateRest(List<String> input) {

        String action = "Add/Update";
        Map <String, String> headers = new HashMap <>();
        headers.put("action", action);
        headers.put("Category", input.get(0));
        headers.put("Name", input.get(1));
        headers.put("Address", input.get(2));
        headers.put("City", input.get(3));
        headers.put("PhoneNumber", input.get(4));
        headers.put("Rating", input.get(5));
        response = sendRequest(headers, "Add/Update");
        return response.json;

    }

    public String deleteRest(String restNameDelete) {
        String action = "Delete";
        Map <String, String> headers = new HashMap <>();
        headers.put("action", action);
        response = sendRequest(headers, restNameDelete);
        return response.json;
    }

    public  Response sendRequest(Map <String, String> headers, String body)  {

        try {
            toServer = new Socket("localhost", port);
            writer = new PrintWriter(toServer.getOutputStream());
            reader = new Scanner(toServer.getInputStream());
            request = new Request(headers, body);
            writer.println(gson.toJson(request));
            writer.flush();
            Type type = new TypeToken <Response>() {
            }.getType();
            response = gson.fromJson(reader.next(), type);
            writer.close();
            reader.close();
            toServer.close();
            return response;
        }
        catch (Exception ex){ ex.printStackTrace();}
        return new Response("Error");

    }

}
