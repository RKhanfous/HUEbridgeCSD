package com.example.hueemulatorapp.Data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HueLightTest {

    HueLight makeTestVar(){
        HueLight hueLight = new HueLight(1, "TESTID", "TESTNAME", "TESTMODEL", true, 123, 123, 123, "TEST EFFECT");
        return hueLight;
    }

    @Test
    void getHSVTest() {
        HueLight testHueLight = makeTestVar();
        float[] output = new float[3];
        output[0] = (float)testHueLight.getHue()/65535f * 360f;
        output[1] = (float)testHueLight.getSat()/254f;
        output[2] = (float)testHueLight.getBri()/254f;
        assertArrayEquals(output, testHueLight.getHSV());
    }

    @Test
    void getHueTest() {
        HueLight testHueLight = makeTestVar();
        int output = 123;
        assertEquals(output, testHueLight.getHue());
    }

    @Test
    void setHueTest() {
        HueLight testHueLight = makeTestVar();
        testHueLight.setHue(321);
        assertEquals(321, testHueLight.getHue());
    }

    @Test
    void getSatTest() {
        HueLight testHueLight = makeTestVar();
        int output = 123;
        assertEquals(output, testHueLight.getSat());
    }

    @Test
    void setSatTest() {
        HueLight testHueLight = makeTestVar();
        testHueLight.setSat(321);
        assertEquals(321, testHueLight.getSat());
    }

    @Test
    void getEffectTest() {
        HueLight testHueLight = makeTestVar();
        String output = "TEST EFFECT";
        assertEquals(output, testHueLight.getEffect());
    }

    @Test
    void setEffectTest() {
        HueLight testHueLight = makeTestVar();
        testHueLight.setEffect("EFFECT TEST");
        assertEquals("EFFECT TEST", testHueLight.getEffect());
    }
}