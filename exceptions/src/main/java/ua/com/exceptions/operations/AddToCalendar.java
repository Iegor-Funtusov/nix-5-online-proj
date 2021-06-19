package ua.com.exceptions.operations;

import ua.com.exceptions.entity.Calendar;
import ua.com.exceptions.services.CalendarService;

public class AddToCalendar {

    private static final int GENERAL_MINUTES_SECONDS = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final int[] MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static Calendar addSeconds(Calendar calendar, int seconds){
            int secondsNew = calendar.getSecond() + seconds;
            if (secondsNew <= GENERAL_MINUTES_SECONDS-1){
                calendar.setSecond(secondsNew);
                return calendar;
            }
            else{
                int minutes = 0;
                while (secondsNew >= GENERAL_MINUTES_SECONDS ){
                    minutes++;
                    secondsNew -= GENERAL_MINUTES_SECONDS;
                }
                calendar.setSecond(secondsNew);
                return AddToCalendar.addMinutes(calendar, minutes);
            }
        }

    public static Calendar addMinutes(Calendar calendar, int minutes){
            int minutesNew = calendar.getMinute() + minutes;
            if (minutesNew <= GENERAL_MINUTES_SECONDS-1){
                calendar.setMinute(minutesNew);
                return calendar;
            }
            else{
                int hours = 0;
                while (minutesNew >= GENERAL_MINUTES_SECONDS){
                    hours++;
                    minutesNew -= GENERAL_MINUTES_SECONDS;
                }
                calendar.setMinute(minutesNew);
                return AddToCalendar.addHourse(calendar, hours);
            }
        }

    public static Calendar addHourse(Calendar calendar, int hours){
            int hoursNew = calendar.getHour() + hours;
            if (hoursNew <= 23){
                calendar.setHour(hoursNew);
                return calendar;
            }
            else{
                int days = 0;
                while (hoursNew >= HOURS_IN_DAY){
                    days++;
                    hoursNew -= HOURS_IN_DAY;
                }
                calendar.setHour(hoursNew);
                return AddToCalendar.addDay(calendar, days);
            }
    }

    public static Calendar addDay(Calendar calendar, int day) {
            int dayNew = calendar.getDay() + day;
            if(dayNew <= CalendarService.getMonthCount(calendar.getMonth(),
                    calendar.getYear())) {
                calendar.setDay(dayNew);
                return calendar;
            }
            else {
                int wasDays = calendar.getDay();
                for(int i = 0; i < calendar.getMonth()-1; i++){
                    if(i == 1){
                        wasDays+= CalendarService.leapYearCheck(calendar.getYear());
                    }
                    wasDays += MONTHS[i];
                }

                int willBeDaysAfterAdd = wasDays + day;
                int k = 0;
                int countyear = 0;
                System.out.println(willBeDaysAfterAdd);
                if(willBeDaysAfterAdd >= 365 + CalendarService.leapYearCheck(calendar.getYear())){
                    while (willBeDaysAfterAdd >= 365 + CalendarService.leapYearCheck(calendar.getYear() + k)){
                        willBeDaysAfterAdd-= 365 + CalendarService.leapYearCheck(calendar.getYear() + k);
                        System.out.println(willBeDaysAfterAdd);
                        k++;
                        countyear+=12;
                    }
                }
                        int sumDay = willBeDaysAfterAdd;
                        int resultMonth = 1;
                        for(int i = 0; i < MONTHS.length; i++){
                            if(sumDay > MONTHS[i]){
                               if(i == 1 && CalendarService.leapYearCheck(calendar.getYear() + k) == 1) {
                                   sumDay--;
                               }
                               sumDay -= MONTHS[i];
                               resultMonth++;
                            }
                        }
                        calendar.setDay(sumDay);
                        calendar.setMonth(resultMonth);
                        if (countyear != 0){
                            return AddToCalendar.addMonth(calendar, countyear);
                        }
                return calendar;
            }
    }

    public static Calendar addMonth(Calendar calendar, int month){
        if(month >= 12){
            int monthNew = month;
            int year = 0;
            while (monthNew >= 12){
                monthNew -= 12;
                year++;
            }
            calendar = AddToCalendar.addYear(calendar, year);
            month = monthNew;
        }
        if(calendar.getMonth() + month <= 12){
            calendar.setMonth(calendar.getMonth() + month);
        }
        else {
            calendar.setMonth(calendar.getMonth() + month - 12);
            calendar.setYear(calendar.getYear() + 1);
        }
        return calendar;
    }

    public static Calendar addYear(Calendar calendar, int year){
        calendar.setYear(calendar.getYear() + year);
        return calendar;
    }
}
