package ua.com.nkrasnovoronka.service;

import ua.com.nkrasnovoronka.model.Calendar;
import ua.com.nkrasnovoronka.util.Constants;

public class CalendarDifferenceService {
    public static final int MONTH_IN_YEAR = 12;
    public static final int HOURS_IN_DAY = 24;
    public static final int MINUTES_IN_HOUR = 60;
    public static final int SECONDS_IN_MINUTE = 60;

    public int differenceInSeconds(Calendar calendar1, Calendar calendar2){
        int sec1 = calendar1.getTime().getSeconds();
        int sec2 = calendar2.getTime().getSeconds();
        return Math.abs(sec1 - sec2) + differenceInMinutes(calendar1, calendar2) * MINUTES_IN_HOUR;
    }

    public int differenceInMinutes(Calendar calendar1, Calendar calendar2){
        int min1 = calendar1.getTime().getMinutes();
        int min2 = calendar2.getTime().getMinutes();
        return Math.abs(min1 - min2) + differenceInHours(calendar1, calendar2) * MINUTES_IN_HOUR;
    }

    public int differenceInHours(Calendar calendar1, Calendar calendar2){
        int hours1 = calendar1.getTime().getHours();
        int hours2 = calendar2.getTime().getHours();
        return Math.abs(hours1 - hours2) + differenceInDays(calendar1, calendar2) * HOURS_IN_DAY;
    }

    public int differenceInDays(Calendar calendar1, Calendar calendar2){
        int day1 = calendar1.getDate().getDay();
        int day2 = calendar2.getDate().getDay();
        int month = differenceInMonth(calendar1, calendar2);
        return Math.abs(day1 - day2) + differenceMonthInDays(month, calendar1, calendar2);
    }

    private int differenceMonthInDays(int month, Calendar calendar1, Calendar calendar2) {
        int[] days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int ans = 0;
        int index = calendar1.getDate().getMonth().ordinal();
        int year = Math.min(calendar1.getDate().getYear(), calendar2.getDate().getYear());
        int count = index;
        for (int i = 0; i < month; i++) {
            if(count == 12){
                year++;
            }
            if(Constants.isLeapYear(year)){
                days[1] = 29;
            }
            ans += days[index];
            index = (index + 1) % 12;
            count++;
        }
        return ans;
    }

    public int differenceInMonth(Calendar calendar1, Calendar calendar2){
        int month1 = calendar1.getDate().getMonth().ordinal() + 1;
        int month2 = calendar2.getDate().getMonth().ordinal() + 1;

        return Math.abs(month1 - month2) + differenceInYear(calendar1, calendar2) * MONTH_IN_YEAR;
    }
    public int differenceInYear(Calendar calendar1, Calendar calendar2){
        int year1 = calendar1.getDate().getYear();
        int year2 = calendar2.getDate().getYear();
        return Math.abs(year1 - year2);
    }
}
