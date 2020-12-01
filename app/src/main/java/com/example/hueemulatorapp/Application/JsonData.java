package com.example.hueemulatorapp.Application;

import android.util.Log;

import com.example.hueemulatorapp.Data.Lamp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonData {

    public static final String TAG = JsonData.class.getName();

    public static final String lights = "/api/newdeveloper/lights";
    public static final String setState = "/state";
    public static final String uri = "http://192.168.178.192:8000";


    public static String getBodyRename(String name){
        return "{\"name\":" + "\"" + name + "\"}";
    }



    public static String getBodyLightOn(Boolean lightOn){
        return "{\"on\":" + "\"" + lightOn.toString() + "\"}";
    }

    public static String getBodyColor(int hue, int bri, int sat){
        return "{\"hue\":" + "\"" + hue + "\"," + "\"bri\":" + "\"" + bri + "\"," + "\"sat\":" + "\"" + sat + "\"}";
    }

    public static ArrayList<Lamp> readGetLightsResponse(String jsonResponse) throws JSONException {
        JSONObject response = new JSONObject(jsonResponse);
        ArrayList<Lamp> lamps = new ArrayList<>();
        boolean done = false; int indexLamp = 1; Lamp addingLamp; JSONObject addingJson;
        while (!done){
            try{
                addingJson = response.getJSONObject(String.valueOf(indexLamp));
                JSONObject state = addingJson.getJSONObject("state");
                JSONObject config = addingJson.getJSONObject("config");

                addingLamp = new Lamp(
                        config.getString("uniqueid"), //Get unique ID
                        addingJson.getString("type"), //Get type.
                        addingJson.getString("name"), //Get Name
                        addingJson.getString("modelid"), //Get model ID
                        state.getBoolean("on"), // Get lights on
                        state.getInt("bri"), // Get brightness
                        state.getInt("hue"), // Get Hue
                        state.getInt("sat"), // Get saturation
                        state.getString("effect") // Get effect
                );

                lamps.add(addingLamp);

            } catch (JSONException e){
                done = true;
                Log.d(TAG, "Stopped finding lights at: " + indexLamp);
            }
        }

        return lamps;
    }
}
