package com.actividad.util;

import com.actividad.model.Device;
import com.actividad.model.House;
import com.actividad.model.Panel;

import java.util.ArrayList;

public class Option {

    private ArrayList<House> houses;

    public Option() {
        houses = new ArrayList<>();
    }

    public void addCasa(String[] commands) {
        if (Validator.valComLength(commands, 4)) {
            String nif = commands[1];
            if (!Validator.houseExists(houses, nif)) {
                String name = commands[2];
                int surface = Integer.parseInt(commands[3]);
                if (Validator.valHouseSurface(surface)) {
                    House newHouse = new House(nif, name, surface);
                    houses.add(newHouse);
                    System.out.println("Casa registrada correctamente");
                }
            } else {
                System.out.println("ERROR: Ya hay una casa registrada con ese NIF:");
            }
        }
    }

    public void addPanel(String[] commands) {
        if (Validator.valComLength(commands, 5)) {
            String nif = commands[1];
            if (Validator.houseExists(houses, nif)) {
                int surface = Integer.parseInt(commands[2]);
                float price = Float.parseFloat(commands[3]);
                int power = Integer.parseInt(commands[4]);
                Panel newPanel = new Panel(surface, price, power);
                for (House house : houses) {
                    if (Validator.houseExists(houses, nif)) {
                        if (Validator.valPanelSurface(house, surface)) {
                            int availableSurface = availableSurface(house);
                            if (availableSurface >= surface){
                            house.addPanel(newPanel);
                            System.out.println("Placa registrada correctamente.");
                            } else {
                                System.out.println("ERROR: No hay suficiente superficie");
                            }
                        }
                        break;
                    }
                }
            } else {
                System.out.println("ERROR: No se encontró ninguna casa registrada con ese NIF");
            }
        }
    }

    public void addDevice(String[] commands) {
        if (Validator.valComLength(commands, 4)) {
            String nif = commands[1];
            if (Validator.houseExists(houses, nif)) {
                String description = commands[2];
                int power = Integer.parseInt(commands[3]);
                if (Validator.valDevPower(power)) {
                    Device newDevice = new Device(description, power);
                    for (House house : houses) {
                        if (Validator.houseExists(houses, nif)) {
                            house.addDevice(newDevice);
                            System.out.println("Aparato registrado correctamente.");
                        }
                        break;
                    }
                }
            } else {
                System.out.println("ERROR: No se encontró ninguna casa registrada con ese NIF");
            }
        }
    }

    public void onHouse(String[] commands) {
        if (Validator.valComLength(commands, 2)) {
            String nif = commands[1];
            if (Validator.houseExists(houses, nif)) {
                for (House house : houses){
                    if (house.getNif().equalsIgnoreCase(nif)){
                        Validator.valSwtch(house);
                        break;
                    }
                }
            } else {
                System.out.println("ERROR: No se encontró ninguna casa registrada con ese NIF");
            }
        }
    }

    public void list(){
        System.out.println("--- Paneles Solares, S.L. ---");
        System.out.println("Casas registradas: " + houses.size());
        for (int i = 0; i < houses.size(); i++){
            House house = houses.get(i);
            System.out.println("Casa " + (i+1));
            System.out.println("Cliente: " + house.getNif() + " - " + house.getName());
            System.out.println("Superficie del tejado: " + house.getHouseSurface());
            System.out.println("Superficie disponible: " + availableSurface(house));
            System.out.println("Interruptor general: " + (house.isSwtch() ? "Encendido" : "Apagado"));
            if (!house.getDevices().isEmpty()){
                System.out.println("Placas solares instaladas: " + house.getPanels().size());
            } else{
                System.out.println("No tiene placas solares instaladas.");
            }
            if (!house.getDevices().isEmpty()){
                System.out.println("Aparatos registrados: " + house.getDevices().size());
            } else{
                System.out.println("No tiene aparatos eléctricos registrados.");
            }
            System.out.println();
        }
    }

    public int availableSurface(House house) {
        int occupiedSurface = 0;
        for (Panel panel : house.getPanels()) {
            occupiedSurface += panel.getPanelSurface();
        }
        return house.getHouseSurface() - occupiedSurface;
    }

}
