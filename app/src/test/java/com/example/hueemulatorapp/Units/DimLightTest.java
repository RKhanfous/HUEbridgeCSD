package com.example.hueemulatorapp.Units;

import com.example.hueemulatorapp.Data.DimLight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DimLightTest {

    DimLight makeTestVar(){
        DimLight dimLight = new DimLight(1, "TESTID", "TESTOBJECT", "TESTMODEL", true, 254);
        return dimLight;
    }

    @Test
    void getIndexTest() {
        DimLight testDimLight = makeTestVar();
        int output = 1;
        assertEquals(output, testDimLight.getIndex());
    }

    @Test
    void getTypeTest() {
        DimLight testDimLight = makeTestVar();
        String output = "Dimmable light";
        assertEquals(output, testDimLight.getType());
    }

    @Test
    void getHSVTest() {
        DimLight testDimLight = makeTestVar();
        float[] output = new float[3];
        output[0] = 0.142f * 360f;
        output[1] = 0.64f;
        output[2] = (float)testDimLight.getBri()/254;
        assertArrayEquals(output, testDimLight.getHSV());
    }

    @Test
    void getIdTest() {
        DimLight testDimLight = makeTestVar();
        String output = "TESTID";
        assertEquals(output, testDimLight.getId());
    }

    @Test
    void getModelIdTest() {
        DimLight testDimLight = makeTestVar();
        String output = "TESTMODEL";
        assertEquals(output, testDimLight.getModelId());
    }

    @Test
    void getNameTest() {
        DimLight testDimLight = makeTestVar();
        String output = "TESTOBJECT";
        assertEquals(output, testDimLight.getName());
    }

    @Test
    void setNameTest() {
        DimLight testDimLight = makeTestVar();
        testDimLight.setName("NEWTESTNAME");
        assertEquals("NEWTESTNAME", testDimLight.getName());
    }

    @Test
    void isOnTest() {
        DimLight testDimLight = makeTestVar();
        Boolean output = true;
        assertEquals(output, testDimLight.isOn());
    }

    @Test
    void setOnTest() {
        DimLight testDimLight = makeTestVar();
        testDimLight.setOn(false);
        assertFalse(testDimLight.isOn());
    }

    @Test
    void getBriTest() {
        DimLight testDimLight = makeTestVar();
        int output = 254;
        assertEquals(output, testDimLight.getBri());
    }

    @Test
    void setBriTest() {
        DimLight testDimLight = makeTestVar();
        testDimLight.setBri(123);
        assertEquals(123, testDimLight.getBri());
    }
}