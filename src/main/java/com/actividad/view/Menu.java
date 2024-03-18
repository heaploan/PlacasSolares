package com.actividad.view;

import com.actividad.model.Device;
import com.actividad.model.House;
import com.actividad.model.Panel;
import com.actividad.util.Validator;
import com.actividad.util.Option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {

    private BufferedReader br;
    private Option op;
    private String[] commandParts;

    public Menu() {
        br = new BufferedReader(new InputStreamReader(System.in));
        op = new Option();
    }

    public void allOptions() {
        do {
            System.out.print("> ");
            try {
                commandParts = br.readLine().split(" ");
                switch (commandParts[0].toLowerCase()) {

                    case "addcasa":
                        op.addCasa(commandParts);
                        break;

                    case "addplaca":
                        op.addPanel(commandParts);
                        break;

                    case "addaparell":
                        op.addDevice(commandParts);
                        break;

                    case "oncasa":

                        break;

                    case "onaparell":
                        break;

                    case "offaparell":
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