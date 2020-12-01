package com.example.hueemulatorapp.Application;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient {

    private static final String TAG = OkHttpClient.class.getSimpleName();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    public Request get(String url) throws IOException {
        Log.i(TAG, "Sending a get request with URL: " + url);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return request;
    }

    public Request put(String url, String json) throws IOException {
        Log.i(TAG, "Sending a put request with body:\n" + json + "\n to URL: " + url);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        return request;
    }

    public void send(Request request, Callback callback){
        httpClient.newCall(request).enqueue(callback);
    }
}
