package com.nixsolutions.courses.service;

import com.nixsolutions.courses.data.Date;

import java.time.DateTimeException;

public class CalendarService {

    public void compareDates() {

    }

    public void subtractFromDate() {

    }

    public void addToDate() {

    }

    public double findDifference(Date from, Date to, String scope) throws DateTimeException {
        switch (scope) {
            case "seconds":
                return differenceInSeconds(from, to);
            case "minutes":
                return differenceInMinutes(from, to);
            case "hours":
                return differenceInHours(from, to);
            case "days":
                return differenceInDays(from, to);
            case "months":
                return differenceInMonths(from, to);
            case "years":
                return differenceInYears(from, to);
        }
        return -1;
    }

    private double differenceInSeconds(Date from, Date to) {
        double result = differenceInMinutes(from, to);
        if (result != 0 || (to.getTime().getSeconds() - from.getTime().getSeconds() >= 0)) {
            result = result * 60 + (to.getTime().getSeconds() - from.getTime().getSeconds());
            return result;
        } else {
            throw new DateTimeException("Value of to date is less than of from date");
        }
    }

    private double differenceInMinutes(Date from, Date to) {
        double result = differenceInHours(from, to);
        if (result != 0 || (to.getTime().getMinutes() - from.getTime().getMinutes() >= 0)) {
            result = result * 60 + (to.getTime().getMinutes() - from.getTime().getMinutes());
            return result;
        } else {
            throw new DateTimeException("Value of to date is less than of from date");
        }
    }

    private double differenceInHours(Date from, Date to) {
        double result = differenceInDays(from, to);
        if (result != 0 || (to.getTime().getHours() - from.getTime().getHours() >= 0)) {
            result = result * 24 + (to.getTime().getHours() - from.getTime().getHours());
            return result;
        } else {
            throw new DateTimeException("Value of to date is less than of from date");
        }
    }


    private double differenceInDays(Date from, Date to) throws DateTimeException {
        double result = differenceInMonths(from, to);
        if (result != 0) {
            if (result > 12) {
                result = 0;
                for (int i = from.getYear() + 1; i <= to.getYear() - 1; i++) {
                    result += 365;
                    if (isLeapYear(i)) result++;
                }
                for (int i = from.getMonth(); i < 13; i++) {
                    result += daysInMonth(i, from.getYear());
                }
                for (int i = to.getMonth(); i > 0; i--) {
                    result += daysInMonth(i, from.getYear());
                }
            } else {
                result = 0;
                for (int i = from.getMonth(); i < to.getMonth() + 1; i++) {
                    result += daysInMonth(i, from.getYear());
                }
            }
            result = result - from.getDay() - (daysInMonth(to.getMonth(), to.getYear()) - to.getDay());

        }
        return result;
    }

    private double differenceInMonths(Date from, Date to) {
        double result = differenceInYears(from, to);
        if (result != 0 || (to.getMonth() - from.getMonth() >= 0)) {
            result = result * 12 + (double) (to.getDay() - from.getDay()) / 31;
            return result;
        } else {
            throw new DateTimeException("Value of to date is less than of from date");
        }
    }

    private double differenceInYears(Date from, Date to) {
        double result = to.getYear() - from.getYear();
        if (result >= 0) {
            result = result + (double) (to.getMonth() - from.getMonth()) / 12;
            return result;
        } else {
            throw new DateTimeException("Value of to date is less than of from date");
        }
    }

    private boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 != 0) {
                return true;
            } else return year % 400 == 0;
        }
        return false;
    }

    private int daysInMonth(int month, int year) {
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
