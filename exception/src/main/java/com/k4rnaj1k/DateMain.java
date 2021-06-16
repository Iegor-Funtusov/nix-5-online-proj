package com.k4rnaj1k;

import java.io.IOException;
import java.util.Scanner;

public class DateMain {
    private static Scanner s = new Scanner(System.in);
    private static MyDate currDate = new MyDate();
    private static boolean english = false;
    private static DateService.DateType dateType;

    public static void main(String[] args) {
        boolean flag = true;
        inputFormat();
        inputDate();
        flag = true;
        while (flag) {
            System.out.print("Current date ");
            DateService.printDate(currDate, dateType, english);
            System.out.println();
            System.out.println("""
                    Choose what to do next:
                    1 - Find difference between dates.
                    2 - Add seconds/minutes/etc. to date.
                    3 - Subtract seconds/minutes/etc. from date.
                    4 - Compare dates
                    5 - Reinput date
                    6 - Change output format
                    Any other number to exit.""");
            switch (s.nextLine()) {
                case "1":
                    findDiff();
                    break;
                case "2":
                    add();
                    break;
                case "3":
                    sub();break;
                case "4":
                    compare();
                    break;
                default: {
                    flag = false;
                }
                break;
            }
        }

    }

    private static void compare() {
        System.out.println("Input the date u'd like to compare with.");
        MyDate compDate = new MyDate();
        parseDate(s.nextLine(), compDate);
        System.out.print("The date u've entered");
        if (DateService.compare(currDate, compDate) == -1) {
            System.out.print(" is coming after ");
        } else if (DateService.compare(currDate, compDate) == 1) {
            System.out.print(" was before ");
        } else {
            System.out.print("equal to ");
        }
        System.out.print("the current one.");
    }

    private static void add() {
        System.out.println("Which time unit would u like to add to the current date?");
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
                if(amount<0){
                    throw new NumberFormatException();
                }
                rightinput = true;
            } catch (NumberFormatException n) {
                System.out.println("Sorry, there was a mistake. Please try again.");
            }
        }
        switch (chosen) {
            case "1":
                TimeService.addSeconds(amount, currDate);
                break;
            case "2":
                TimeService.addMinutes(amount, currDate);
                break;
            case "3":
                TimeService.addHours(amount, currDate);
                break;
            case "4":
                DateService.addDays(amount, currDate);
                break;
            case "5":
                DateService.addMonths(amount, currDate);
                break;
            case "6":
                DateService.addYears(amount, currDate);
                break;
        }
    }

    private static void sub(){
        System.out.println("Which time unit would u like to substract from the current date?");
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
                if(amount<0){
                    throw new NumberFormatException();
                }
                rightinput = true;
            } catch (NumberFormatException n) {
                System.out.println("Sorry, there was a mistake. Please try again.");
            }
        }
        switch (chosen) {
            case "1":
                TimeService.subSeconds(amount, currDate);
                break;
            case "2":
                TimeService.subMinutes(amount, currDate);
                break;
            case "3":
                TimeService.subHours(amount, currDate);
                break;
            case "4":
                DateService.subDays(amount, currDate);
                break;
            case "5":
                DateService.subMonths(amount, currDate);
                break;
            case "6":
                DateService.subYears(amount, currDate);
                break;
        }
    }

    private static void parseDate(String input, MyDate date) {
        String[] inputSplit = input.split(" ");
        boolean rightInput = false;
        while (!rightInput) {
            try {
                String[] splitDate;
                if (inputSplit[0].replaceAll("[^/]", "").length() == 2) {
                    splitDate = inputSplit[0].split("/");
                    date.setMonths(DateService.parseMonths(splitDate[1]));
                    date.setDays(DateService.parseDays(splitDate[0], date));
                } else if (inputSplit[0].replaceAll("[^-]", "").length() == 2) {
                    splitDate = inputSplit[0].split("-");
                    try {
                        date.setMonths(DateService.parseMonthsfromString(splitDate[0]));
                        date.setDays(DateService.parseDays(splitDate[1], date));
                    } catch (NumberFormatException n) {
                        date.setMonths(DateService.parseMonthsfromString(splitDate[1]));
                        date.setDays(DateService.parseDays(splitDate[0], date));
                    }
                } else {
                    throw new NumberFormatException();
                }
                date.setYears(DateService.parseYears(splitDate[2]));
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
                        date.setSeconds(TimeService.parseSeconds(splitTime[0]));
                    } else if (splitTime.length == 2) {
                        date.setMinutes(TimeService.parseMinutes(splitTime[0]));
                        date.setSeconds(TimeService.parseSeconds(splitTime[1]));
                    } else if (splitTime.length == 3) {
                        date.setHours(TimeService.parseHours(splitTime[0]));
                        date.setMinutes(TimeService.parseMinutes(splitTime[1]));
                        date.setSeconds(TimeService.parseSeconds(splitTime[2]));
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
        if (DateService.compare(currDate, diffDate) == 1) {
            DateService.findDiff(diffDate, currDate, resDate);
        } else {
            DateService.findDiff(currDate, diffDate, resDate);
        }
        System.out.println("The difference between dates is " + resDate.getSeconds() + " seconds " + resDate.getMinutes() + " minutes "
                + resDate.getHours() + " hours " + resDate.getDays() + " days "  + resDate.getMonths() + " months " + resDate.getYears() + " years");
    }

    private static void inputDate(){
        System.out.println("""
                Please input date and time to work with.
                Please note - if year's format is like 21 - system will convert it to 2021.
                Also, you can input time.
                01 - will be converted to 0 hours, 0 minutes, 1 second.
                01:01 - will be converted to 0 hours, 1 minute, 1 second.
                01:01:01 - will be converted to 1 hour, 1 minute, 1 second.""");
        parseDate(s.nextLine(), currDate);
    }

    private static void inputFormat(){
        boolean flag = true;
        System.out.println("""
                        Please choose date output format
                        Available formats:
                        1 - dd/mm/yy - 01/12/21
                        2 - m/d/yyyy - 3/4/2021
                        3 - mmm-d-yy - Март-4-21 || March-4-21
                        4 - dd-mmm-yyyy 00:00 - 09-Апрель-1789 00:00 || 09-April-1789
                        Note: you can input the date in any format you'd like, only output is affected by this.""");
        while (flag) {
            try {
                int chosen = Integer.parseInt(s.nextLine());
                dateType = DateService.DateType.values()[chosen - 1];
                if (chosen == 3 || chosen == 4) {
                    System.out.println("English output format? (y/n)");
                    english = s.nextLine().toLowerCase().startsWith("y");
                }
                flag = false;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException n) {
                System.out.println("Sorry, there was an exception, couldn't parse the input.");
            }
        }
    }

}
