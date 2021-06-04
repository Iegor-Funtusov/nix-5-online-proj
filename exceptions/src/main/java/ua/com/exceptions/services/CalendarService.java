package ua.com.exceptions.services;

import ua.com.exceptions.entity.Calendar;
import ua.com.exceptions.entity.Date;
import ua.com.exceptions.entity.Time;

public class CalendarService {
    private static DateService dateService = new DateService();
    private static TimeService timeService = new TimeService();

    public static Calendar checkCalendar(int year, int month, int day, int hours, int minutes, int seconds){
        if(dateService.checkDate(year, month, day) != null &&
                timeService.checkTime(hours, minutes, seconds)!= null){
            return new Calendar(new Date(year, month, day), new Time(hours, minutes, seconds));
        }
        return null;
    }
}
