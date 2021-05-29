package DateHelpers;
import DatePackage.*;
import java.util.Map;

public class CountDate {
    //Нужно ещё предусматривать что год может быть високосным

    public static Date addSeconds(Date date, int seconds){
        int totalTime = date.getTime() + seconds;

        if(totalTime > DateConstants.SECONDS_PER_DAY){
            date = addDays(date, totalTime / DateConstants.SECONDS_PER_DAY);
            date.setTime(totalTime % DateConstants.SECONDS_PER_DAY);
        }
        else{
            date.setTime(totalTime);
        }
        return date;
    }


    public static Date addMinutes(Date date, int minutes){
        int totalTime = date.getTime() + (minutes * 60);
        if(totalTime > DateConstants.SECONDS_PER_DAY){
            date = addDays(date, totalTime / DateConstants.SECONDS_PER_DAY);
            date.setTime(totalTime % DateConstants.SECONDS_PER_DAY);
        }
        else{
            date.setTime(totalTime);
        }
        return date;
    }


    public static Date addHours(Date date, int hours){
        int totalTime = date.getTime() + (hours * 3600);
        if(totalTime > DateConstants.SECONDS_PER_DAY){
            date = addDays(date, totalTime / DateConstants.SECONDS_PER_DAY);
            date.setTime(totalTime % DateConstants.SECONDS_PER_DAY);
        }
        else{
            date.setTime(totalTime);
        }
        return date;
    }


    public static Date addDays(Date date, int days){
        Map<Integer, Integer> monthDay = DateConstants.getMonthDay();
        int daysInCurMonth = monthDay.get((int)date.getMonth());
        int totalDays = date.getDay() + days;
        if(totalDays > daysInCurMonth){
            date = addMonths(date, totalDays / daysInCurMonth);
            date.setDay((byte) (totalDays % daysInCurMonth));
        }
        else {
            date.setDay((byte) (totalDays % daysInCurMonth));
        }
        return date;
    }


    public static Date addMonths(Date date, int months){
        int totalMonths = date.getMonth() + months;
        if(totalMonths > DateConstants.QUANTITY_OF_MONTHS){
            date = addYears(date, totalMonths / DateConstants.QUANTITY_OF_MONTHS);
            date.setMonth((byte) (totalMonths % DateConstants.QUANTITY_OF_MONTHS));
        }
        else {
            date.setMonth((byte) (totalMonths % DateConstants.QUANTITY_OF_MONTHS));
        }
        return date;
    }


    public static Date addYears(Date date, int years){
        int totalYears = date.getYear() + years;
        date.setYear(totalYears);
        return date;
    }

}
