package com.actividad.util;

import com.actividad.model.Device;
import com.actividad.model.House;
import com.actividad.model.Panel;

import java.security.PublicKey;
import java.util.ArrayList;

public class Option {

    private ArrayList<House> houses;
    public Option() {
        houses = new ArrayList<>();
    }

    public void addCasa(String[] commands){
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
                            house.addPlaca(newPanel);
                            System.out.println("Placa registrada correctamente.");
                        }
                        break;
                    }
                }
            } else {
                System.out.println("ERROR: No se encontró ninguna casa registrada con ese NIF");
            }
        }
    }

    public void addDevice(String[] commands){
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
    public void onHouse(String[] commands)  {


    }
}
