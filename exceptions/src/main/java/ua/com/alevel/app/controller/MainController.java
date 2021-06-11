package ua.com.alevel.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void menu() {

        String input;
        printActionList();
        try{
            while ((input = reader.readLine()) != null) {
                switch (input) {
                    case "1": System.out.println("1"); break;
                    case "2": System.out.println("2"); break;
                    case "3": System.out.println("3"); break;
                    case "4": System.out.println("4"); break;
                    case "0": System.exit(0); break;
                    default: System.out.println("Wrong input, choose one of the options below"); break;
                }
                printActionList();
            }
        } catch (IOException e) {
            System.out.println("Sorry, something went wrong. Please, try again.");
        }
    }

    private void printActionList() {
        System.out.println("Choose the action:\n" +
                "1 -> Find difference in dates\n" +
                "2 -> Add time to date\n" +
                "3 -> Subtract time from date\n" +
                "4 -> Compare dates\n" +
                "0 -> Stop the program");
    }
}
