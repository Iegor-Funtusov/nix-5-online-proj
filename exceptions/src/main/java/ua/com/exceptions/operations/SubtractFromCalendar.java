package ua.com.exceptions.operations;

import ua.com.exceptions.entity.Calendar;
import ua.com.exceptions.services.CalendarService;

public class SubtractFromCalendar {

    private static final int GENERAL_MINUTES_SECONDS = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final int[] MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static Calendar subtractSeconds(Calendar calendar, int seconds){
        int secondsNew = calendar.getSecond() - seconds;
        if (secondsNew >= 0){
            calendar.setSecond(secondsNew);
            return calendar;
        }
        else{
            seconds -= calendar.getSecond();
            int minutes = 1;
            while (seconds >= GENERAL_MINUTES_SECONDS ){
                minutes++;
                seconds -= GENERAL_MINUTES_SECONDS;
            }
            calendar.setSecond(Math.abs(GENERAL_MINUTES_SECONDS - seconds));
            return SubtractFromCalendar.subtractMinutes(calendar, minutes);
        }
    }

    public static Calendar subtractMinutes(Calendar calendar, int minutes){
        int minutesNew = calendar.getMinute() - minutes;
        if (minutesNew >= 0 && minutesNew < GENERAL_MINUTES_SECONDS){
            calendar.setMinute(minutesNew);
            return calendar;
        }
        else{
            int hours = 1;
            minutes -= calendar.getMinute();
            while (minutes >= GENERAL_MINUTES_SECONDS){
                hours++;
                minutes -= GENERAL_MINUTES_SECONDS;
            }
            calendar.setMinute(GENERAL_MINUTES_SECONDS - minutes);
            return SubtractFromCalendar.subtractHourse(calendar, hours);
        }
    }

    public static Calendar subtractHourse(Calendar calendar, int hours){
        int hoursNew = calendar.getHour() - hours;
        if (hoursNew >= 0 && hoursNew <=23){
            calendar.setHour(hoursNew);
            return calendar;
        }
        else{
            int days = 1;
            hours -= calendar.getHour();
            while (hours >= HOURS_IN_DAY){
                days++;
                hours -= HOURS_IN_DAY;
            }
            calendar.setHour(HOURS_IN_DAY - hours);
            return SubtractFromCalendar.subtractDay(calendar, days);
        }
    }

    public static Calendar subtractDay(Calendar calendar, int day) {
        int dayNew = calendar.getDay() - day;
        int k = 0;
        if(dayNew == 0){
            if(calendar.getMonth()-1!=0){
                calendar.setDay(CalendarService.getMonthCount
                        (calendar.getMonth()-1, calendar.getYear()));
            calendar.setMonth(calendar.getMonth()-1);
            return calendar;}
        if(calendar.getMonth()-1 == 0){
                calendar.setDay(31);
                calendar.setMonth(1);
                calendar.setYear(calendar.getYear()-1);
                return calendar;
            }
        }
        if (dayNew <= CalendarService.getMonthCount(calendar.getMonth(),
                calendar.getYear()) && dayNew > 0) {
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
            int willBeDaysAfterSubtract = 0;
            if(wasDays > day) {
                willBeDaysAfterSubtract = wasDays - day;
                int sumDay = willBeDaysAfterSubtract;
                int resultMonth = 1;
                for(int i = 0; i < MONTHS.length; i++){
                    if(sumDay > MONTHS[i]){
                        if(i == 1 && CalendarService.leapYearCheck(calendar.getYear()) == 1) {
                            sumDay--;
                        }
                        sumDay -= MONTHS[i];
                        resultMonth++;
                    }
                }
                calendar.setDay(sumDay);
                calendar.setMonth(resultMonth);
            }
            else {
                willBeDaysAfterSubtract = day - wasDays;
                int countyear = 12;
                while (willBeDaysAfterSubtract > 365 +
                        CalendarService.leapYearCheck(calendar.getYear())){
                    willBeDaysAfterSubtract-= 365 + CalendarService.leapYearCheck(calendar.getYear() - k);
                    k++;
                    countyear+=12;
                }
                int sumDay = willBeDaysAfterSubtract;
                int resultMonth = 12;
                for(int i = 0; i < MONTHS.length; i++){
                    if(sumDay > MONTHS[12-i-1]){
                        if(i == 1 && CalendarService.leapYearCheck(calendar.getYear() - k - 1) == 1 ) {
                            sumDay--;
                        }
                        sumDay -= MONTHS[12-i-1];
                        resultMonth--;
                    }
                   if(sumDay < MONTHS[12-i-1]){
                        int res = MONTHS[12-i-1] - sumDay;
                        calendar.setDay(res);
                        calendar.setMonth(resultMonth);
                        break;
                    }
                }
                if(countyear >= 12){
                    return SubtractFromCalendar.subtractYear(calendar, countyear/12);
                }
            }
            return calendar;
        }
    }

    public static Calendar subtractMonth(Calendar calendar, int month){
        int year = 0;
        if(calendar.getMonth() == month){
            calendar.setMonth(12);
            calendar.setYear(calendar.getYear()-1);
            return calendar;
        }
        if(calendar.getMonth() - month > 0){
            calendar.setMonth(12 - calendar.getMonth() - month);
            return calendar;
        }
        if(calendar.getMonth() - month <= 0){
            month -= calendar.getMonth();
            year++;
        }
        if(month >= 12){
            int monthNew = month;
            while (monthNew >= 12){
                monthNew -= 12;
                year++;
            }
            System.out.println(year);
            calendar = SubtractFromCalendar.subtractYear(calendar, year);
            month = monthNew;
        }
            calendar.setMonth(Math.abs(12 - month));
        return calendar;
    }

    public static Calendar subtractYear(Calendar calendar, int year){
        calendar.setYear(calendar.getYear() - year);
        return calendar;
    }

}
