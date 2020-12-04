package com.example.hueemulatorapp.Data;

public class HttpParser {

    private static final String lights = "/api/newdeveloper/lights";
    private static final String setState = "/state";
    private static final String uri = "http://10.0.2.2:8000";

    public static String GetLights(){
        return uri + lights;
    }

    public static String Rename(String id){
        return uri + lights + "/" + id;
    }

    public static String SetState(String id){
        return uri + lights + "/" + id + setState;
    }


}
