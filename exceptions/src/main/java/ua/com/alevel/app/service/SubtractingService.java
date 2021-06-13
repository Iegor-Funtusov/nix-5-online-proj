package ua.com.alevel.app.service;

import ua.com.alevel.app.entity.Calendar;
import ua.com.alevel.app.entity.Date;
import ua.com.alevel.app.entity.Month;
import ua.com.alevel.app.entity.Time;
import ua.com.alevel.app.exception.OutOfBoundsException;
import ua.com.alevel.app.util.Utils;

public class SubtractingService {

    public Calendar subSeconds(Calendar calendar, int seconds) throws OutOfBoundsException {
        Time time = calendar.getTime();
        Date date = calendar.getDate();
        int currentTime = time.getMinutes();
        int num = time.getSeconds() - seconds;

        if (num <= 0) {
            time.setSeconds((num + 60) % 60);
            calendar.setTime(time);
            return subMinutes(calendar, (seconds + currentTime + 60) / 60);
        }
        time.setSeconds(num);
        calendar.setDate(date);
        return calendar;
    }

    public Calendar subMinutes(Calendar calendar, int minutes) throws OutOfBoundsException {
        Time time = calendar.getTime();
        Date date = calendar.getDate();
        int currentTime = time.getMinutes();
        int num = time.getMinutes() - minutes;

        if (num <= 0) {
            time.setMinutes((num + 60) % 60);
            calendar.setTime(time);
            return subHours(calendar, (minutes + currentTime + 60) / 60);
        }
        time.setMinutes(num);
        calendar.setDate(date);
        return calendar;
    }

    public Calendar subHours(Calendar calendar, int hours) throws OutOfBoundsException {
        Time time = calendar.getTime();
        Date date = calendar.getDate();
        int currentTime = time.getHours();
        int num = time.getHours() - hours;

        if (num <= 0) {
            time.setHours((num + 24) % (24));
            calendar.setTime(time);
            return subDays(calendar, (hours + currentTime + 24) / 23);
        }
        time.setHours(num);
        calendar.setDate(date);
        return calendar;
    }

    public Calendar subDays(Calendar calendar, int days) throws OutOfBoundsException {
        Date date = calendar.getDate();
        int num = (date.getDay() - days);

        if (num <= 0) {
            date.setDay(getNewDay(num, date.getMonth()));
            calendar.setDate(date);
            return subMonths(calendar, (date.getDay() + days) / 30);
        }
        date.setDay(num);
        calendar.setDate(date);
        return calendar;
    }

    public Calendar subMonths(Calendar calendar, int month) throws OutOfBoundsException {
        Date date = calendar.getDate();
        int num = (date.getMonth().ordinal() - month);

        if (num <= 0) {
            if (num == -1) {
                date.setMonth(Month.getMonthByParameter(12));
            } else {
                date.setMonth(Month.getMonthByParameter((num + 12 + 1) % 12));
            }
            calendar.setDate(date);
            return subYears(calendar, (month + date.getMonth().ordinal() + 1) / 12);
        }
        date.setMonth(Month.getMonthByParameter(date.getMonth().ordinal() - month + 1));
        calendar.setDate(date);
        return calendar;
    }

    public Calendar subYears(Calendar calendar, int years) throws OutOfBoundsException {
        Date date = calendar.getDate();
        int num = (date.getYear() - years);

        if (num < 0) {
            throw new OutOfBoundsException("Year cannot be smaller than 0");
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
        while (true) {
            index = (index - 1) % 12;
            ans += days[index];
            if (ans <= 31 && ans > 0) {
                break;
            }
            if (index - 1 < 0) {
                index = days.length;
            }
        }
        return Math.abs(ans);
    }
}
