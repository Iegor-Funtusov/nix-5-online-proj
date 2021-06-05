package com.nixsolutions.date.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleController {
    private static final BufferedReader bufferedReader;
    private static final String REGEX_NUM = "^[0-9]$";
    private final DateController dateController = new DateController();

    static {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void printMainMenu() {

        System.out.println("Please, choose the operation and input 0-4 to " +
                "select and then press Enter.\n" +
                "1. Difference between two dates.\n" +
                "2. Plus to time or date.\n" +
                "3. Subtract from time or date.\n" +
                "4. Create some dates and sort them.\n" +
                "0. Exit.\n");

        switch (SelectionInput()) {
            case "1": {
                dateController.printDatesDifference();
                break;
            }
            case "2": {
                dateController.calculateDate(1);
                break;
            }
            case "3": {
                dateController.calculateDate(2);
                break;
            }
            case "4": {
                dateController.sortDate();
                break;
            }
            case "0": {
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Wrong input, try again!");
                break;
            }
        }
        printMainMenu();
    }

    private String SelectionInput() {

        String numInput;

        do {
            System.out.println("Your choice: ");
            try {
                numInput = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Can't read user numInput", e);
            }
        } while (!numInput.matches(REGEX_NUM));
        return numInput;
    }
}
