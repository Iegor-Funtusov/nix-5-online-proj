package com.k4rnaj1k;

public class TimeService {
    public static long parseMinutes(String minutes) throws NumberFormatException {
        long minutescount = Integer.parseInt(minutes);
        if (minutescount >= 0 && minutescount <= 59) {
            return minutescount;
        }
        throw new NumberFormatException();
    }

    public static long parseSeconds(String seconds) throws NumberFormatException {
        int secondsscount = Integer.parseInt(seconds);
        if (secondsscount >= 0 && secondsscount <= 59) {
            return secondsscount;
        }
        throw new NumberFormatException();
    }

    public static long parseHours(String hours) throws NumberFormatException {
        long hourscount = Integer.parseInt(hours);
        if (hourscount >= 0 && hourscount <= 23) {
            return hourscount;
        }
        throw new NumberFormatException();
    }

    public static void addSeconds(int seconds, MyDate currDate) {
        if (currDate.getSeconds() + seconds >= 60) {
            addMinutes(1, currDate);
            currDate.setSeconds(0L);
            addSeconds(seconds - 60, currDate);
        } else {
            currDate.setSeconds(currDate.getSeconds() + seconds);
        }
    }

    public static void addMinutes(int minutes, MyDate currDate) {
        if (currDate.getMinutes() + minutes >= 60) {
            addHours(1, currDate);
            currDate.setMinutes(0L);
            addMinutes(minutes - 60, currDate);
        } else {
            currDate.setMinutes(currDate.getMinutes() + minutes);
        }
    }

    public static void addHours(int hours, MyDate currDate) {
        if (currDate.getHours() + hours >= 24) {
            DateService.addDays(1, currDate);
            currDate.setHours(0L);
            addHours(hours - 24, currDate);
        } else {
            currDate.setHours(currDate.getHours() + hours);
        }
    }

    public static void subSeconds(long seconds, MyDate currDate) {
        long prevseconds = currDate.getSeconds();
        if (currDate.getSeconds() - seconds < 0) {
            subMinutes(1, currDate);
            currDate.setSeconds(60L);
            subSeconds(seconds - prevseconds, currDate);
        } else {
            currDate.setSeconds(currDate.getSeconds() - seconds);
        }
    }

    public static void subMinutes(long minutes, MyDate currDate) {
        long prevminutes = currDate.getMinutes();
        if (currDate.getMinutes() - minutes < 0) {
            subHours(1, currDate);
            currDate.setMinutes(60L);
            subMinutes(minutes - prevminutes, currDate);
        } else {
            currDate.setMinutes(currDate.getMinutes() - minutes);
        }
    }

    public static void subHours(long hours, MyDate currDate) {
        long prevhours = currDate.getHours();
        if (currDate.getHours() - hours < 0) {
            DateService.subDays(1, currDate);
            currDate.setHours(24L);
            subHours(hours - prevhours, currDate);
        } else {
            currDate.setHours(currDate.getHours() - hours);
        }
    }

}
