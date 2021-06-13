package ua.com.alevel.app.service;

import ua.com.alevel.app.entity.Calendar;
import ua.com.alevel.app.entity.Date;
import ua.com.alevel.app.entity.Month;
import ua.com.alevel.app.entity.Time;
import ua.com.alevel.app.exception.OutOfBoundsException;
import ua.com.alevel.app.util.Utils;

public class AddingService {

    public Calendar addSeconds(Calendar calendar, int seconds) throws OutOfBoundsException {
        Time time = calendar.getTime();
        Date date = calendar.getDate();
        int num = time.getSeconds() + seconds;

        if (num >= 60) {
            time.setSeconds(num % 60);
            calendar.setTime(time);
            return addMinutes(calendar, num / 60);
        }
        time.setSeconds(num);
        calendar.setDate(date);
        return calendar;
    }

    public Calendar addMinutes(Calendar calendar, int minutes) throws OutOfBoundsException {
        Time time = calendar.getTime();
        Date date = calendar.getDate();
        int num = time.getMinutes() + minutes;

        if (num >= 60) {
            time.setMinutes(num % 60);
            calendar.setTime(time);
            return addHours(calendar, num / 60);
        }
        time.setMinutes(num);
        calendar.setDate(date);
        return calendar;
    }

    public Calendar addHours(Calendar calendar, int hours) throws OutOfBoundsException {
        Time time = calendar.getTime();
        Date date = calendar.getDate();
        int num = time.getHours() + hours;

        if (num >= 24) {
            time.setHours(num % 24);
            calendar.setTime(time);
            return addDays(calendar, num / 24);
        }
        time.setHours(num);
        calendar.setDate(date);
        return calendar;
    }

    public Calendar addDays(Calendar calendar, int days) throws OutOfBoundsException {
        Date date = calendar.getDate();
        int num = date.getDay() + days;

        if (num > date.getMonth().getNumberOfDays()) {
            date.setDay(getNewDay(num, date.getMonth()));
            calendar.setDate(date);
            return addMonths(calendar, num / date.getMonth().getNumberOfDays());
        }
        date.setDay(num);
        calendar.setDate(date);
        return calendar;
    }

    public Calendar addMonths(Calendar calendar, int month) throws OutOfBoundsException {
        Date date = calendar.getDate();
        int num = date.getMonth().ordinal() + 1 + month;

        if (num > 12) {
            date.setMonth(Month.getMonthByParameter(num % 12));
            calendar.setDate(date);
            return addYears(calendar, num / 12);
        }
        date.setMonth(Month.getMonthByParameter(num));
        calendar.setDate(date);
        return calendar;
    }

    public Calendar addYears(Calendar calendar, int years) throws OutOfBoundsException {
        Date date = calendar.getDate();
        int num = (date.getYear() + years);

        if (num > 9999) {
            throw new OutOfBoundsException("Year cannot be bigger than 9999");
        } else {
            date.setYear(num);
            if (Utils.isLeapYear(num)) {
                date.setDay(date.getDay() - 1);
            }
            calendar.setDate(date);
        }
        return calendar;
    }

    private int getNewDay(int day, Month startMonth) {
        int[] days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int ans = day;
        int index = startMonth.ordinal();
        while (ans > startMonth.getNumberOfDays()) {
            ans -= days[index];
            index = (index + 1) % 12;
        }
        return ans;
    }
}
