package ua.com.nkrasnovoronka.formatter;

import ua.com.nkrasnovoronka.exception.TimeFormatException;
import ua.com.nkrasnovoronka.model.Time;
import ua.com.nkrasnovoronka.util.Constants;

import java.util.StringJoiner;

public class TimeFormatter {

    public static final String TIME_DELIMITER = ":";
    private static final int DEFAULT_HOURS = 0;
    private static final int DEFAULT_MINUTES = 0;
    private static final int DEFAULT_SECONDS= 0;

    private int hour = DEFAULT_HOURS;
    private int minute = DEFAULT_MINUTES;
    private int seconds = DEFAULT_SECONDS;

    public Time formatStringToTime(String input) throws TimeFormatException{
        if(input.contains(TIME_DELIMITER)){
            String[] split = input.trim().split(TIME_DELIMITER);
            if(split.length > 1 && split.length <= 3){
                ifPassedTwoNumbers(split);
                ifPassedThreeNumbers(split);
                return new Time(hour, minute, seconds);
            }
        }
        throw new TimeFormatException("Invalid time format. Time format must be hh:mm:ss or hh:mm or mm:ss");

    }

    public String formatTimeToString(Time time){
        StringJoiner sj = new StringJoiner(TIME_DELIMITER);
        sj.add(String.format("%02d", time.getHours()));
        sj.add(String.format("%02d", time.getMinutes()));
        sj.add(String.format("%02d", time.getSeconds()));
        return sj.toString();
    }

    private void ifPassedThreeNumbers(String[] split) throws TimeFormatException {
        if(split.length == 3){
           if(split[0].matches(Constants.HOUR_REGEXP) && split[1].matches(Constants.MINUTE_REGEXP) && split[0].matches(Constants.SECONDS_REGEXP)){
               hour = Integer.parseInt(split[0]);
               minute = Integer.parseInt(split[1]);
               seconds = Integer.parseInt(split[2]);
           }else {
               throw new TimeFormatException("Invalid time values. Pleas check your input");
           }
       }
    }

    private void ifPassedTwoNumbers(String[] split) throws TimeFormatException {
        if(split.length == 2){
            if(split[0].matches(Constants.HOUR_REGEXP) && split[1].matches(Constants.MINUTE_REGEXP)){
                hour = Integer.parseInt(split[0]);
                minute = Integer.parseInt(split[1]);
            }
            else if(split[0].matches(Constants.MINUTE_REGEXP) && split[1].matches(Constants.SECONDS_REGEXP)){
                minute = Integer.parseInt(split[0]);
                seconds = Integer.parseInt(split[1]);
            }
            else {
                throw new TimeFormatException("Invalid time values. Pleas check your input");

            }
        }
    }
}
