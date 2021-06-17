package com.nixsolutions.courses.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleController {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final CalendarController calendarController = new CalendarController();

    private String readConsole() {
        System.out.println("Choose option:\n" +
                "0 - exit\n" +
                "1 - find difference in dates\n" +
                "2 - add to date\n" +
                "3 - subtract from date\n" +
                "4 - compare dates by ascending or descending order");
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("Sorry, something went wrong with reading from console. Try again");
        }
        return input;
    }

    public void printMenu() {
        String input;
        while ((input = readConsole()) != null) {
            switch (input) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    calendarController.findDifference();
                    break;
                case "2":
                    calendarController.addToDate();
                    break;
                case "3":
                    calendarController.subtractFromDate();
                    break;
                case "4":
                    calendarController.compareDates();
                    break;
                default:
                    System.out.println("Wrong option. Try again");
            }
        }
    }
}
