package service;

import domain.Calendar;
import domain.Date;
import utils.CONSTANTS;

import java.util.Map;

public class Addition {

    public static Date addSeconds(int seconds, Date date) {
        int min = seconds / 60;
        int sec = seconds - min * 60;

        date = addMinutes(min, date);

        int totalTime = (int) (date.getTime() + sec);
      /* if (totalTime >= 86400) {
            date = addDays(totalTime / 86400);
            date.setTime(totalTime % 86400);
            }
*/
        if (totalTime >= 86400) {
            date = addMinutes(1, date);
            date.setTime(totalTime % 86400);

        } else {
            date.setTime(totalTime);
        }

        return date;
    }

    public static Date addMinutes(int minutes, Date date) {

        int hrs = minutes / 60;
        int min = minutes - hrs * 60;
        date = addHours(hrs, date);

        int totalTime = (int) (date.getTime() + min * 60L);
      /*  if (totalTime >= 86400) {
            date = addDays(totalTime / 86400);
            date.setTime(totalTime % 86400);
        }*/

        if (totalTime >= 86400) {
            date = addHours(1, date);
            date.setTime(totalTime % 86400);
        } else {
            date.setTime(totalTime);
        }

        return date;
    }

    public static Date addHours(int hours, Date date) {

        int days = hours / 24;
        int hrs = hours - days * 24;
        date = addDays(days, date);

        int totalTime = (int) (date.getTime() + hrs * 3600L);
        if (totalTime >= 86400) {
          /*  date = addDays(totalTime / 86400);
            date.setTime(totalTime % 86400);*/
            date = addDays(1, date);
            date.setTime(totalTime % 86400);

        } else {
            date.setTime(totalTime);
        }
        return date;
    }

    public static Date addDays(int days, Date date) {
        Map<Integer, Integer> monthDay = CONSTANTS.monthsMap;
        int totalDays = date.getDate() + days;

        int i;
        if (totalDays > monthDay.get(date.getMonth())) {
            for (i = date.getMonth(); i <= 13; i++) {
                if (Calendar.isLeapYear(date.getYear())) monthDay.put(2, 29);
                if (!Calendar.isLeapYear(date.getYear())) monthDay.put(2, 28);
                if (i == 13) {
                    i = 1;
                    date = addYears(1, date);
                }
                if (totalDays <= monthDay.get(i)) break;
                totalDays = totalDays - monthDay.get(i);
            }
            date.setMonth(i);
            date.setDate(totalDays);
        } else
            date.setDate(totalDays);

        return date;
    }

    public static Date addMonths(int months, Date date) {
        int totalMonths = date.getMonth() + months;
        if (totalMonths % 12 == 0) {
            date = addYears(totalMonths / 12 - 1, date);
            date.setMonth(12);
        } else {
            if (totalMonths > 12) {
                date = addYears(totalMonths / 12, date);
                date.setMonth(totalMonths % 12);
            }
            date.setMonth(totalMonths % 12);
        }
        return date;
    }

    public static Date addYears(int years, Date date) {
        int totalYears = date.getYear() + years;
        if (Calendar.isLeapYear(date.getYear())) {
            if ((date.getMonth() == 2) && (date.getDate() == 29)) {
                if (years % 4 == 0) date.setDate(date.getDate());
                else date.setDate(date.getDate() - 1);
            }
        }

        date.setYear(totalYears);
        return date;
    }
}
