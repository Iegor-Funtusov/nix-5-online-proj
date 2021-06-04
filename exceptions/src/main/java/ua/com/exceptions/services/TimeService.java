package ua.com.exceptions.services;

import ua.com.exceptions.entity.Time;

public class TimeService {

    public Time checkTime(int hours, int minutes, int seconds){
        try{
            if(validHour(hours) && validMinute(minutes) && validSeconds(seconds)){
                return new Time(hours, minutes, seconds);
            }
        }
        catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private boolean validHour(int hours){
        if(hours >=0 && hours <= 23){
            return true;
        }
        else{
            throw new RuntimeException("Error! Hour should be in a range of 0-23");
        }
    }

    private boolean validMinute(int minutes){
        if(minutes >=0 && minutes <= 59){
            return true;
        }
        else {
            throw new RuntimeException("Error! Minutes should be in a range of 0-59");
        }
    }

    private boolean validSeconds(int seconds){
        if(seconds >=0 && seconds <= 59){
            return true;
        }
        else {
            throw new RuntimeException("Error! Seconds should be in a range of 0-59");
        }
    }

}

