package com.example.hueemulatorapp.Application;

import android.util.Log;

import com.example.hueemulatorapp.Data.DimLight;
import com.example.hueemulatorapp.Data.HueLight;
import com.example.hueemulatorapp.Data.Lamp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonData {

    public static final String TAG = JsonData.class.getName();

    public static final String lights = "/api/newdeveloper/lights";
    public static final String setState = "/state";
    public static final String uri = "http://10.0.2.2:8000";


    public static String getBodyRename(String name){
        return "{\"name\":" + "\"" + name + "\"}";
    }



    public static String getBodyLightOn(Boolean lightOn){
        return "{\"on\":" + "\"" + lightOn.toString() + "\"}";
    }

    public static String getBodyColor(int hue, int bri, int sat){
        return "{\"hue\":" + "\"" + hue + "\"," + "\"bri\":" + "\"" + bri + "\"," + "\"sat\":" + "\"" + sat + "\"}";
    }

    public static String getBodyBrightness(int bri) {

        JSONObject body = new JSONObject();
        try {
            body.put("bri", bri);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return body.toString();
    }

    public static ArrayList<DimLight> readGetLightsResponse(String jsonResponse) throws JSONException {

        Log.i(JsonData.class.getName(), jsonResponse);
        JSONObject response = new JSONObject(jsonResponse);
        ArrayList<DimLight> lights = new ArrayList<>();

        boolean done = false; int indexLight = 1; JSONObject addingJson;
        while (!done){
            try{
                addingJson = response.getJSONObject(String.valueOf(indexLight));

                if (isDimLight(addingJson)){
                    lights.add(getDimLight(String.valueOf(indexLight), addingJson));
                } else {
                    lights.add(getHueLight(String.valueOf(indexLight), addingJson));
                }


            } catch (JSONException e){
                done = true;
                Log.d(TAG, "Stopped finding lights at: " + indexLight);
            }
            indexLight++;
        }

        return lights;
    }

    private static boolean isDimLight(JSONObject lampJson) throws JSONException {
        return lampJson.getString("type").equals("Dimmable light");
    }


    private static DimLight getDimLight(String index, JSONObject addingJson) throws JSONException {
        JSONObject state = addingJson.getJSONObject("state");
        return new DimLight(
                index,
                addingJson.getString("uniqueid"),
                addingJson.getString("name"),
                addingJson.getString("modelid"),
                state.getBoolean("on"),
                state.getInt("bri")
                );
    }

    private static HueLight getHueLight(String index, JSONObject addingJson) throws JSONException {

        JSONObject state = addingJson.getJSONObject("state");
        return new HueLight(
                index,
                addingJson.getString("uniqueid"), //Get unique ID
                addingJson.getString("name"), //Get Name
                addingJson.getString("modelid"), //Get model ID
                state.getBoolean("on"),
                state.getInt("hue"), // Get brightness
                state.getInt("sat"), // Get Hue
                state.getInt("bri"), // Get saturation
                state.getString("effect") // Get effect
        );
    }
}
