package com.example.hueemulatorapp.Application;

import android.util.JsonReader;
import android.util.Log;

import com.example.hueemulatorapp.Data.DimLight;
import com.example.hueemulatorapp.Data.HueLight;
import com.example.hueemulatorapp.Data.Lamp;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

public class JsonData {

    public static final String TAG = JsonData.class.getName();

    public static final String lights = "/api/newdeveloper/lights";
    public static final String setState = "/state";
    public static final String uri = "http://10.0.2.2:8000";


    public static String getBodyRename(String name){
        JSONObject body = new JSONObject();
        try{
            body.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return body.toString();
    }

    public static String getBodyLightOn(Boolean lightOn){
        JSONObject body = new JSONObject();
        try{
            body.put("on", lightOn);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return body.toString();
    }

    public static String getBodyColor(int hue, int bri, int sat){
        JSONObject body = new JSONObject();
        try{
            body.put("hue", hue);
            body.put("bri", bri);
            body.put("sat", sat);
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
                    lights.add(getDimLight(addingJson));
                } else {
                    lights.add(getHueLight(addingJson));
                }


            } catch (JSONException e){
                done = true;
                Log.d(TAG, "Stopped finding lights at: " + indexLight);
            }
            indexLight++;
        }

        return lights;
    }

    public static ArrayList<Lamp> readGetLampsResponse(String jsonResponse) throws JSONException {
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


    private static DimLight getDimLight(JSONObject addingJson) throws JSONException {
        JSONObject state = addingJson.getJSONObject("state");
        return new DimLight(
                addingJson.getString("uniqueid"),
                addingJson.getString("name"),
                addingJson.getString("modelid"),
                state.getBoolean("on"),
                state.getInt("bri")
                );
    }

    private static HueLight getHueLight(JSONObject addingJson) throws JSONException {

        JSONObject state = addingJson.getJSONObject("state");
        return new HueLight(
                addingJson.getString("uniqueid"), //Get unique ID
                addingJson.getString("name"), //Get Name
                addingJson.getString("modelid"), //Get model ID
                state.getBoolean("on"),
                state.getInt("bri"), // Get brightness
                state.getInt("hue"), // Get Hue
                state.getInt("sat"), // Get saturation
                state.getString("effect") // Get effect
        );
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
