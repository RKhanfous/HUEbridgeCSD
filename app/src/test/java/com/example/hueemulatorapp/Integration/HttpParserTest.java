package com.example.hueemulatorapp.Integration;

import com.example.hueemulatorapp.Data.HttpParser;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;

class HttpParserTest {


    @Test
    void getLightsTest(){
        String expected = "http://10.0.2.2:8000/api/newdeveloper/lights";
        Assert.assertEquals(expected, HttpParser.getLights());
    }

    @Test
    void renameTest(){
        int index = 1;
        String expected = "http://10.0.2.2:8000/api/newdeveloper/lights/" + index;
        Assert.assertEquals(expected, HttpParser.rename(index));
    }

    @Test
    void setStateTest(){
        int index = 1;
        String expected = "http://10.0.2.2:8000/api/newdeveloper/lights/" + index + "/state";
        Assert.assertEquals(expected, HttpParser.setState(index));
    }
}
