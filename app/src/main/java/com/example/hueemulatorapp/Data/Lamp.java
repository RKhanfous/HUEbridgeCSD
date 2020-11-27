package com.example.hueemulatorapp.Data;

import java.io.Serializable;

public class Lamp implements Serializable {

    private String id;
    private String type;
    private String name;
    private String modelId;
    private String productName;

    private int bri;
    private int hue;
    private int sat;
    private String effect;

    public final int MIN_BRI = 1;
    public final int MAX_BRI = 254;
    public final int MIN_HUE = 0;
    public final int MAX_HUE = 65535;
    public final int MIN_SAT = 0;
    public final int MAX_SAT = 254;

    public Lamp(String id, String type, String name, String modelId, String productName, int bri, int hue, int sat, String effect) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.modelId = modelId;
        this.productName = productName;
        this.bri = bri;
        this.hue = hue;
        this.sat = sat;
        this.effect = effect;
    }

    public float[] getHSV(){
        return new float[]{(float)this.hue/(float)MAX_HUE, (float)this.sat/(float)MAX_SAT, (float)this.bri/(float)MAX_BRI};
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
