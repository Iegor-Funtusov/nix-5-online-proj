package ua.com.alevel.app.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuController {

    public static BufferedReader reader;

    public static void mainMenu() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        try {
            System.out.println("\nChoose the level:\n" +
                    "1 -> First level (1.Count unique symbols, 2.Knight turn validator, 3.Find triangle area)\n" +
                    "2 -> Second level (Check: if string is valid)\n" +
                    "3 -> Third level (\"Game of Life\")\n" +
                    "0 -> Stop the program");
            while ((input = reader.readLine())!= null) {
                switch (input) {
                    case "1" : {
                        FirstLevelController.firstLevelMenu();
                    } break;
                    case "2" : {
                        SecondLevelController.stringValidator();
                    } break;
                    case "3" : {
                        GameLifeController.gameLife();
                    } break;
                    case "0": {
                        System.exit(0);
                    } break;
                    default: {
                        System.out.println("Wrong input");
                    } break;
                }
                System.out.println("\nChoose the level:\n" +
                        "1 -> First level (1.Count unique symbols, 2.Knight turn validator, 3.Find triangle area)\n" +
                        "2 -> Second level (Check: if string is valid)\n" +
                        "3 -> Third level (\"Game of Life\")\n" +
                        "0 -> Stop the program");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
