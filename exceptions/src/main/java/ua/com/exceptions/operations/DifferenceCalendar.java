package ua.com.exceptions.operations;

import ua.com.exceptions.entity.Calendar;
import ua.com.exceptions.services.CalendarService;

public class DifferenceCalendar {

    private static final int[] MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int GENERAL_MINUTES_SECONDS = 60;
    private static final int HOURS_IN_DAY = 24;

    public static long getDiffInSeconds(Calendar a, Calendar b) {
        Calendar[] calendars = getSequence(a, b);
        Calendar first = calendars[0];
        Calendar second = calendars[1];
        long res = first.getSecond() + GENERAL_MINUTES_SECONDS - second.getSecond();
        int minute = 0;
        if (res >= GENERAL_MINUTES_SECONDS) {
            res -= 60;
            minute ++;
        }
        return res + (getDiffInMinutes(first, second) + minute) * GENERAL_MINUTES_SECONDS;
    }

    public static long getDiffInMinutes(Calendar first, Calendar second){
        long res = first.getMinute() + GENERAL_MINUTES_SECONDS - second.getMinute();
        int hour = 0;
        if (res >= GENERAL_MINUTES_SECONDS) {
            res -= 60;
            hour++;
        }
        return res + (getDiffInHours(first, second) + hour) * GENERAL_MINUTES_SECONDS;
    }

    public static int getDiffInHours(Calendar first, Calendar second){
        int res = first.getHour() + HOURS_IN_DAY - second.getHour();
        int day = 0;
        if(res>=HOURS_IN_DAY){
            res -= HOURS_IN_DAY;
            day++;
        }
        return res + (getDiffInDays(first, second) + day) * HOURS_IN_DAY;
    }

    public static int getDiffInDays(Calendar first, Calendar second){
        int first_day = first.getDay();
        int second_day = CalendarService.getMonthCount(second.getMonth(), second.getYear()) - second.getDay();
        int res = Math.abs(first_day - second_day);
        int monthDiffInCurrentYear = first.getMonth() - second.getMonth();
        if(res >= CalendarService.getMonthCount(monthDiffInCurrentYear, first.getYear())){
            res -= CalendarService.getMonthCount(monthDiffInCurrentYear, first.getYear());
            monthDiffInCurrentYear++;
        }
        int days = 0;
        for(int i = 0; i < monthDiffInCurrentYear - 1; i++){
            days += CalendarService.getMonthCount(i, first.getYear());
        }

        int yearDiff = getDiffInYears(first, second);
        int leap = 0;
        if(yearDiff > 0){
            for(int j = 0; j < yearDiff; j++){
                leap += CalendarService.leapYearCheck(first.getYear()-j);
            }
        }

        return res + days +
                (((getDiffInMonths(first, second) - monthDiffInCurrentYear) / 12) * 365) + leap;
    }


    public static int getDiffInMonths(Calendar first, Calendar second){
        return Math.abs(first.getMonth() - second.getMonth()) +
                getDiffInYears(first, second) * 12;
    }

    public static int getDiffInYears(Calendar first, Calendar second){
        return Math.abs(first.getYear() - second.getYear());
    }

    public static Calendar[] getSequence(Calendar a, Calendar b){
        if(a.getYear() > b.getYear()) {
            return new Calendar [] {a, b};
        }
        if(a.getMonth() > b.getMonth()){
            return new Calendar [] {a, b};
        }
        if(a.getDay() > b.getDay()){
            return new Calendar [] {a, b};
        }
        if(a.getHour() > b.getHour()) {
            return new Calendar[] {a, b};
        }
        if(a.getMinute() > b.getMinute()) {
            return new Calendar[] {a, b};
        }
        if(a.getSecond() > b.getSecond()){
            return new Calendar[] {a, b};
        }
        return new Calendar[] {b, a};
    }
}
