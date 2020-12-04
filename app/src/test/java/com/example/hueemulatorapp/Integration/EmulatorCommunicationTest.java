package com.example.hueemulatorapp.Integration;

import com.example.hueemulatorapp.Application.HttpClient;
import com.example.hueemulatorapp.Application.JsonData;
import com.example.hueemulatorapp.Data.HttpParser;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * For these tests, please use the testConfig.json for the Hue Emulator included
 * (Which is the standard config with an extra lux bulb)
 */

class EmulatorCommunicationTest {


    @Test
    void getLightsTest() throws IOException {
        final String expected =
                "{" +
                        "\"1\":" +
                        "{\"modelid\":\"LCT001\"," +
                        "\"name\":\"Hue Lamp 1\"," +
                        "\"swversion\":\"65003148\"," +
                        "\"state\":" +
                        "{\"xy\":[0,0],\"ct\":0," +
                        "\"alert\":\"none\"," +
                        "\"sat\":254," +
                        "\"effect\":\"none\"," +
                        "\"bri\":254," +
                        "\"hue\":4444," +
                        "\"colormode\":\"hs\"," +
                        "\"reachable\":true," +
                        "\"on\":true}," +
                        "\"type\":\"Extended color light\"," +
                        "\"pointsymbol\":" +
                        "{\"1\":\"none\"," +
                        "\"2\":\"none\"," +
                        "\"3\":\"none\"," +
                        "\"4\":\"none\"," +
                        "\"5\":\"none\"," +
                        "\"6\":\"none\"," +
                        "\"7\":\"none\"," +
                        "\"8\":\"none\"}," +
                        "\"uniqueid\":\"00:17:88:01:00:d4:12:08-0a\"" +
                        "}," +
                        "\"2\":" +
                        "{\"modelid\":\"LCT001\",\"name\":\"Hue Lamp 2\",\"swversion\":\"65003148\"," +
                        "\"state\":{\"xy\":[0.346,0.3568],\"ct\":201,\"alert\":\"none\",\"sat\":144,\"effect\":\"none\",\"bri\":254,\"hue\":23536,\"colormode\":\"hs\",\"reachable\":true,\"on\":true}," +
                        "\"type\":\"Extended color light\",\"pointsymbol\":{\"1\":\"none\",\"2\":\"none\"," + "\"3\":\"none\",\"4\":\"none\",\"5\":\"none\",\"6\":\"none\",\"7\":\"none\",\"8\":\"none\"},\"uniqueid\":\"00:17:88:01:00:d4:12:08-0b\"}," +
                        "\"3\":" +
                        "{\"modelid\":\"LCT001\",\"name\":\"Hue Lamp 3\",\"swversion\":\"65003148\"," +
                        "\"state\":{\"xy\":[0.346,0.3568],\"ct\":201,\"alert\":\"none\",\"sat\":254,\"effect\":\"none\",\"bri\":254,\"hue\":65136,\"colormode\":\"hs\",\"reachable\":true,\"on\":true}," +
                        "\"type\":\"Extended color light\",\"pointsymbol\":{\"1\":\"none\",\"2\":\"none\",\"3\":\"none\",\"4\":\"none\",\"5\":\"none\",\"6\":\"none\",\"7\":\"none\",\"8\":\"none\"},\"uniqueid\":\"00:17:88:01:00:d4:12:08-0c\"}}";
        Request getLightsRequest = HttpClient.getRequest(HttpParser.getInstance().getLights());
        HttpClient.getInstance().send(getLightsRequest, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Assert.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Assert.assertEquals(expected, response.body().string());
            }
        });
    }

    @Test
    void renameTest() throws IOException {
        final String renameName = "rename";
        String index = "1";

        final String expected = "[{\"success\":{\"/lights/" + index + "/name\":\"" + renameName + "\"}}]\n";

        Request requestRename = HttpClient.putRequest(HttpParser.getInstance().rename(Integer.parseInt(index)), JsonData.getBodyRename(renameName));
        HttpClient.getInstance().send(requestRename, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Assert.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Assert.assertEquals(expected, response.body().string());
            }
        });
    }

//    @Test
//    void changeBrightnessTest() throws IOException {
//        int bri = 140;
//        final String expected = "[{\"success\":{\"/lights/4/state/bri\":" + bri + "}}]";
//        Request requestBrightness = HttpClient.putRequest(HttpParser.getInstance().setState(bri), JsonData.getBodyBrightness(bri));
//        HttpClient.getInstance().send(requestBrightness, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Assert.fail();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Assert.assertEquals(expected, response.body().string());
//            }
//        });
//    }

//    @Test
//    void changePowerOnTest() throws IOException {
//        testPower(true, 3);
//        boolean isOn = true;
//        int index = 3;
//        final String expected = "[{\"success\":{\"/lights/" + index + "/state/on\":" + isOn + "}}]";
//        Request requestPower = HttpClient.putRequest(HttpParser.getInstance().setState(index), JsonData.getBodyLightOn(isOn));
//        HttpClient.getInstance().send(requestPower, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Assert.fail();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Assert.assertEquals(expected, response.body().string());
//            }
//        });
//    }

//    void testPower(boolean isOn, int index) throws IOException {
//        final String expected = "[{\"success\":{\"/lights/" + index + "/state/on\":" + isOn + "}}]";
//        Request requestPower = HttpClient.putRequest(HttpParser.getInstance().setState(index), JsonData.getBodyLightOn(isOn));
//        HttpClient.getInstance().send(requestPower, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Assert.fail();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Assert.assertEquals(expected, response.body().string());
//            }
//        });
//    }
}
