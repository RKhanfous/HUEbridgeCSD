package com.example.hueemulatorapp.Application;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonData {

    public static String putJsonLightsRename(String name){
        JSONObject postdata = new JSONObject();
        try{
            postdata.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postdata.toString();
    }

    public static String putJsonLightsOnOff(Boolean OnOff){
        JSONObject postdata = new JSONObject();
        try{
            postdata.put("on", OnOff);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postdata.toString();
    }

    public static String putJsonLightsHueBriSat(int hue, int bri, int sat){
        JSONObject postdata = new JSONObject();
        try{
            postdata.put("hue", hue);
            postdata.put("bri", bri);
            postdata.put("sat", sat);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postdata.toString();
    }
    // json on/off
    // json hue/bri/sat


}
