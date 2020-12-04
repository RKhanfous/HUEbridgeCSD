package com.example.hueemulatorapp.Integration;

import com.example.hueemulatorapp.Data.HttpParser;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

class HttpParserTest {

    private static final int port = 8000;

    @BeforeAll @Test
    static void getInstanceTest(){
        Assert.assertEquals(port, HttpParser.getInstance().getPort());
    }

    @Test
    void getLightsTest(){
        String expected = "http://10.0.2.2:" + port + "/api/newdeveloper/lights";
        Assert.assertEquals(expected, HttpParser.getInstance().getLights());
    }

    @Test
    void renameTest(){
        int index = 1;
        String expected = "http://10.0.2.2:8000/api/newdeveloper/lights/" + index;
        Assert.assertEquals(expected, HttpParser.getInstance().rename(index));
    }

    @Test
    void setStateTest(){
        int index = 1;
        String expected = "http://10.0.2.2:8000/api/newdeveloper/lights/" + index + "/state";
        Assert.assertEquals(expected, HttpParser.getInstance().setState(index));
    }

    @AfterAll @Test
    static void setPortTest(){
        int port = 80;
        int index = 3;
        String expected = "http://10.0.2.2:" + port + "/api/newdeveloper/lights/" + index + "/state";
        HttpParser.getInstance().setPort(port);
        Assert.assertEquals(expected, HttpParser.getInstance().setState(index));
    }

}
