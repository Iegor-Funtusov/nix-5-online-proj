package com.nixsolutions.courses.service;

import com.nixsolutions.courses.data.Date;

import java.time.DateTimeException;

public class CalendarService {

    public void compareDates() {

    }

    public void subtractFromDate() {

    }

    public Date addToDate(Date date, int value, String scope) {
        switch (scope) {
            case "seconds":
                return addSeconds(date, value);
            case "minutes":
                return addMinutes(date, value);
            case "hours":
                return addHours(date, value);
            case "days":
                return addDays(date, value);
            case "months":
                return addMonths(date, value);
            case "years":
                return addYears(date, value);
        }
        return date;
    }

    private Date addSeconds(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int hours = date.getTime().getHours();
        int minutes = date.getTime().getMinutes();
        int seconds = date.getTime().getSeconds();
        for (int i = seconds; i <= 60; i++) {
            if (value >= 0) {
                if (i == 60) {
                    i = 0;
                    if (++minutes == 60) {
                        minutes = 0;
                        if (++hours == 24) {
                            hours = 0;
                            if (++day > daysInMonth(month, year)) {
                                day = 1;
                                if (++month > 12) {
                                    month = 1;
                                    year++;
                                }
                            }
                        }
                    }
                }
                value--;
            } else {
                seconds = i -1;
                break;
            }
        }
        date.getTime().setSeconds(seconds);
        date.getTime().setMinutes(minutes);
        date.getTime().setHours(hours);
        date.setDay(day);
        date.setMonth(month);
        date.setYear(year);

        return date;
    }

    private Date addMinutes(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int hours = date.getTime().getHours();
        int minutes = date.getTime().getMinutes();
        for (int i = minutes; i <= 60; i++) {
            if (value >= 0) {
                if (i == 60) {
                    i = 0;
                    if (++hours == 24) {
                        hours = 0;
                        if (++day > daysInMonth(month, year)) {
                            day = 1;
                            if (++month > 12) {
                                month = 1;
                                year++;
                            }
                        }
                    }
                }
                value--;
            } else {
                minutes = i - 1;
                break;
            }
        }
        date.getTime().setMinutes(minutes);
        date.getTime().setHours(hours);
        date.setDay(day);
        date.setMonth(month);
        date.setYear(year);
        return date;
    }

    private Date addHours(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int hours = date.getTime().getHours();
        for (int i = hours; i <= 24; i++) {
            if (value >= 0) {
                if (i == 24) {
                    i = 0;
                    if (++day > daysInMonth(month, year)) {
                        day = 1;
                        if (++month > 12) {
                            month = 1;
                            year++;
                        }
                    }
                }
                value--;
            } else {
                hours = i - 1;
                break;
            }
        }
        date.getTime().setHours(hours);
        date.setDay(day);
        date.setMonth(month);
        date.setYear(year);
        return date;
    }

    private Date addDays(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        for (int i = day; i <= daysInMonth(month, year); i++) {
            if (value > 0) {
                if (i == daysInMonth(month, year)) {
                    i = 0;
                    if (++month > 12) {
                        month = 1;
                        year++;
                    }
                }
                value--;
            } else {
                day = i;
                break;
            }
        }
        date.setDay(day);
        date.setMonth(month);
        date.setYear(year);
        return date;
    }

    private Date addMonths(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        System.out.println("month=" + month);
        while (value > 0) {
            month++;
            System.out.println("month:" + month);
            if (month > 12) {
                month = 1;
                year++;
            }
            value--;
        }
        date.setMonth(month);
        date.setYear(year);
        return date;
    }

    private static Date addYears(Date date, int value) {
        date.setYear(date.getYear() + value);
        return date;
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
