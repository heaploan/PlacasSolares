package com.actividad.util;

import java.util.ArrayList;
import com.actividad.model.House;

public class Validator {

    public static boolean valComLength(String[] commandParts, int expectedLength){
        if (commandParts.length != expectedLength) {
            System.out.println("ERROR: Comando incompleto");
            return false;
        }
        return true;
    }

    public static boolean houseExists(ArrayList<House> houses, String nif) {
        for(House house : houses){
            if (house.getNif().equalsIgnoreCase(nif)){
                return true;
            }
        }
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

    public static void valSwtch (House house) {
        if (house.isSwtch()) {
            System.out.println("ERROR: El interruptor ya está encendido");
        } else {
            house.setSwtch(true);
            System.out.println("Interruptor se ha encendido");
        }
    }
}
