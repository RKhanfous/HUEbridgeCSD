package com.example.hueemulatorapp.Data;

public class DimLight {
    private String id;
    private String type;
    private String name;
    private String modelId;

    private boolean on;
    private int bri;

    public DimLight(String id, String type, String name, String modelId, boolean on, int bri) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.modelId = modelId;
        this.on = on;
        this.bri = bri;
    }

    public float[] getHSV(){
        return new float[]{Light.DIM_HUE, Light.DIM_SAT, (float)this.bri/Light.MAX_BRI};
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getModelId() {
        return modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int getBri() {
        return bri;
    }

    public void setBri(int bri) {
        this.bri = bri;
    }
}
