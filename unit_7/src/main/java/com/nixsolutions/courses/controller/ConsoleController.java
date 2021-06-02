package com.nixsolutions.courses.controller;

import com.nixsolutions.courses.service.CalendarService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleController {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private void findDifference() {

    }

    private void addToDate() {

    }

    private void subtractFromDate() {

    }

    private void compareDates() {

    }

    private void printOptions() {
        System.out.println("Enter option:\n0 - exit\n1 - find difference in dates\n2 - add to date\n3 - subtract from date\n4 - compare dates by ascending or descending order");
    }

    public void readConsole() {
        String input;
        while (true) {
            printOptions();
            try {
                while ((input = reader.readLine()) != null) {
                    switch (input) {
                        case "0":
                            System.exit(0);
                            break;
                        case "1":
                            findDifference();
                            break;
                        case "2":
                            addToDate();
                            break;
                        case "3":
                            subtractFromDate();
                            break;
                        case "4":
                            compareDates();
                            break;
                        default:
                            System.out.println("Wrong option. Try again");
                            break;
                    }
                    printOptions();
                }
            } catch (IOException e) {
                System.out.println("Some input-output error happened. Try again");
            }
        }
    }
}
