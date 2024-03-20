package com.actividad.view.util;

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
                if (Validator.isInteger(commands[3])){
                    int surface = Integer.parseInt(commands[3]);
                    if (Validator.valHouseSurface(surface)) {
                        House newHouse = new House(nif, name, surface);
                        houses.add(newHouse);
                        System.out.println("Casa registrada correctamente");
                    }
                } else {
                    System.out.println("ERROR: La superficie debe ser un numero entero");
                }
            } else {
                System.out.println("ERROR: Ya hay una casa registrada con ese NIF");
            }
        }
    }

    public void addPanel(String[] commands) {
        if (Validator.valComLength(commands, 5)) {
            String nif = commands[1];
            if (Validator.houseExists(houses, nif)) {
                if (Validator.isInteger(commands[2]) && Validator.isInteger(commands[4]) && Validator.isFloat(commands[3])) {
                    int surface = Integer.parseInt(commands[2]);
                    float price = Float.parseFloat(commands[3]);
                    int power = Integer.parseInt(commands[4]);
                    if (Validator.valNumbers(surface, price, power)) {
                        Panel newPanel = new Panel(surface, price, power);
                        for (House house : houses) {
                            if (Validator.houseExists(houses, nif)) {
                                if (Validator.valPanelSurface(house, surface)) {
                                    int availableSurface = availableSurface(house);
                                    if (availableSurface >= surface) {
                                        house.addPanel(newPanel);
                                        System.out.println("Placa registrada correctamente.");
                                    } else {
                                        System.out.println("ERROR: No hay suficiente superficie");
                                    }
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("ERROR: No se encontró ninguna casa registrada con ese NIF");
                }
            }
        }
    }

    /*
    Recibe los comandos, mira si la casa existe, si existe se le agrega una descripción y el poder, transforma la info
    de poder a int y si el poder es mayor a 0 entonces el aparato se añade a la casa de la cual se le pasó el nif.
     */

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
                            System.out.println("Aparato registrado correctamente a la casa.");
                        }
                        break;
                    }
                }
            } else {
                System.out.println("ERROR: No se encontró ninguna casa registrada con ese NIF");
            }
        }
    }

    /*
    Recibe los comandos, mira si la casa existe y mira si el switch de la casa está encendido o apagado.
    En caso de estar apagado, lo enciende.
     */

    public void onHouse(String[] commands) {
        if (Validator.valComLength(commands, 2)) {
            String nif = commands[1];
            if (Validator.houseExists(houses, nif)) {
                for (House house : houses){
                    if (house.getNif().equalsIgnoreCase(nif)){
                        Validator.valHouseSwtch(house);
                        break;
                    }
                }
            } else {
                System.out.println("ERROR: No se encontró ninguna casa registrada con ese NIF");
            }
        }
    }

    public void onDevice(String[] commands) {
        if (Validator.valComLength(commands, 3)) {
            String nif = commands[1];
            String description = commands[2];
            if (Validator.houseExists(houses, nif) && Validator.deviceExists(houses, nif, description)) {
                for (House house : houses) {
                    if (house.getNif().equalsIgnoreCase(nif)) {
                        for (Device device : house.getDevices()) {
                            if (device.getDescription().equalsIgnoreCase(description) && house.isSwtch()) {
                                if (!device.isSwtch()){
                                    device.setSwtch(true);
                                    System.out.println("Aparato encendido correctamente.");
                                    Validator.overPowered(house);
                                } else {
                                    System.out.println("ERROR: El aparato ya está encendido.");
                                }
                            }
                        }
                    } else {
                        System.out.println("ERROR: El interruptor general está apagado.");}
                }
            }
        }
    }

    public void offDevice(String[] commands){
        if (Validator.valComLength(commands,3)){
            String nif = commands[1];
            String description = commands[2];
            if (Validator.houseExists(houses, nif) && Validator.deviceExists(houses, nif, description)){
                for (House house : houses){
                    if (house.getNif().equalsIgnoreCase(nif)){
                        for (Device device : house.getDevices()){
                            if (device.getDescription().equalsIgnoreCase(description)){
                                if (device.isSwtch()){
                                    device.setSwtch(false);
                                    System.out.println("Dispositivo apagado correctamente.");
                                } else {
                                    System.out.println("ERROR: El dispositivo está apagado.");
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /*
     Muestra la información de todas las casas, si tiene aparatos, paneles registrados, su superficie y la superficie
     restante disponible.
     */

    public void list(){
        System.out.println("--- Paneles Solares, S.L. ---");
        System.out.println("Casas registradas: " + houses.size());
        infoCasas();
    }

        /*
        Calcular superficie disponible, recibiendo la superficie de una casa y la superficie de los paneles
        registrados y se resta.
        */

    public int availableSurface(House house) {
        int occupiedSurface = 0;
        for (Panel panel : house.getPanels()) {
            occupiedSurface += panel.getPanelSurface();
        }
        return house.getHouseSurface() - occupiedSurface;
    }




    /*
    Recorre el objeto house y coge la lista "houses" con un bucle for
    cada posición de casa es una casa diferente y coge toda su información.
     */

    public void infoCasas(){
        for (int i = 0; i < houses.size(); i++){
            House house = houses.get(i);
            System.out.println("Casa " + (i+1));
            System.out.println("Cliente: " + house.getNif() + " - " + house.getName());
            System.out.println("Superficie del tejado: " + house.getHouseSurface());
            System.out.println("Superficie disponible: " + availableSurface(house));
            System.out.println("Interruptor general: " + (house.isSwtch() ? "Encendido" : "Apagado"));
            if (!house.getPanels().isEmpty()){
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

}
