package com.hit.model;
import java.util.List;

public class Response {
    public String json;
    public List<Restaurant> rest;

    public Response(List<Restaurant> rests)
    {
        this.rest = rests;
    }


    public Response(String string) {
        json = string;
    }
    public Response()
    {

    }

    public String toString() {
        return  "{'Rests':" + rest + "', 'json':'" + json + "'}";
    }
}
