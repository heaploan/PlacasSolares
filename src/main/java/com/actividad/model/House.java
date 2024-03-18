package com.actividad.model;

import java.util.ArrayList;

public class House {
    private String nif;
    private String name;
    private int surface;
    private boolean swtch;
    private ArrayList<Panel> panels;
    private ArrayList<Device> devices;

    // Constructor
    public House(String nif, String name, int surface){
        this.nif = nif;
        this.name = name;
        this.surface = surface;
        this.swtch = true;
        panels = new ArrayList<>();
        devices = new ArrayList<>();
    }
    // Adding
    public void addPanel(Panel panel) {
        panels.add(panel);
    }
    public void addDevice(Device device){
        devices.add(device);
    }

    // Getter
    public String getNif(){
        return nif;
    }
    public String getName(){
        return name;
    }
    public int getHouseSurface(){
        return surface;
    }
    public boolean isSwtch() {
        return swtch;
    }
    public ArrayList<Panel> getPanels() {
        return panels;
    }
    public ArrayList<Device> getDevices() {
        return devices;
    }

    // Setter
    public void setSwtch(boolean swtch) {
        this.swtch = swtch;
    }

}
