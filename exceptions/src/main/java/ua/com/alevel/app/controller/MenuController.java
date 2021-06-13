package ua.com.alevel.app.controller;

import ua.com.alevel.app.entity.Calendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuController {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final CalendarController calendarController = new CalendarController();

    public void menu() {
        String input;
        try {
            printActions();
            while ((input = reader.readLine())!= null) {
                switch (input) {
                    case "1": formatMenu();break;
                    case "2": addMenu();break;
                    case "3": subtractMenu();break;
                    case "4": findDiffMenu();break;
                    case "5": sortMenu();break;
                    case "0": System.exit(0);break;
                    default: System.err.println("Sorry, your input is wrong. Please try again");menu();break;
                }
                printActions();
            }
        } catch (IOException e) {
            System.err.println("Something went wrong, please try again");
        }
    }

    public void formatMenu() {
        String input;
        try {
            printFormats();
            while ((input = reader.readLine())!= null) {
                switch (input) {
                    case "1": calendarController.setFormat(1);break;
                    case "2": calendarController.setFormat(2);break;
                    case "3": calendarController.setFormat(3);break;
                    case "4": calendarController.setFormat(4);break;
                    case "0": return;
                    default: System.err.println("Sorry, your input is wrong. Please try again");formatMenu();break;
                }
                menu();
            }
        } catch (IOException e) {
            System.err.println("Something went wrong, please try again");
        }
    }

    public void addMenu() {
        Calendar date = calendarController.inputDate();
        date.printCalendar(calendarController.getFormat());
        String input;
        try {
            System.out.println("Choose something to add:");
            printOptions();
            while ((input = reader.readLine())!= null) {
                switch (input) {
                    case "1": calendarController.addSeconds(date).printCalendar(calendarController.getFormat());break;
                    case "2": calendarController.addMinutes(date).printCalendar(calendarController.getFormat());break;
                    case "3": calendarController.addHours(date).printCalendar(calendarController.getFormat());break;
                    case "4": calendarController.addDays(date).printCalendar(calendarController.getFormat());break;
                    case "5": calendarController.addMonths(date).printCalendar(calendarController.getFormat());break;
                    case "6": calendarController.addYears(date).printCalendar(calendarController.getFormat());break;
                    case "0": menu();break;
                    default: System.err.println("Sorry, your input is wrong. Please try again");printOptions();break;
                }
                printOptions();
            }
        } catch (IOException e) {
            System.err.println("Something went wrong, please try again");
        }
    }

    public void subtractMenu() {
        Calendar date = calendarController.inputDate();
        date.printCalendar(calendarController.getFormat());
        String input;
        try {
            System.out.println("Choose something to subtract:");
            printOptions();
            while ((input = reader.readLine())!= null) {
                switch (input) {
                    case "1": calendarController.subSeconds(date).printCalendar(calendarController.getFormat());break;
                    case "2": calendarController.subMinutes(date).printCalendar(calendarController.getFormat());break;
                    case "3": calendarController.subHours(date).printCalendar(calendarController.getFormat());break;
                    case "4": calendarController.subDays(date).printCalendar(calendarController.getFormat());break;
                    case "5": calendarController.subMonths(date).printCalendar(calendarController.getFormat());break;
                    case "6": calendarController.subYears(date).printCalendar(calendarController.getFormat());break;
                    case "0": menu();break;
                    default: System.err.println("Sorry, your input is wrong. Please try again");printOptions();break;
                }
                printOptions();
            }
        } catch (IOException e) {
            System.err.println("Something went wrong, please try again");
        }
    }

    public void findDiffMenu() {
        Calendar date1 = calendarController.inputDate();
        date1.printCalendar(calendarController.getFormat());
        Calendar date2 = calendarController.inputDate();
        date2.printCalendar(calendarController.getFormat());
        String input;
        try {
            System.out.println("Show difference in:");
            printOptions();
            while ((input = reader.readLine())!= null) {
                switch (input) {
                    case "1": calendarController.findDiffSeconds(date1, date2);break;
                    case "2": calendarController.findDiffMinutes(date1, date2);break;
                    case "3": calendarController.findDiffHours(date1, date2);break;
                    case "4": calendarController.findDiffDays(date1, date2);break;
                    case "5": calendarController.findDiffMonth(date1, date2);break;
                    case "6": calendarController.findDiffYears(date1, date2);break;
                    case "0": menu();break;
                    default: System.err.println("Sorry, your input is wrong. Please try again");printOptions();break;
                }
                printOptions();
            }
        } catch (IOException e) {
            System.err.println("Something went wrong, please try again");
        }
    }

    public void sortMenu() {
        String input;
        try {
            System.out.println("Choose sorting type:");
            printSortOptions();
            while ((input = reader.readLine())!= null) {
                switch (input) {
                    case "1": calendarController.sortDates(1)
                            .forEach(calendar -> calendar.printCalendar(calendarController.getFormat()));break;
                    case "2": calendarController.sortDates(2)
                            .forEach(calendar -> calendar.printCalendar(calendarController.getFormat()));break;
                    case "0": menu();break;
                    default: System.err.println("Sorry, your input is wrong. Please try again");sortMenu();break;
                }
                printSortOptions();
            }
        } catch (IOException e) {
            System.err.println("Something went wrong, please try again");
        }
    }

    private void printActions() {
        System.out.println("Choose the option (default data format dd/mm/yy):\n" +
                "1 -> Choose the date format\n" +
                "2 -> Add to date\n" +
                "3 -> Sub from date\n" +
                "4 -> Find difference in dates\n" +
                "5 -> Sort dates\n" +
                "0 -> exit");
    }

    private void printFormats() {
        System.out.println("Choose the date format:\n" +
                "1 -> dd/mm/yy (example: 12/12/12 00:00:00)\n" +
                "2 -> m/d/yyyy (example: 1/1/1999 00:00:00)\n" +
                "3 -> mmm-d-yy (example: january-1-22 00:00:00)\n" +
                "4 -> dd-mmm-yyyy (example: 01-january-1937 00:00:00)");
    }

    private void printOptions() {
        System.out.println("1 -> seconds\n" +
                "2 -> minutes\n" +
                "3 -> hours\n" +
                "4 -> days\n" +
                "5 -> month\n" +
                "6 -> years\n" +
                "0 -> back to menu");
    }

    private void printSortOptions() {
        System.out.println("1 -> Ascending sort\n" +
                "2 -> Descending sort\n" +
                "0 -> back to menu");
    }
}
