package com.example.hueemulatorapp.Data;

public class HttpParser {

    private static final String lights = "/api/newdeveloper/lights";
    private static final String setState = "/state";
    private static final String uri = "http://10.0.2.2:8000";

    public static String getLights(){
        return uri + lights;
    }

    public static String rename(int index){
        return uri + lights + "/" + index;
    }

    public static String setState(int index){
        return uri + lights + "/" + index + setState;
    }


}
