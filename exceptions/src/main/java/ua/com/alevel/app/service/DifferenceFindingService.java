package ua.com.alevel.app.service;

import ua.com.alevel.app.entity.Calendar;
import ua.com.alevel.app.util.Utils;

public class DifferenceFindingService {

    public int diffInSeconds(Calendar calendar1, Calendar calendar2){
        int sec1 = calendar1.getTime().getSeconds();
        int sec2 = calendar2.getTime().getSeconds();
        return Math.abs(sec1 - sec2) + diffInMinutes(calendar1, calendar2) * 60;
    }

    public int diffInMinutes(Calendar calendar1, Calendar calendar2){
        int min1 = calendar1.getTime().getMinutes();
        int min2 = calendar2.getTime().getMinutes();
        return Math.abs(min1 - min2) + diffInHours(calendar1, calendar2) * 60;
    }

    public int diffInHours(Calendar calendar1, Calendar calendar2){
        int hours1 = calendar1.getTime().getHours();
        int hours2 = calendar2.getTime().getHours();
        return Math.abs(hours1 - hours2) + diffInDays(calendar1, calendar2) * 24;
    }

    public int diffInDays(Calendar calendar1, Calendar calendar2){
        int day1 = calendar1.getDate().getDay();
        int day2 = calendar2.getDate().getDay();
        int month = diffInMonths(calendar1, calendar2);
        return Math.abs(day1 - day2) + diffMonthInDays(month, calendar1, calendar2);
    }

    public int diffInMonths(Calendar calendar1, Calendar calendar2){
        int month1 = calendar1.getDate().getMonth().ordinal() + 1;
        int month2 = calendar2.getDate().getMonth().ordinal() + 1;

        return Math.abs(month1 - month2) + diffInYears(calendar1, calendar2) * 12;
    }

    public int diffInYears(Calendar calendar1, Calendar calendar2){
        int year1 = calendar1.getDate().getYear();
        int year2 = calendar2.getDate().getYear();
        return Math.abs(year1 - year2);
    }

    private int diffMonthInDays(int month, Calendar calendar1, Calendar calendar2) {
        int[] days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int ans = 0;
        int index = calendar1.getDate().getMonth().ordinal();
        int year = Math.min(calendar1.getDate().getYear(), calendar2.getDate().getYear());
        int count = index;
        for (int i = 0; i < month; i++) {
            if(count == 12){
                year++;
            }
            if(Utils.isLeapYear(year)){
                days[1] = 29;
            }
            ans += days[index];
            index = (index + 1) % 12;
            count++;
        }
        return ans;
    }
}
