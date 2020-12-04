package com.example.hueemulatorapp.Data;

import com.example.hueemulatorapp.Application.HttpClient;

public class HttpParser {

    private static final String lights = "/api/newdeveloper/lights";
    private static final String setState = "/state";
    private static final String uri = "http://10.0.2.2:";

    private int port;
    private static HttpParser instance = null;

    private HttpParser(){
    }

    public void setPort(int port){
        this.port = port;
    }

    public int getPort(){ return this.port; }

    public static HttpParser getInstance(){
        if (instance == null){
            instance = new HttpParser();
            instance.setPort(8000);
        }
        return instance;
    }

    public String getLights(){
        return uri + port + lights;
    }

    public String rename(int index){
        return uri + port + lights + "/" + index;
    }

    public String setState(int index){
        return uri + port + lights + "/" + index + setState;
    }


}
