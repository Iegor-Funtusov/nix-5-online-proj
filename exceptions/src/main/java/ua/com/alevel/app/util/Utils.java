package ua.com.alevel.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static String firstLetterToCapital(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        return str.charAt(0) + str.substring(1).toLowerCase();
    }

    public static boolean isLeapYear(int year){
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else return year % 100 != 0;
    }

    public static boolean tryParseInt(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int daysInMonth(int month, int year) {
        if (month != 2) {
            return 30 + (month + month / 8) % 2;
        } else {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        }
    }

    public static String inputStr() {
        System.out.println("(input date in chosen format, input time separating by \":\", separate date and time with space)\nEnter date and time: ");
        String res = "";
        try {
            res = bufferedReader.readLine();
        } catch (IOException e) {
            System.err.println("Sorry, your input is wrong. Please try again");
        }
        return res;
    }
}