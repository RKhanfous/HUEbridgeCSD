package com.example.hueemulatorapp.Activities;

import com.example.hueemulatorapp.Data.DimLight;
import com.example.hueemulatorapp.Data.HueLight;

public interface DetailFragmentReplacer {
    void replaceHue(HueLight light);
    void replaceDim(DimLight light);
}
