package Dates;

import Controllers.DateTypes;
import Controllers.InputOutput;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Adding {
    public static void add(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter \"R\" if you want to return to function selection\nEnter \"F\" if you want to choose FORMAT of date yourself\n" +
                "or input in one of the ways (examples):\n" +
                "\"1/10/1934\" - 1st October, 1934 year, 0 hours, 0 minutes, 0 seconds\n" +
                "\"/5/47 00:24:00\" - 1st May, 1947 year, 0 hours, 24 minutes, 0 seconds\n" +
                "\"/2/\" - 1st February, 2021 year\n" +
                "\"1256 59:59\" - 1st January, 1256 year, 0 hours, 59 minutes, 59 seconds\n");
        int sign = 0;
        while (true){
            System.out.println("Your date (R - return, F - format):");
            int[] date = DateTypes.dateControl(sign);
            if(date[0] == -2)
                return;

            System.out.println("What would you like to add to your date?\n" +
                    "1 >> years\n" +
                    "2 >> months\n" +
                    "3 >> days\n" +
                    "4 >> hours\n" +
                    "5 >> minutes\n" +
                    "6 >> seconds");
            String input = scanner.nextLine();
            switch (input){
                case "1":{
                    addYear(date);
                }break;
                case "2":{
                    addMonths(date);
                }break;
                case "3":{
                    System.out.println("Input number of days that you want to add");
                    int days = validMonthDays();
                    System.out.println("Inputted date:");
                    InputOutput.Output(date);
                    System.out.println("Result:");
                    InputOutput.Output(addDays(date, days));
                }break;
                case "4":{
                    System.out.println("Input number of hours that you want to add");
                    int hours = validMonthDays();
                    System.out.println("Inputted date:");
                    InputOutput.Output(date);
                    System.out.println("Result:");
                    InputOutput.Output(addHours(date, hours));
                }break;
                case "5":{
                    System.out.println("Input number of minutes that you want to add");
                    int minutes = validMinSec();
                    System.out.println("Inputted date:");
                    InputOutput.Output(date);
                    System.out.println("Result:");
                    InputOutput.Output(addMinutes(date, minutes));
                }break;
                case "6":{
                    System.out.println("Input number of seconds that you want to add");
                    int seconds = validMinSec();
                    System.out.println("Inputted date:");
                    InputOutput.Output(date);
                    System.out.println("Result:");
                    InputOutput.Output(addSeconds(date, seconds));
                }break;
                default:
                    System.out.println("Incorrect input");
            }
            sign = date[6];
            System.out.println();
        }
    }

    private static void addYear(int[] date){
        System.out.println("Input number of years that you want to add");
        int year = validYear();
        System.out.println("Inputted date:");
        InputOutput.Output(date);
        date[2] += year;
        System.out.println("Result:");
        InputOutput.Output(date);
    }

    public static int validYear(){
        Scanner scanner = new Scanner(System.in);
        try{
            int year = scanner.nextInt();
            if(year < 0 || year >= 100_000){
                System.out.println("Incorrect input. Input only one positive number and not more than 100_000");
                return validYear();
            }
            return year;
        }
        catch (InputMismatchException e){
            System.out.println("Incorrect input. Input only one number not more than 100_000");
            return validYear();
        }
    }

    private static void addMonths(int[] date){
        System.out.println("Input number of months that you want to add");
        int months = validMonthDays();
        System.out.println("Inputted date:");
        InputOutput.Output(date);
        date[2] += months/12;
        if(months % 12 + date[1] <= 12)
            date[1] = months % 12 + date[1];
        else{
            date[2]++;
            months -= 12 - date[1];
            date[1] = months;
        }
        System.out.println("Result:");
        InputOutput.Output(date);
    }

    private static int[] addDays(int[] date, int days){
        int Month = Difference.defineDays(date[1], date[2]);
        if(Month < days) {
            days -= Month - date[0] + 1;
            date[0] = 1;
            date[1]++;
            if (date[1] == 13) {
                date[1] = 1;
                date[2]++;
            }
            while (days >= 365) {
                if (date[2] % 4 == 0 && days > 365) {
                    date[2]++;
                    days -= 366;
                } else if (date[2] % 4 != 0 && days >= 365) {
                    date[2]++;
                    days -= 365;
                }
            }
            while (days >= 28) {
                Month = Difference.defineDays(date[1], date[2]);
                if (Month <= days) {
                    date[1]++;
                    if (date[1] == 13) {
                        date[1] = 1;
                        date[2]++;
                    }
                    days -= Month;
                } else
                    break;
            }
        }
        Month = Difference.defineDays(date[1], date[2]);
        for (int i = date[0]; i <= (Month + 1); i++) {
            if(days != 0) {
                date[0]++;
                if(date[0] == Month + 1) {
                    date[0] = 1;
                    date[1]++;
                    if(date[1] == 13) {
                        date[1] = 1;
                        date[2]++;
                    }
                }
                days--;
            }
        }
        if(days != 0)
            date[0] += days;
        return date;
    }

    private static int[] addHours(int[] date, int hours){
        int days;
        if(hours >= 24) {
            days = hours / 24;
            date = addDays(date, days);
            date[3] = hours % 24;
        }
        else {
            date[3] += hours % 24;
            if (date[3] >= 24) {
                date[3] = date[3] - 24;
                date[0]++;
                if(date[3] == 24)
                    date[3] = 0;
                if(date[0] > Difference.defineDays(date[1], date[2])){
                    date[0] = 1;
                    date[1]++;
                    if(date[1] == 13){
                        date[1] = 1;
                        date[2]++;
                    }
                }
            }
        }
        return date;
    }

    private static int[] addMinutes(int[] date, int minutes){
        int hours, diff;
        if(minutes >= 60){
            hours = minutes / 60;
            diff = date[4];
            date = addHours(date, hours);
            date[4] = minutes % 60 + diff;
            if(date[4] > 60) {
                date[4] -= 60;
                date[3]++;
                if (date[3] >= 24) {
                    date[3] = date[3] - 24;
                    date[0]++;
                    if (date[3] == 24)
                        date[3] = 0;
                    if (date[0] > Difference.defineDays(date[1], date[2])) {
                        date[0] = 1;
                        date[1]++;
                        if (date[1] == 13) {
                            date[1] = 1;
                            date[2]++;
                        }
                    }
                }
            }
        }
        else{
            date[4] += minutes % 60;
            if(date[4] >= 60) {
                date[4] = date[4] - 60;
                date[3]++;
                if(date[4] == 60)
                    date[4] = 0;
                if (date[3] >= 24) {
                    date[3] = date[3] - 24;
                    date[0]++;
                    if (date[3] == 24)
                        date[3] = 0;
                    if (date[0] > Difference.defineDays(date[1], date[2])) {
                        date[0] = 1;
                        date[1]++;
                        if (date[1] == 13) {
                            date[1] = 1;
                            date[2]++;
                        }
                    }
                }
            }
        }
        return date;
    }

    private static int[] addSeconds(int[] date, int seconds){
        int minutes, diff;
        if(seconds >= 60){
            minutes = seconds / 60;
            diff = date[5];
            date = addMinutes(date, minutes);
            date[5] = seconds % 60 + diff;
            if(date[5] > 60) {
                date[5] -= 60;
                date[4]++;
                if (date[4] >= 60) {
                    date[4] -= 60;
                    date[3]++;
                    if (date[3] >= 24) {
                        date[3] = date[3] - 24;
                        date[0]++;
                        if (date[3] == 24)
                            date[3] = 0;
                        if (date[0] > Difference.defineDays(date[1], date[2])) {
                            date[0] = 1;
                            date[1]++;
                            if (date[1] == 13) {
                                date[1] = 1;
                                date[2]++;
                            }
                        }
                    }
                }
            }
        }
        else {
            date[5] += seconds % 60;
            if (date[5] >= 60) {
                date[5] = date[5] - 60;
                date[4]++;
                if (date[4] >= 60) {
                    date[4] = date[4] - 60;
                    date[3]++;
                    if (date[4] == 60)
                        date[4] = 0;
                    if (date[3] >= 24) {
                        date[3] = date[3] - 24;
                        date[0]++;
                        if (date[3] == 24)
                            date[3] = 0;
                        if (date[0] > Difference.defineDays(date[1], date[2])) {
                            date[0] = 1;
                            date[1]++;
                            if (date[1] == 13) {
                                date[1] = 1;
                                date[2]++;
                            }
                        }
                    }
                }
            }
        }
        return date;
    }

    public static int validMonthDays(){
        Scanner scanner = new Scanner(System.in);
        try{
            int month = scanner.nextInt();
            if(month < 0 || month >= 1_000_000){
                System.out.println("Incorrect input. Input only one positive number and not more than 1_000_000");
                return validMonthDays();
            }
            return month;
        }
        catch (InputMismatchException e){
            System.out.println("Incorrect input. Input only one number not more than 1_000_000");
            return validMonthDays();
        }
    }

    public static int validMinSec(){
        Scanner scanner = new Scanner(System.in);
        try{
            int month = scanner.nextInt();
            if(month < 0 || month >= 10_000_000){
                System.out.println("Incorrect input. Input only one positive number and not more than 10_000_000");
                return validMinSec();
            }
            return month;
        }
        catch (InputMismatchException e){
            System.out.println("Incorrect input. Input only one number not more than 10_000_000");
            return validMinSec();
        }
    }

}
