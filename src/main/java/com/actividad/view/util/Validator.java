package com.actividad.view.util;

import java.util.ArrayList;

import com.actividad.model.Device;
import com.actividad.model.House;
import com.actividad.model.Panel;

public class Validator {

    public static boolean valComLength(String[] commandParts, int expectedLength){
        if (commandParts.length != expectedLength) {
            System.out.println("ERROR: Comando incompleto");
            return false;
        }
        return true;
    }

    public static boolean isInteger(String str){
        try{
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex){
            System.out.println("ERROR: La superficie y la potencia deben ser números enteros.");
            return false;
        }
    }

    public static boolean isFloat(String str){
        try{
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException ex){
            System.out.println("ERROR: El precio debe ser un numero");
            return false;
        }
    }

    public static boolean houseExists(ArrayList<House> houses, String nif) {
        for(House house : houses){
            if (house.getNif().equalsIgnoreCase(nif)){
                return true;
            }
        }
        return false;
    }

    public static boolean deviceExists(ArrayList<House> houses, String nif, String description) {
        for (House house : houses) {
            if (house.getNif().equals(nif)) {
                for (Device device : house.getDevices()) {
                    if (device.getDescription().equals(description)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean valNumbers(int surface, float price, int power){
        if (surface > 0 && price > 0 && power > 0){
            return true;
        }
        System.out.println("ERROR: los números deben ser mayores a 0");
        return false;
    }
    public static boolean valHouseSurface(int surface){
        if (surface <= 10){
            System.out.println("ERROR: La superficie de la casa debe ser mayor a 10");
            return false;
        }
        return true;
    }

    public static boolean valPanelSurface(House house, int surface){
        if(surface > house.getHouseSurface()){
            System.out.println("ERROR: La superficie de la casa es demasiado pequeña.");
            return false;
        }
        return true;
    }

    public static boolean valDevPower(int power){
        if (power <= 0){
            System.out.println("ERROR: La potencia debe ser mayor a 0.");
            return false;
        }
        return true;
    }

    public static void valHouseSwtch(House house) {
        if (house.isSwtch()) {
            System.out.println("ERROR: El interruptor ya está encendido");
        } else {
            house.setSwtch(true);
            System.out.println("Interruptor se ha encendido");
        }
    }

    public static int panelsPower(House house){
        int totalPanelPower = 0;
        for(Panel panel : house.getPanels()){
            totalPanelPower += panel.getPanelPower();
        }
        return totalPanelPower;
    }

    public static int devicesPower(House house){
        int totalDevicePower = 0;
        for (Device device : house.getDevices()){
            totalDevicePower += device.getDevicePower();
        }
        return totalDevicePower;
    }

    public static boolean overPowered(House house){
        int totalPanelPower = panelsPower(house);
        int totalDevicePower = devicesPower(house);
        if (totalPanelPower < totalDevicePower){
            house.setSwtch(false);
            for(Device device : house.getDevices()){
                device.setSwtch(false);
            }
            System.out.println("Se ha excedido el limite de potencia, se ha apagado todo.");
            return true;
        }
        return false;
    }

}
