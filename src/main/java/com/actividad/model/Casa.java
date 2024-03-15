package com.actividad.model;

import java.util.ArrayList;

public class Casa {
    private String nif;
    private String name;
    private int surface;
    private boolean swtch;
    private ArrayList<Placa> placas;
    public Casa(String nif, String name, int surface){
        this.nif = nif;
        this.name = name;
        this.surface = surface;
        this.swtch = true;
        placas = new ArrayList<>();
    }

    public void addPlaca(Placa placa) {
        placas.add(placa);
    }

    public String getNif(){
        return nif;
    }
    public String getName(){
        return name;
    }
    public int getSurface(){
        return surface;
    }
    public boolean isSwtch() {
        return swtch;
    }
    public ArrayList<Placa> getPlacas() {
        return placas;
    }
}
