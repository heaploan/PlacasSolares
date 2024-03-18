package com.actividad.model;

public class Device {
    private String description;
    private int power;
    private boolean swtch;

    // Constructor
    public Device(String description, int power) {
        this.description = description;
        this.power = power;
        this.swtch = false;
    }

    // Getter
    public String getDescription() {
        return description;
    }

    public int getPower() {
        return power;
    }

    public boolean isSwtch() {
        return swtch;
    }

    // Setter
    public void setSwtch(boolean swtch) {
        this.swtch = swtch;
    }
}
