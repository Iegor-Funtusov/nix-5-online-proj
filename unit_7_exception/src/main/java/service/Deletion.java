package service;

import domain.Calendar;
import domain.Date;
import exception.InvalidDateFormatException;
import utils.CONSTANTS;

import java.util.Map;

public class Deletion {

   public static Date delSeconds(int seconds, Date date) {
        int min = seconds / 60;
        int sec = seconds - min * 60;

        date = delMinutes(min, date);

        int totalTime = (int) (date.getTime() - sec);

        if (totalTime < 0) {
            date = delMinutes(1, date);
            date.setTime(86400 - Math.abs(totalTime));
        } else {
            date.setTime(totalTime);
        }
        return date;
    }

    public static Date delMinutes(int minutes, Date date) {

        int hrs = minutes / 60;
        int min = minutes - hrs * 60;

        date = delHours(hrs, date);

        int totalTime = (int) (date.getTime() - min * 60);

        if (totalTime < 0) {
            date = delHours(1, date);
            date.setTime(1440 * 60 - Math.abs(totalTime));
        } else {
            date.setTime(totalTime);
        }
        return date;
    }

    public static Date delHours(int hours, Date date) {
        int days = hours / 24;
        int hrs = hours - days * 24;

        date = delDays(days, date);

        int totalTime = (int) (date.getTime() - hrs * 3600);
        if (totalTime < 0) {
            date = delDays(1, date);
            date.setTime(24 * 3600 - Math.abs(totalTime));
        } else {
            date.setTime(totalTime);
        }
        return date;
    }

    public static Date delDays(int days, Date date) {
        Map<Integer, Integer> monthDay = CONSTANTS.monthsMap;
        int totalDays = date.getDate() - days;

        int i;
        if (totalDays == 0) {
            date = delMonths(1, date);
            if (Calendar.isLeapYear(date.getYear())) monthDay.put(2, 29);
            date.setDate(monthDay.get(date.getMonth()));
        } else {
            if (totalDays < 0) {
                totalDays = days - date.getDate();
                date = delMonths(1, date);
                for (i = date.getMonth(); i >= 0; i--) {
                    if (Calendar.isLeapYear(date.getYear())) monthDay.put(2, 29);
                    if (!Calendar.isLeapYear(date.getYear())) monthDay.put(2, 28);
                    if (i == 0) {
                        i = 12;
                        date = delYears(1, date);
                    }
                    totalDays = monthDay.get(i) - Math.abs(totalDays);
                    if (totalDays > 0) break;
                }
                date.setMonth(i);
                date.setDate(Math.abs(totalDays));
            } else
                date.setDate(totalDays);
        }
        return date;
    }

    public static Date delMonths(int months, Date date) {
        int totalMonths = date.getMonth() - months;

        int years;
        if (months % 12 == 0) {
            years = months / 12;
            date = delYears(years, date);
        } else {
            if (months > 12 || months >= date.getMonth()) {
                years = 1 + (months / 12);
                date = delYears(years, date);
                months = months - date.getMonth();
                totalMonths = (byte) (12 - (months % 12));
                date.setMonth(totalMonths);
            } else {
                date.setMonth((date.getMonth() - months));
            }
        }
        return date;
    }

    public static Date delYears(int years, Date date) {
       // System.out.println(date);
        int totalYears = date.getYear() - years;
        if (totalYears < 0) {
            try {
                throw new InvalidDateFormatException("Год меньше 0");
            } catch (InvalidDateFormatException e) {
                System.out.println(e.getMessage());
            }
        } else {
            if (Calendar.isLeapYear(date.getYear())) {
                if ((date.getMonth() == 2) && (date.getDate() == 29)) {
                    if (years % 4 == 0) date.setDate(date.getDate());
                    else {
                        date.setDate(date.getDate() - 1);
                      //  System.out.println(date);
                    }
                }
            }
            date.setYear(totalYears);
        }
        return date;
    }
}
