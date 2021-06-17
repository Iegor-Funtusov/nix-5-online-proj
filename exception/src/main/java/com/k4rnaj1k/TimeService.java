package com.k4rnaj1k;

public class TimeService {
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
        if (currDate.getSeconds() + seconds >= 60) {
            addMinutes(1, currDate);
            currDate.setSeconds(0);
            addSeconds(seconds - 60, currDate);
        }else{
        currDate.setSeconds(currDate.getSeconds() + seconds);
    }
    }

    public static void addMinutes(int minutes, MyDate currDate) {
        if (currDate.getMinutes() + minutes >= 60) {
            addHours(1, currDate);
            currDate.setMinutes(0);
            addMinutes(minutes - 60, currDate);
        }
        else{
        currDate.setMinutes(currDate.getMinutes() + minutes);
    }
    }

    public static void addHours(int hours, MyDate currDate) {
        if (currDate.getHours() + hours >= 24) {
            DateService.addDays(1, currDate);
            currDate.setHours(0);
            addHours(hours - 24, currDate);
        }else{
        currDate.setHours(currDate.getHours() + hours);
    }}

    public static void subSeconds(int seconds, MyDate currDate){
        int prevseconds = currDate.getSeconds();
        if(currDate.getSeconds() - seconds < 0){
            subMinutes(1, currDate);
            currDate.setSeconds(60);
            subSeconds(seconds - prevseconds, currDate);
        }else{
        currDate.setSeconds(currDate.getSeconds() - seconds);
    }
    }

    public static void subMinutes(int minutes, MyDate currDate){
        int prevminutes = currDate.getMinutes();
        if(currDate.getMinutes() - minutes < 0){
            subHours(1, currDate);
            currDate.setMinutes(60);
            subMinutes(minutes-prevminutes, currDate);
        }else{
        currDate.setMinutes(currDate.getMinutes()-minutes);
    }
    }

    public static void subHours(int hours, MyDate currDate){
        int prevhours = currDate.getHours();
        if(currDate.getHours() - hours < 0){
            DateService.subDays(1,currDate);
            currDate.setHours(24);
            subHours(hours - prevhours, currDate);
        }else{
        currDate.setHours(currDate.getHours() - hours);
    }
    }

}
