package ua.com.nkrasnovoronka.service;

import ua.com.nkrasnovoronka.exception.CalendarException;
import ua.com.nkrasnovoronka.model.Calendar;
import ua.com.nkrasnovoronka.model.Date;
import ua.com.nkrasnovoronka.model.Month;
import ua.com.nkrasnovoronka.model.Time;
import ua.com.nkrasnovoronka.util.Constants;

public class CalendarSubtractService {
    public static final int MIN_YEAR = 0;
    public static final int MONTH_IN_YEAR = 12;
    public static final int HOURS_IN_DAY = 24;
    public static final int MINUTES_IN_HOUR = 60;
    public static final int SECONDS_IN_MINUTE = 60;

    public Calendar subtractSecondsToDate(Calendar calendar, int seconds) throws CalendarException {
        Time time = calendar.getTime();
        Date date = calendar.getDate();
        int currentTime = time.getMinutes();
        int newSeconds = time.getSeconds() - seconds;
        if (newSeconds <= 0) {
            time.setSeconds((newSeconds + SECONDS_IN_MINUTE) % SECONDS_IN_MINUTE);
            calendar.setTime(time);
            return subtractMinutesToDate(calendar, (seconds + currentTime + SECONDS_IN_MINUTE) / SECONDS_IN_MINUTE);
        }
        time.setSeconds(newSeconds);
        calendar.setDate(date);
        return calendar;

    }

    public Calendar subtractMinutesToDate(Calendar calendar, int minutes) throws CalendarException {
        Time time = calendar.getTime();
        Date date = calendar.getDate();
        int currentTime = time.getMinutes();
        int newMinutes = time.getMinutes() - minutes;

        if (newMinutes <= 0) {
            time.setMinutes((newMinutes + MINUTES_IN_HOUR) % MINUTES_IN_HOUR);
            calendar.setTime(time);
            return subtractHoursToDate(calendar, (minutes + currentTime + MINUTES_IN_HOUR) / MINUTES_IN_HOUR);
        }
        time.setMinutes(newMinutes);
        calendar.setDate(date);
        return calendar;

    }

    public Calendar subtractHoursToDate(Calendar calendar, int hours) throws CalendarException {
        Time time = calendar.getTime();
        Date date = calendar.getDate();
        int currentTime = time.getHours();
        int newHours = time.getHours() - hours;

        if (newHours <= 0) {
            time.setHours((newHours + HOURS_IN_DAY) % (HOURS_IN_DAY - 1));
            calendar.setTime(time);
            return subtractDaysToDate(calendar, (hours + currentTime + HOURS_IN_DAY) / (HOURS_IN_DAY - 1));
        }
        time.setHours(newHours);
        calendar.setDate(date);
        return calendar;
    }

    public Calendar subtractDaysToDate(Calendar calendar, int days) throws CalendarException {
        Date date = calendar.getDate();
        int newDay = (date.getDay() - days);

        if (newDay <= 0) {
            date.setDay(getNewDay(newDay, date.getMonth()));
            calendar.setDate(date);
            return subtractMonthToDate(calendar, (date.getDay() + days) / 30);
        }
        date.setDay(newDay);
        calendar.setDate(date);
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

    public Calendar subtractMonthToDate(Calendar calendar, int month) throws CalendarException {
        Date date = calendar.getDate();
        int newMonth = (date.getMonth().ordinal() - month);
        if (newMonth <= 0) {
            if (newMonth == -1) {
                date.setMonth(Month.getMountByParameter(12));
            } else {
                date.setMonth(Month.getMountByParameter((newMonth + 12 + 1) % 12));
            }
            calendar.setDate(date);
            return subtractYearsToDate(calendar, (month + date.getMonth().ordinal() + 1) / MONTH_IN_YEAR);
        }
        date.setMonth(Month.getMountByParameter(date.getMonth().ordinal() - month + 1));
        calendar.setDate(date);
        return calendar;

    }

    public Calendar subtractYearsToDate(Calendar calendar, int years) throws CalendarException {
        Date date = calendar.getDate();
        int newYear = (date.getYear() - years);
        if (newYear < MIN_YEAR) {
            throw new CalendarException("Calendar min year is 0000");
        } else {
            date.setYear(newYear);
            if (Constants.isLeapYear(newYear)) {
                date.setDay(date.getDay() - 1);
            }
            calendar.setDate(date);
        }
        return calendar;
    }
}
