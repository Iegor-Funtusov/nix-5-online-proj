package com.k4rnaj1k;

import java.util.Scanner;

public class DateMain {
    private static Scanner s = new Scanner(System.in);
    private static MyDate currDate = new MyDate();

    public static void main(String[] args) {
        System.out.println("""
                Please input date and time to work with.
                Available formats:
                - dd/mm/yy - 01/12/21
                - m/d/yyyy - 3/4/2021
                - mmm-d-yy - Март-4-21 || March-4-21
                - dd-mmm-yyyy 00:00 - 09-Апрель-1789 00:00 || 09-April-1789
                Please note - if year's format is like 21 - system will convert it to 2021.
                Also, you can input time.
                01 - will be converted to 0 hours, 0 minutes, 1 second.
                01:01 - will be converted to 0 hours, 1 minute, 1 second.
                01:01:01 - will be converted to 1 hour, 1 minute, 1 second.""");
        parseDate(s.nextLine(), currDate);
        boolean flag = true;
        while (flag) {
            System.out.println("""
                    Choose what to do next:
                    1 - Find difference between dates.
                    2 - Add seconds/minutes/etc. to date.
                    3 - Subtract seconds/minutes/etc. from date.
                    4 - Compare dates
                    Any other number to exit.""");
            switch (s.nextLine()) {
                case "1":
                    findDiff();
                    break;
                case "2":
                    add();
                    break;/*
                case "3":
                    sub();break;
                case "4":
                    compare();break;
                default:{flag = false;}break;*/
            }
        }

    }

    private static void add() {
        System.out.println("Which time unit would u like to add to the current date? " + currDate);
        boolean rightinput = false;
        String chosen = "";
        int amount = 0;
        while (!rightinput) {
            try {
                System.out.println("""
                        1 - seconds
                        2 - minutes
                        3 - hours
                        4 - days
                        5 - months
                        6 - years
                        Anything else to cancel.""");
                chosen = s.nextLine();
                System.out.println("Input the amount that you'd like to add.");
                amount = Integer.parseInt(s.nextLine());
                rightinput = true;
            } catch (NumberFormatException n) {
                System.out.println("Sorry, there was a mistake. Please try again.");
            }
        }
        switch (chosen) {
            case "1":
                TimeOperations.addSeconds(amount, currDate);
                break;
            case "2":
                TimeOperations.addMinutes(amount, currDate);
                break;
            case "3":
                TimeOperations.addHours(amount, currDate);
                break;
            case "4":
                DateOperations.addDays(amount, currDate);
                break;
            case "5":
                DateOperations.addMonths(amount, currDate);
                break;
            case "6":
                DateOperations.addYears(amount, currDate);
                break;
            default:
                System.out.println("");
        }
        System.out.println(currDate.toString());
    }

    private static void parseDate(String input, MyDate date) {
        String[] inputSplit = input.split(" ");
        boolean rightInput = false;
        while (!rightInput) {
            try {
                String[] splitDate;
                if (inputSplit[0].replaceAll("[^/]", "").length() == 2) {
                    splitDate = inputSplit[0].split("/");
                    date.setMonths(DateOperations.parseMonths(splitDate[1]));
                    date.setDays(DateOperations.parseDays(splitDate[0], date.getMonths()));
                } else if (inputSplit[0].replaceAll("[^-]", "").length() == 2) {
                    splitDate = inputSplit[0].split("-");
                    try {
                        date.setMonths(DateOperations.parseMonthsfromString(splitDate[0]));
                        date.setDays(DateOperations.parseDays(splitDate[1], date.getMonths()));
                    } catch (NumberFormatException n) {
                        date.setMonths(DateOperations.parseMonthsfromString(splitDate[1]));
                        date.setDays(DateOperations.parseDays(splitDate[0], date.getMonths()));
                    }
                } else {
                    throw new NumberFormatException();
                }
                date.setYears(DateOperations.parseYears(splitDate[2]));
                rightInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Sorry, the date you've inputted is wrong. Please try again.");
                input = s.nextLine();
                inputSplit = input.split(" ");
            }
        }
        if (inputSplit.length == 2) {
            rightInput = false;
            while (!rightInput) {
                try {
                    String[] splitTime = inputSplit[1].split(":");
                    if (splitTime.length == 1) {
                        date.setSeconds(TimeOperations.parseSeconds(splitTime[0]));
                    } else if (splitTime.length == 2) {
                        date.setMinutes(TimeOperations.parseMinutes(splitTime[0]));
                        date.setSeconds(TimeOperations.parseSeconds(splitTime[1]));
                    } else if (splitTime.length == 3) {
                        date.setHours(TimeOperations.parseHours(splitTime[0]));
                        date.setMinutes(TimeOperations.parseMinutes(splitTime[1]));
                        date.setSeconds(TimeOperations.parseSeconds(splitTime[2]));
                    } else {
                        throw new NumberFormatException();
                    }
                    rightInput = true;
                } catch (NumberFormatException n) {
                    System.out.println("The time you've inputted had wrong format. Please try again.");
                    inputSplit[1] = s.nextLine();
                }
            }
        }
    }

    private static void findDiff() {
        MyDate diffDate = new MyDate();
        System.out.println("Please input date to find difference with.");
        parseDate(s.nextLine(), diffDate);
        MyDate resDate = new MyDate();
        DateOperations.findDiff(currDate, diffDate, resDate);
        System.out.println("The difference between the two dates is = " + resDate.getSeconds() + " seconds " + resDate.getMinutes() + " minutes " + resDate.getHours() + " hours " + resDate.getDays() + " days " + resDate.getMonths() + " months " + resDate.getYears() + " years");
    }

}
