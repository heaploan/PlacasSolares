package com.actividad.util;

import java.util.ArrayList;
import com.actividad.model.Casa;

public class Validator {
    public static boolean validateSurface(int surface){
        if (surface <= 10){
            System.out.println("ERROR: La superficie de la casa debe ser mayor a 10");
            return false;
        }
        return true;
    }

    public static boolean validatePlacaSurface(Casa casa, int surface){
        if(surface > casa.getSurface()){
            System.out.println("ERROR: La superficie de la casa es demasiado pequeña.");
            return false;
        }
        return true;
    }

    public static boolean validateCommandLength(String[] commandParts, int expectedlength){
        if (commandParts.length != expectedlength) {
            System.out.println("ERROR: Comando incompleto");
            return false;
        }
        return true;
    }

    public static boolean casaExists(ArrayList<Casa> casas, String nif) {
        for(Casa casa : casas){
            if (casa.getNif().equalsIgnoreCase(nif)){
                return true;
            }
        }
        return false;
    }

}
