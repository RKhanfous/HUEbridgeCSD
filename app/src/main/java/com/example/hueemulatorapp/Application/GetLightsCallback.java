package com.example.hueemulatorapp.Application;

import android.util.Log;

import com.example.hueemulatorapp.Data.DimLight;
import com.example.hueemulatorapp.Data.Lamp;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GetLightsCallback implements Callback {

    HttpListener httpListener;

    public GetLightsCallback(HttpListener httpListener){
        this.httpListener = httpListener;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        e.printStackTrace();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        try {
            List<DimLight> lights = JsonData.readGetLightsResponse(response.body().string());
            for (DimLight light : lights) {
                Log.i(GetLightsCallback.class.getName(), "Found Lamp: " + light.getName());
            }
            httpListener.onLightsAvailable(lights);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
