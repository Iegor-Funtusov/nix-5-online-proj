package com.nixsolutions.courses.util;

import com.nixsolutions.courses.data.Date;
import com.nixsolutions.courses.data.Time;

public class CalendarUtils {

    public static boolean validDatePair(Date from, Date to) {
        boolean result = false;
        if (from.getYear() <= to.getYear()) {
            result = true;
            if (from.getYear().equals(to.getYear())) {
                if (from.getMonth() <= to.getMonth()) {
                    if (from.getMonth().equals(to.getMonth())) {
                        if (from.getDay() <= to.getDay()) {
                            if (from.getDay().equals(to.getDay())) {
                                return validTimePair(from.getTime(), to.getTime());
                            }
                        } else {
                            result = false;
                        }
                    }
                } else {
                    result = false;
                }
            }
        }
        return result;
    }

    private static boolean validTimePair(Time from, Time to) {
        boolean result = false;
        if (from.getHours() <= to.getHours()) {
            result = true;
            if (from.getHours().equals(to.getHours())) {
                if (from.getMinutes() <= to.getMinutes()) {
                    if (from.getMinutes().equals(to.getMinutes())) {
                        if (!(from.getSeconds() <= to.getSeconds()))
                            result = false;
                    }
                } else {
                    result = false;
                }
            }
        }
        return result;
    }

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
