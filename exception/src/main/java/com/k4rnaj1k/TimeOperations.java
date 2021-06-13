package com.k4rnaj1k;

public class TimeOperations {
    public static int parseMinutes(String minutes) throws NumberFormatException {
        int minutescount = Integer.parseInt(minutes);
        if (minutescount >= 0 && minutescount <= 60) {
            return minutescount;
        }
        throw new NumberFormatException();
    }

    public static int parseSeconds(String seconds) throws NumberFormatException {
        int secondsscount = Integer.parseInt(seconds);
        if (secondsscount >= 0 && secondsscount <= 60) {
            return secondsscount;
        }
        throw new NumberFormatException();
    }

    public static int parseHours(String hours) throws NumberFormatException {
        int hourscount = Integer.parseInt(hours);
        if (hourscount >= 0 && hourscount <= 24) {
            return hourscount;
        }
        throw new NumberFormatException();
    }

    public static void addSeconds(int seconds, MyDate currDate) {
        if (currDate.getSeconds() + seconds > 60) {
            addMinutes(1, currDate);
            currDate.setSeconds(0);
            addSeconds(seconds - 60, currDate);
        }
        currDate.setSeconds(currDate.getSeconds() + seconds);
    }

    public static void addMinutes(int minutes, MyDate currDate) {
        if (currDate.getMinutes() + minutes > 60) {
            addHours(1, currDate);
            currDate.setMinutes(0);
            addMinutes(minutes - 60, currDate);
        }
        currDate.setMinutes(currDate.getMinutes() + minutes);
    }

    public static void addHours(int hours, MyDate currDate) {
        if (currDate.getHours() + hours > 24) {
            DateOperations.addDays(1, currDate);
            currDate.setHours(0);
            addHours(hours - 24, currDate);
        }
        currDate.setHours(currDate.getHours() + hours);
    }

}
