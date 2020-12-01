package com.example.hueemulatorapp.Application;

import static com.example.hueemulatorapp.Application.JsonData.putJsonLightsRename;
import static org.junit.jupiter.api.Assertions.*;

class JsonDataTest {

    @org.junit.jupiter.api.Test
    void putJsonLightsRenameTest() {
        String renamedLights = "{\"name\" : \"redouan\"} ";
        assertEquals(renamedLights, putJsonLightsRename("redouan"));
    }

    @org.junit.jupiter.api.Test
    void putJsonLightsOnOffTest() {
    }

    @org.junit.jupiter.api.Test
    void putJsonLightsHueBriSatTest() {
    }
}