package com.nixsolutions.courses.util;

public class CalendarUtils {

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 != 0) {
                return true;
            } else return year % 400 == 0;
        }
        return false;
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
}
