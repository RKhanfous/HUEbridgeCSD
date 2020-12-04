package com.example.hueemulatorapp.Data;

public class HueLight extends DimLight {

    private int hue;
    private int sat;
    private String effect;

    public HueLight(String index, String id, String name, String modelId, boolean on, int hue, int sat, int bri, String effect) {
        super(index, id, name, modelId, on, bri);
        this.type = "Extended color light";
        this.hue = hue;
        this.sat = sat;
        this.effect = effect;
    }

    @Override
    public float[] getHSV() {
        return new float[]{(float)this.hue/(float)Light.MAX_HUE * 360f, (float)this.sat/(float)Light.MAX_SAT, (float)super.getBri()/(float)Light.MAX_BRI};
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
}
