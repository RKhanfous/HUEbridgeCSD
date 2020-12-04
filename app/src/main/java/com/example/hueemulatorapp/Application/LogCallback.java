package com.example.hueemulatorapp.Application;

import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LogCallback implements Callback {

    private String TAG;

    public LogCallback(String tag) {
        this.TAG = tag;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        e.printStackTrace();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        Log.e(TAG, response.body().string());
    }
}
