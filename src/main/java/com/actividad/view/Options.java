package com.actividad.view;

import com.actividad.model.Casa;
import com.actividad.model.Placa;
import com.actividad.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Options {
    private ArrayList<Casa> casas = new ArrayList<>();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String[] commandParts;

    public void allOptions() {
        do {
            System.out.print("> ");
            try {
                commandParts = br.readLine().split(" ");
                switch (commandParts[0].toLowerCase()) {

                    case "addcasa":
                        if (Validator.validateCommandLength(commandParts, 4)) {
                            String nif = commandParts[1];
                            if (!Validator.casaExists(casas, nif)) {
                                String name = commandParts[2];
                                int surface = Integer.parseInt(commandParts[3]);
                                if (Validator.validateSurface(surface)) {
                                    Casa nuevaCasa = new Casa(nif, name, surface);
                                    casas.add(nuevaCasa);
                                    System.out.println("Casa registrada correctamente");
                                }
                            } else {
                                System.out.println("ERROR: Ya hay una casa registrada con ese NIF:");
                            }
                        }
                        break;

                    case "addplaca":
                        if (Validator.validateCommandLength(commandParts, 5)) {
                            String nif = commandParts[1];
                            if (Validator.casaExists(casas, nif)) {
                                int surface = Integer.parseInt(commandParts[2]);
                                float price = Float.parseFloat(commandParts[3]);
                                int power = Integer.parseInt(commandParts[4]);
                                Placa nuevaPlaca = new Placa(surface, price, power);
                                for (Casa casa : casas) {
                                    if (Validator.casaExists(casas, nif)) {
                                        if (Validator.validatePlacaSurface(casa, surface)){
                                        casa.addPlaca(nuevaPlaca);
                                        System.out.println("Placa registrada correctamente.");
                                        }
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("ERROR: No se encontró ninguna casa registrada con ese NIF");
                            }
                        }
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
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Comando incorrecto.");
                }
            } catch (IOException e) {
                System.out.println("ERROR al leer la entrada.");
            }
        } while (!commandParts[0].equals("quit"));
    }
}