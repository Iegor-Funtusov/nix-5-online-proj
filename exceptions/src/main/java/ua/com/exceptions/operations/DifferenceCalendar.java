package ua.com.exceptions.operations;

import ua.com.exceptions.entity.Calendar;

public class DifferenceCalendar {

    public static int getDiffInSeconds(Calendar a, Calendar b) {
        return Math.abs(a.getSecond() - b.getSecond());
    }

    public static long getDiffInMinutes(Calendar a, Calendar b){
        return Math.abs(a.getMinute() -b.getMinute());
    }

    public static int getDiffInHours(Calendar a, Calendar b){
        return Math.abs(a.getHour() - b.getHour());
    }

    public static int getDiffInDays(Calendar a, Calendar b){
        return Math.abs(a.getDay()-b.getDay());
    }

    public static int getDiffInMonths(Calendar a, Calendar b){
       return Math.abs(a.getMonth()-b.getMonth());
    }

    public static int getDiffInYears(Calendar a, Calendar b){
       return Math.abs(a.getYear()-b.getYear());
    }

}
