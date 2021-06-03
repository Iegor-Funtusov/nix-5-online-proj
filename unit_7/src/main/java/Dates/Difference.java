package Dates;

import Controllers.DateTypes;
import Controllers.InputOutput;

import java.util.Scanner;

public class Difference {
    public static void diff(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter \"R\" if you want to return to function selection\nEnter \"F\" if you want to choose FORMAT of date yourself\n" +
                "or input in one of the ways (examples):\n" +
                "\"1/10/1934\" - 1st October, 1934 year, 0 hours, 0 minutes, 0 seconds\n" +
                "\"/5/47 00:24:00\" - 1st May, 1947 year, 0 hours, 24 minutes, 0 seconds\n" +
                "\"/2/\" - 1st February, 2021 year\n" +
                "\"1256 59:59\" - 1st January, 1256 year, 0 hours, 59 minutes, 59 seconds\n");
        int format = 0;
        while(true){
            System.out.println("First date (R - return, F - format):");
            int[] date = DateTypes.dateControl(format);
            if(date[0] == -2)
                return;
            format = date[6];
            System.out.println("Second date (R - return, F - format):");
            int[] date2 = DateTypes.dateControl(format);
            if(date2[0] == -2)
                return;
            System.out.println("Inputted dates:");
            InputOutput.Output(date);
            InputOutput.Output(date2);
            if(checkOrder(date, date2, 2) == false){
                for (int i = 0; i < 6; i++) {
                    int swap = date[i];
                    date[i] = date2[i];
                    date2[i] = swap;
                }
            }

            System.out.println("Choose what would you like to know between dates:\n" +
                    "1 >> years\n" +
                    "2 >> months\n" +
                    "3 >> days\n" +
                    "4 >> hours\n" +
                    "5 >> minutes\n" +
                    "6 >> seconds");
            String input = scanner.nextLine();
            switch (input){
                case "1":{
                    System.out.println("Years: "+Math.abs(years(date,date2)));
                }break;
                case "2":{
                    System.out.println("Months: "+Math.abs(months(date,date2)));
                }break;
                case "3":{
                    System.out.println("Days: "+Math.abs(days(date,date2)));
                }break;
                case "4":{
                    System.out.println("Hours: "+Math.abs(hours(date,date2)));
                }break;
                case "5":{
                    if(minutes(date, date2) == 0)
                        System.out.println("Too big difference. Please, input less dates");
                    else
                        System.out.println("Minutes: "+Math.abs(minutes(date,date2)));
                }break;
                case "6":{
                    if(seconds(date, date2) == 0)
                        System.out.println("Too big difference. Please, input less dates");
                    else
                        System.out.println("Seconds: "+Math.abs(seconds(date,date2)));
                }break;
            }
            format = date2[6];
        }
    }
    public static boolean checkOrder(int[] date1, int[] date2, int size){
        if(size == 6)
            return true;
        else if(size == 2){
            if(date1[size]>date2[size])
                return false;
            else if(date1[size] == date2[size])
                return checkOrder(date1,date2,size-1);
            else
                return true;
        }
        else if(size < 2 && size != -1){
            if(date1[size] > date2[size]){
                return false;
            }
            else if(date1[size] == date2[size])
                return checkOrder(date1, date2, size - 1);
            else{
                return true;
            }
        }
        else if(size == -1){
            return checkOrder(date1, date2, 3);
        }
        else if(size == 3){
            if(date1[size] > date2[size])
                return false;
            else if(date1[size] == date2[size])
                return checkOrder(date1, date2, size+1);
            else
                return true;
        }
        else if (size > 3){
            if(date1[size] > date2[size])
                return false;
            else if(date1[size-1]==date2[size-1])
                return checkOrder(date1, date2, size+1);
            else
                return true;
        }
        return true;
    }


    private static int years(int[] date1, int[] date2){
        int years = date1[2] - date2[2];
        return years;
    }

    private static int months(int[] date1, int[] date2){
        int months, date1M, date2M;
        date1M = date1[1] + date1[2]*12;
        date2M = date2[1] + date2[2]*12;
        months = date1M - date2M;
        return months;
    }


    private static int days(int[] date1, int[] date2){
        int year1, year2;
        int month1, month2;
        int day1, day2, days = 0;
        year1 = date1[2];
        year2 = date2[2];
        month1 = date1[1];
        month2 = date2[1];
        day1 = date1[0];
        day2 = date2[0];
        int beginYear = year1;
        year1++;
        while(year1 < year2) {
            if(year1 % 4 == 0)
                days += 366;
            else
                days += 365;
            year1++;
        }
        if(beginYear != year2) {
            int i = month1;
            while (i < 13) {
                if (month1 == i) {
                    days += defineDays(month1, beginYear) - day1 + 1;
                } else {
                    days += defineDays(i, beginYear);
                }
                i++;
            }
            i = 1;
            while (i < month2) {
                if (month2 == i) {
                    days += defineDays(month1, beginYear) - day2 + 1;
                } else {
                    days += defineDays(i, beginYear);
                }
                i++;
            }
        }
        else{
            int i = month1;
            while (i <= month2) {
                if(i == month2){
                    days += day2-day1;
                }
                else if (month1 == i) {
                    days += defineDays(month1, beginYear) - day1 + 1;
                } else {
                    days += defineDays(i, beginYear);
                }
                i++;
            }
        }
        return days;
    }

    public static int defineDays(int month, int year){
        if(month == 2 && year % 100 == 0 && year % 400 != 0)
            return 28;
        else if(month == 2 && year % 400 == 0)
            return 29;
        else if(month == 2 && year % 4 == 0)
            return 29;
        else if(month == 2)
            return 28;
        else if((month <= 7 && month % 2 == 1) || (month >= 8 && month % 2 == 0)){
            return 31;
        }
        else
            return 30;
    }

    private static int hours (int[] date1, int[] date2){
        int days = days(date1, date2)-1;
        int hours;
        if(days >= 0){
            hours = days * 24 + (24-date1[3]) + date2[3];
        }
        else{
            hours = date2[3] - date1[3];
        }
        return hours;
    }

    private static long minutes (int[] date1, int[] date2){
        int hours = hours(date1, date2)-1;
        long minutes;
        if(hours >= 0){
            minutes = hours * 60 + (60-date1[4]) + date2[4];
        }
        else{
            minutes = date2[4] - date1[4];
        }
        return minutes;
    }

    private static long seconds (int[] date1, int[] date2){
        long minutes = minutes(date1, date2)-1;
        long seconds;
        if(minutes >= 0){
            seconds = minutes * 60 + (60-date1[5]) + date2[5];
        }
        else{
            seconds = date2[5] - date1[5];
        }
        return seconds;
    }
}
