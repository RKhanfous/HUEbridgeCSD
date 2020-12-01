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
        Log.i(JsonData.class.getName(), jsonResponse);
        JSONObject response = new JSONObject(jsonResponse);
        ArrayList<Lamp> lamps = new ArrayList<>();
        boolean done = false; int indexLamp = 1; JSONObject addingJson;
        while (!done){
            try{
                addingJson = response.getJSONObject(String.valueOf(indexLamp));

                if (isDimLight(addingJson)){
                    lamps.add(getDimLamp(addingJson));
                } else {
                    lamps.add(getHueLamp(addingJson));
                }


            } catch (JSONException e){
                done = true;
                Log.d(TAG, "Stopped finding lights at: " + indexLamp);
            }
            indexLamp++;
        }

        return lamps;
    }

    private static boolean isDimLight(JSONObject lampJson) throws JSONException {
        return lampJson.getString("type").equals("Dimmable light");
    }


    private static Lamp getDimLamp(JSONObject addingJson) throws JSONException {
        JSONObject state = addingJson.getJSONObject("state");
        return new Lamp(
                addingJson.getString("uniqueid"), //Get unique ID
                addingJson.getString("type"), //Get type.
                addingJson.getString("name"), //Get Name
                addingJson.getString("modelid"), //Get model ID
                "dummy",
                state.getBoolean("on"), // Get lights on
                state.getInt("bri"), // Get brightness
                0, // Get Hue
                0, // Get saturation
                "none" // Get effect
        );
    }

    private static Lamp getHueLamp(JSONObject addingJson) throws JSONException {
        JSONObject state = addingJson.getJSONObject("state");
        return new Lamp(
                addingJson.getString("uniqueid"), //Get unique ID
                addingJson.getString("type"), //Get type.
                addingJson.getString("name"), //Get Name
                addingJson.getString("modelid"), //Get model ID
                "dummy",
                state.getBoolean("on"), // Get lights on
                state.getInt("bri"), // Get brightness
                state.getInt("hue"), // Get Hue
                state.getInt("sat"), // Get saturation
                state.getString("effect") // Get effect
        );
    }
}
