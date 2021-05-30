package Dates;

import Controllers.DateTypes;
import Controllers.InputOutput;

import java.util.Scanner;

import static Dates.Adding.*;

public class Subtracting {
    public static void subtract(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter \"R\" if you want to return to function selection\nEnter \"F\" if you want to choose FORMAT of date yourself\n" +
                "or input in one of the ways (examples):\n" +
                "\"1/10/1934\" - 1st October, 1934 year, 0 hours, 0 minutes, 0 seconds\n" +
                "\"/5/47 00:24:00\" - 1st May, 1947 year, 0 hours, 24 minutes, 0 seconds\n" +
                "\"/2/\" - 1st February, 2021 year\n" +
                "\"1256 59:59\" - 1st January, 1256 year, 0 hours, 59 minutes, 59 seconds\n");
        int format = 0;
        while (true){
            System.out.println("Your date (R - return, F - format):");
            int[] date = DateTypes.dateControl(format);
            if(date[0] == -2)
                return;
            System.out.println("What would you like to subtract from your date?\n" +
                    "1 >> years\n" +
                    "2 >> months\n" +
                    "3 >> days\n" +
                    "4 >> hours\n" +
                    "5 >> minutes\n" +
                    "6 >> seconds");
            String input = scanner.nextLine();
            switch (input){
                case "1":{
                    subYear(date);
                }break;
                case "2":{
                    System.out.println("Input number of months that you want to subtract");
                    int months = validMonthDays();
                    System.out.println("Inputted date:");
                    InputOutput.Output(date);
                    subMonths(date, months);
                }break;
                case "3":{
                    System.out.println("Input number of days that you want to subtract");
                    int days = validMonthDays();
                    System.out.println("Inputted date:");
                    InputOutput.Output(date);
                    System.out.println("Result:");
                    InputOutput.Output(subDays(date, days));
                }break;
                case "4":{
                    System.out.println("Input number of hours that you want to subtract");
                    int hours = validMonthDays();
                    System.out.println("Inputted date:");
                    InputOutput.Output(date);
                    System.out.println("Result:");
                    InputOutput.Output(subHours(date, hours));
                }break;
                case "5":{
                    System.out.println("Input number of minutes that you want to subtract");
                    int minutes = validMinSec();
                    System.out.println("Inputted date:");
                    InputOutput.Output(date);
                    System.out.println("Result:");
                    InputOutput.Output(subMinutes(date, minutes));
                }break;
                case "6":{
                    System.out.println("Input number of seconds that you want to subtract");
                    int seconds = validMinSec();
                    System.out.println("Inputted date:");
                    InputOutput.Output(date);
                    System.out.println("Result:");
                    InputOutput.Output(subSeconds(date, seconds));
                }break;
            }
            format = date[6];
        }
    }

    private static void subYear(int[] date){
        System.out.println("Input number of years that you want to subtract");
        int year = validYear();
        date[2] -= year;
        date[2] = Math.abs(date[2]);
        InputOutput.Output(date);
    }

    private static void subMonths(int[] date, int months){
        date[2] -= months/12;
        months = months % 12;
        date[2] = Math.abs(date[2]);
        if(months > date[1]) {
            months -= date[1];
            date[1] = 12;
            date[1] = 12 - months;
            date[2] -= 1;
        }
        else{
            date[1] = date[1] - months;
            if(date[1] == 0) {
                date[1] = 12;
                date[2] -= 1;
            }
        }
        System.out.println("Result:");
        InputOutput.Output(date);
    }

    private static int[] subDays(int[] date, int days){
        int Month;
        if(days >= date[0]){
            days -= date[0];
            date[1]--;
            if(date[1] == 0) {
                date[1] = 12;
                date[2]--;
            }
            date[0] = Difference.defineDays(date[1], date[2]);
        }
        else{
            date[0] = date[0] - days;
            return date;
        }

        while (days >= 365){
            if (date[2] % 4 == 0 && days > 365) {
                date[2]--;
                days -= 366;
            } else if (date[2] % 4 != 0 && days >= 365) {
                date[2]--;
                days -= 365;
            }
        }

        while (days >= 28){
            Month = Difference.defineDays(date[1], date[2]);
            if (Month <= days) {
                date[1]--;
                if (date[1] == 0) {
                    date[1] = 12;
                    date[2]--;
                }
                days -= Month;
            } else
                break;
        }
        Month = Difference.defineDays(date[1], date[2]);
        date[0] = Month - days;
        return date;
    }

    private static int[] subHours(int[] date, int hours){
        if(hours >= date[3]){
            hours -= date[3];
            date[3] = 0;
        }
        else {
            date[3] -= hours;
            return date;
        }

        int days = hours/24;
        if(days > 0){
            date = subDays(date, days);
            date[3] = 24 - hours % 24;
            if(date[3] == 24)
                date[3] = 0;
            date[0]--;
            if(date[0] == 0){
                date[1]--;
                if(date[1] == 0){
                    date[2]--;
                    date[1] = 12;
                }
                date[0] = Difference.defineDays(date[1], date[2]);
            }
        }
        else {
            date[3] = 24 - hours;
            if(date[3] == 24)
                date[3] = 0;
            date[0]--;
            if(date[0] == 0){
                date[1]--;
                if(date[1] == 0){
                    date[2]--;
                    date[1] = 12;
                }
                date[0] = Difference.defineDays(date[1], date[2]);
            }
        }

        return date;
    }

    private static int[] subMinutes(int[] date, int minutes){
        if(minutes >= date[4]){
            minutes -= date[4];
            date[4] = 0;
        }
        else {
            date[4] -= minutes;
            return date;
        }
        int hours = minutes/60;

        if(hours > 0){
            date = subHours(date, hours);
            date[4] = 60 - minutes % 60;
            if(date[4] == 60)
                date[4] = 0;
            date[3]--;
            if(date[3] == -1) {
                date[3] = 23;
                date[0]--;
                if(date[0] == 0){
                    date[1]--;
                    if(date[1] == 0){
                        date[2]--;
                        date[1] = 12;
                    }
                    date[0] = Difference.defineDays(date[1], date[2]);
                }
            }
        }
        else {
            date[4] = 60 - minutes;
            if(date[4] == 60)
                date[4] = 0;
            date[3]--;
            if(date[3] == -1) {
                date[3] = 23;
                date[0]--;
                if(date[0] == 0){
                    date[1]--;
                    if(date[1] == 0){
                        date[2]--;
                        date[1] = 12;
                    }
                    date[0] = Difference.defineDays(date[1], date[2]);
                }
            }
        }

        return date;
    }

    private static int[] subSeconds(int[] date, int seconds){
        if(seconds >= date[5]){
            seconds -= date[5];
            date[5] = 0;
        }
        else {
            date[5] -= seconds;
            return date;
        }
        int minutes = seconds/60;

        if(minutes > 0){
            date = subMinutes(date, minutes);
            date[5] = 60 - seconds % 60;
            if(date[5] == 60)
                date[5] = 0;
            date[4]--;
            if(date[4] == -1) {
                date[4] = 59;
                date[3]--;
                if (date[3] == -1) {
                    date[3] = 23;
                    date[0]--;
                    if (date[0] == 0) {
                        date[1]--;
                        if (date[1] == 0) {
                            date[2]--;
                            date[1] = 12;
                        }
                        date[0] = Difference.defineDays(date[1], date[2]);
                    }
                }
            }
        }
        else {
            date[5] = 60 - seconds;
            if(date[5] == 60)
                date[5] = 0;
            date[4]--;
            if(date[4] == -1) {
                date[4] = 59;
                date[3]--;
                if (date[3] == -1) {
                    date[3] = 23;
                    date[0]--;
                    if (date[0] == 0) {
                        date[1]--;
                        if (date[1] == 0) {
                            date[2]--;
                            date[1] = 12;
                        }
                        date[0] = Difference.defineDays(date[1], date[2]);
                    }
                }
            }
        }
        return date;
    }
}
