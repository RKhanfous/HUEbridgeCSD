package com.example.hueemulatorapp.Units;

import com.example.hueemulatorapp.Application.JsonData;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class JsonDataTest {


    @Test
    void getBodyRenameTest(){
        String expected = "{\"name\":\"rename\"}";
        Assert.assertEquals(expected, JsonData.getBodyRename("rename"));
    }

    @Test
    void getBodyLightOnTest(){
        boolean lightsOn =  false;
        String expectedOff = "{\"on\":\"" + lightsOn + "\"}";
        Assert.assertEquals(expectedOff, JsonData.getBodyLightOn(lightsOn));

        lightsOn = true;
        String expectedOn = "{\"on\":\"" + lightsOn + "\"}";
        Assert.assertEquals(expectedOn, JsonData.getBodyLightOn(lightsOn));
    }

//    @Test
//    void getBodyBrightnessTest() {
//        int bri = 107;
//        String expected = "{\"bri\":" + bri + "}";
//        Assert.assertEquals(expected, JsonData.getBodyBrightness(bri));
//    }
//
//    @Test
//    void getBodyColorTest() {
//        int hue = 41496;
//        int bri = 117;
//        int sat = 116;
//        String expected = "{\"hue\":\"" + hue + "\",\"bri\":\"" + bri + "\",\"sat\":\"116\"}";
//    }
//
//    @Test
//    void readGetLightsResponseTest() {
//    }
}
