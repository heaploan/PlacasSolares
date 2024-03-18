package com.actividad.model;

public class Panel {
    private int surface;
    private float price;
    private int power;

    // Constructor
    public Panel(int surface, float price, int power ){
        this.surface = surface;
        this.price = price;
        this.power = power;
    }

    // Getter
    public int getPanelSurface() {
        return surface;
    }
    public float getPrice() {
        return price;
    }
    public int getPanelPower() {
        return power;
    }
}
