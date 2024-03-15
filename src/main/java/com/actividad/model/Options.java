package com.actividad.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Options {
    private ArrayList<Casa> casas = new ArrayList<>();
    private String[] commandParts;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public void allOptions() {
        do {
            System.out.print("> ");
            try {
                commandParts = br.readLine().split(" ");
                switch (commandParts[0].toLowerCase()) {
                    case "addcasa":
                        if (commandParts.length == 4) {
                            String nif = commandParts[1];
                            if (!casaExists(casas, nif)) {
                                String name = commandParts[2];
                                int surface = Integer.parseInt(commandParts[3]);
                                if (surface > 10) {
                                    Casa nuevaCasa = new Casa(nif, name, surface);
                                    casas.add(nuevaCasa);
                                    System.out.println("Casa agregada correctamente");
                                } else {
                                    System.out.println("ERROR: Ya hay una casa registrada con ese NIF.");
                                }
                            }
                        } else {
                            System.out.println("ERROR: Comando incompleto");
                        }
                        break;
                    case "addPlaca":
                        break;
                    case "addAparell":
                        break;
                    case "onCasa":
                        break;
                    case "onAparell":
                        break;
                    case "offAparell":
                        break;
                    case "list":
                        break;
                    case "info":
                        break;
                    case "quit":
                        break;
                    default:
                        System.out.println("Comando incorrecto.");
                }
            } catch (IOException e) {
                System.out.println("ERROR al leer la entrada.");
            }
        } while (!commandParts[0].equals("quit"));
    }
    private boolean casaExists(ArrayList<Casa> casas, String nif) {
        for(Casa casa : casas){
            if (casa.getNif().equalsIgnoreCase(nif)){
                return true;
            }
        }
        return false;
    }
}