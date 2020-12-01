package com.example.hueemulatorapp.Data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LampTest {

    Lamp makeTestValues(){
        return new Lamp("00:17:88:01:00:d4:12:08-0a", "Extended color light", "Hue Lamp 1", "LCT001", true, 254, 4444, 254, "none");
    }
    @Test
    void getHSVTest() {
        Lamp lamp = makeTestValues();
        float[] hsvValues = new float[3];
        hsvValues[0] = (float)4444/(float)65535 * 360; //hue value
        hsvValues[1] = ((float)254/(float)254); //sat value
        hsvValues[2] = ((float)254/(float)254); //bri value

        assertArrayEquals(hsvValues, lamp.getHSV());
    }

    @Test
    void setNameTest() {
        Lamp lamp = makeTestValues();
        lamp.setName("redouan");

        assertEquals( "redouan", lamp.getName());
    }

    @Test
    void getBriTest() {
        Lamp lamp = makeTestValues();
        int bri = 254;

        assertEquals(bri, lamp.getBri());
    }

    @Test
    void setBriTest() {
        Lamp lamp = makeTestValues();
        lamp.setBri(200);

        assertEquals( 200, lamp.getBri());
    }

    @Test
    void getHueTest() {
        Lamp lamp = makeTestValues();
        int hue = 4444;

        assertEquals(hue, lamp.getHue());
    }

    @Test
    void setHueTest() {
        Lamp lamp = makeTestValues();
        lamp.setHue(2000);

        assertEquals( 2000, lamp.getHue());
    }

    @Test
    void getSatTest() {
        Lamp lamp = makeTestValues();
        int sat = 254;

        assertEquals(sat, lamp.getSat());
    }

    @Test
    void setSatTest() {
        Lamp lamp = makeTestValues();
        lamp.setSat(200);

        assertEquals( 200, lamp.getSat());
    }

    @Test
    void getEffect() {
        Lamp lamp = makeTestValues();
        String effect = "none";

        assertEquals(effect, lamp.getEffect());
    }

    @Test
    void setEffectTest() {
        Lamp lamp = makeTestValues();
        lamp.setEffect("colorloop");

        assertEquals("colorloop", lamp.getEffect());
    }

    @Test
    void getIdTest() {
        Lamp lamp = makeTestValues();
        String ID = "00:17:88:01:00:d4:12:08-0a";

        assertEquals(ID, lamp.getId());
    }

    @Test
    void getTypeTest() {
        Lamp lamp = makeTestValues();
        String type = "Extended color light";

        assertEquals(type, lamp.getType());
    }

    @Test
    void getNameTest() {
        Lamp lamp = makeTestValues();
        String name = "Hue Lamp 1";

        assertEquals(name, lamp.getName());
    }

    @Test
    void getModelIdTest() {
        Lamp lamp = makeTestValues();
        String modelID = "LCT001";

        assertEquals(modelID, lamp.getModelId());
    }
}