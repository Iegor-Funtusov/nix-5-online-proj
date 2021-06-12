package com.nixsolutions.courses.controller;

import com.nixsolutions.courses.data.Date;
import com.nixsolutions.courses.service.CalendarService;
import com.nixsolutions.courses.util.DateInputFormatter;
import com.nixsolutions.courses.util.DateOutputFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.util.zip.DataFormatException;

public class ConsoleController {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final CalendarService calendarService = new CalendarService();

    private void findDifference() {
        while (true) {
            try {
                printFormats();
                String format = reader.readLine();
                System.out.println("Enter from date:");
                Date from = DateInputFormatter.formatDate(reader.readLine(), format);
                System.out.println("Enter to date:");
                Date to = DateInputFormatter.formatDate(reader.readLine(), format);
                String scope = printScopes();
                double result = calendarService.findDifference(from, to, scope);
                String str = String.format("%.1f ", result);
                System.out.println(str + scope);
                break;
            } catch (DataFormatException | DateTimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Sorry, something went wrong. Try again");
            }
        }
    }

    private void addToDate() {
        while (true) {
            try {
                printFormats();
                String format = reader.readLine();
                System.out.println("Enter date:");
                Date date = DateInputFormatter.formatDate(reader.readLine(), format);
                String scope = printScopes();
                System.out.println("Enter value:");
                int value = Integer.parseInt(reader.readLine());
                date = calendarService.addToDate(date, value, scope);

                printFormats();
                format = reader.readLine();
                String output = DateOutputFormatter.formatDate(date, format);
                System.out.println(output);
                break;
            } catch (IOException e) {
                System.out.println("Sorry, something went wrong. Try again");
            } catch (DataFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void subtractFromDate() {
        while (true) {
            try {
                printFormats();
                String format = reader.readLine();
                System.out.println("Enter date:");
                Date date = DateInputFormatter.formatDate(reader.readLine(), format);
                String scope = printScopes();
                System.out.println("Enter value:");
                int value = Integer.parseInt(reader.readLine());
                date = calendarService.subtractFromDate(date, value, scope);

                printFormats();
                format = reader.readLine();
                String output = DateOutputFormatter.formatDate(date, format);
                System.out.println(output);
                break;
            } catch (IOException e) {
                System.out.println("Sorry, something went wrong. Try again");
            } catch (DataFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void compareDates() {
        printFormats();

    }

    private String printScopes() {
        while (true) {
            try {
                while (true) {
                    System.out.println("Get result in:\n1 - seconds\n2 - minutes\n3 - hours\n4 - days\n5 - months\n6 - years");
                    switch (reader.readLine()) {
                        case "1":
                            return "seconds";
                        case "2":
                            return "minutes";
                        case "3":
                            return "hours";
                        case "4":
                            return "days";
                        case "5":
                            return "months";
                        case "6":
                            return "years";
                        default:
                            System.out.println("Wrong option. Try again");
                    }
                }
            } catch (IOException e) {
                System.out.println("Sorry, something went wrong. Try again");
            }
        }
    }

    private void printFormats() {
        System.out.println("Choose input-output format:\n1 - dd/mm/yy hh:mm:ss\n2 - m/d/yyyy hh:mm:ss\n3 - mmm-d-yy hh:mm:ss\n4 - dd-mmm-yyyy hh:mm:ss");
    }

    private void printOptions() {
        System.out.println("Choose option:\n0 - exit\n1 - find difference in dates\n2 - add to date\n3 - subtract from date\n4 - compare dates by ascending or descending order");
    }

    public void readConsole() {
        String input;
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
            System.out.println("Sorry, something went wrong. Try again");
        }
    }
}
