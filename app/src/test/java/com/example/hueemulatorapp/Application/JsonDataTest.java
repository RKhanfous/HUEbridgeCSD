package com.example.hueemulatorapp.Application;

import com.example.hueemulatorapp.Data.Lamp;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.example.hueemulatorapp.Application.JsonData.getBodyColor;
import static com.example.hueemulatorapp.Application.JsonData.getBodyLightOn;
import static com.example.hueemulatorapp.Application.JsonData.getBodyRename;
import static com.example.hueemulatorapp.Application.JsonData.readGetLightsResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonDataTest {

    @Test
    void getBodyRenameTest() {
        String renamedLights =  "{\"name\":\"redouan\"}";
        assertEquals(renamedLights, getBodyRename("redouan"));
    }

    @Test
    void getBodyLightOnTest() {
        String bodyLight = "{\"on\":\"true\"}";
        assertEquals(bodyLight, getBodyLightOn(true));
    }

    @Test
    void getBodyColorTest() {
        String hueSatBriLight = "{\"hue\":\"10000\",\"bri\":\"123\",\"sat\":\"123\"}";
        assertEquals(hueSatBriLight, getBodyColor(10000, 123, 123));
    }

    @Test
    void readGetLightsResponseTest() throws JSONException {
        String lightResponse = "{\"1\":{\"modelid\":\"LCT001\",\"name\":\"Hue Lamp 1\",\"swversion\":\"65003148\",\"state\":{\"xy\":[0,0],\"ct\":0,\"alert\":\"none\",\"sat\":254,\"effect\":\"none\",\"bri\":254,\"hue\":4444,\"colormode\":\"hs\",\"reachable\":true,\"on\":true},\"type\":\"Extended color light\",\"pointsymbol\":{\"1\":\"none\",\"2\":\"none\",\"3\":\"none\",\"4\":\"none\",\"5\":\"none\",\"6\":\"none\",\"7\":\"none\",\"8\":\"none\"},\"uniqueid\":\"00:17:88:01:00:d4:12:08-0a\"}";
        Lamp lamp = new Lamp("00:17:88:01:00:d4:12:08-0a", "Extended color light", "Hue Lamp 1", "LCT001", true, 254, 4444, 254, "none");
        ArrayList<Lamp> lamps = new ArrayList<>();
        lamps.add(lamp);
        assertEquals(lamps, readGetLightsResponse(lightResponse));
    }
}