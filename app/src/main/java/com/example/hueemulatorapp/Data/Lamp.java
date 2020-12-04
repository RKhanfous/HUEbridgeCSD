package com.example.hueemulatorapp.Data;

import java.io.Serializable;

public class Lamp implements Serializable {

    private String id;
    private String type;
    private String name;
    private String modelId;
    private String productName;

    private boolean lightsOn;
    private int bri;
    private int hue;
    private int sat;
    private String effect;

    public static final int MIN_BRI = 1;
    public static final int MAX_BRI = 254;
    public static final int MIN_HUE = 0;
    public static final int MAX_HUE = 65535;
    public static final int MIN_SAT = 0;
    public static final int MAX_SAT = 254;
    public static final String EFFECT_NULL = "none";
    public static final String EFFECT_COLOR_LOOP = "colorloop";

    public Lamp(String id, String type, String name, String modelId, String productName, boolean lightsOn, int bri, int hue, int sat, String effect) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.modelId = modelId;
        this.productName = productName;
        this.lightsOn = lightsOn;
        this.bri = bri;
        this.hue = hue;
        this.sat = sat;
        this.effect = effect;
    }

    public static Lamp Dummy(){
        return new Lamp("id", "type", "name", "modelid", "productname", true, 0, 0, 0, "none");
    }

    public float[] getHSV(){
        return new float[]{(float)this.hue/(float)MAX_HUE * 360, (float)this.sat/(float)MAX_SAT, (float)this.bri/(float)MAX_BRI};
    }

    public String getProductName() {
        return productName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBri() {
        return bri;
    }

    public void setBri(int bri) {
        this.bri = bri;
    }

    public int getHue() {
        return hue;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getModelId() {
        return modelId;
    }
}
