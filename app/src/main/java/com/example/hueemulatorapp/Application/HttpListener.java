package com.example.hueemulatorapp.Application;

import com.example.hueemulatorapp.Data.DimLight;
import com.example.hueemulatorapp.Data.Lamp;

import java.util.List;

public interface HttpListener {
    void onLightsAvailable(List<DimLight> lights);
}
