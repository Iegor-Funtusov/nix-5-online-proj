package ua.com.nkrasnovoronka.service;

import ua.com.nkrasnovoronka.exception.CalendarException;
import ua.com.nkrasnovoronka.model.Calendar;
import ua.com.nkrasnovoronka.model.Date;
import ua.com.nkrasnovoronka.model.Month;
import ua.com.nkrasnovoronka.model.Time;
import ua.com.nkrasnovoronka.util.Constants;

public class CalendarAddingService {
    public static final int MAX_YEAR = 9999;
    public static final int MONTH_IN_YEAR = 12;
    public static final int HOURS_IN_DAY = 24;
    public static final int MINUTES_IN_HOUR = 60;
    public static final int SECONDS_IN_MINUTE = 60;

    public Calendar addSecondsToDate(Calendar calendar, int seconds) throws CalendarException {
        Time time = calendar.getTime();
        Date date = calendar.getDate();
        int newSeconds = time.getSeconds() + seconds;

        if (newSeconds > SECONDS_IN_MINUTE - 1) {
            time.setSeconds(newSeconds % SECONDS_IN_MINUTE);
            calendar.setTime(time);
            return addMinutesToDate(calendar, newSeconds / SECONDS_IN_MINUTE);
        }
        time.setSeconds(newSeconds);
        calendar.setDate(date);
        return calendar;

    }
    public Calendar addMinutesToDate(Calendar calendar, int minutes) throws CalendarException {
        Time time = calendar.getTime();
        Date date = calendar.getDate();
        int newMinutes = time.getMinutes() + minutes;

        if (newMinutes > MINUTES_IN_HOUR - 1) {
            time.setMinutes(newMinutes % MINUTES_IN_HOUR);
            calendar.setTime(time);
            return addHoursToDate(calendar, newMinutes / MINUTES_IN_HOUR);
        }
        time.setMinutes(newMinutes);
        calendar.setDate(date);
        return calendar;

    }
    public Calendar addHoursToDate(Calendar calendar, int hours) throws CalendarException {
        Time time = calendar.getTime();
        Date date = calendar.getDate();
        int newHours = time.getHours() + hours;

        if (newHours > HOURS_IN_DAY - 1) {
            time.setHours(newHours % HOURS_IN_DAY);
            calendar.setTime(time);
            return addDaysToDate(calendar, newHours / HOURS_IN_DAY);
        }
        time.setHours(newHours);
        calendar.setDate(date);
        return calendar;
    }
    public Calendar addDaysToDate(Calendar calendar, int days) throws CalendarException {
        Date date = calendar.getDate();
        int newDay = date.getDay() + days;

        if (newDay > date.getMonth().getNumberOfDays()) {
            date.setDay(getNewDay(newDay, date.getMonth()));
            calendar.setDate(date);
            return addMonthToDate(calendar, newDay / date.getMonth().getNumberOfDays());
        }
        date.setDay(newDay);
        calendar.setDate(date);
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
    public Calendar addMonthToDate(Calendar calendar, int month) throws CalendarException {
        Date date = calendar.getDate();
        int newMonth = date.getMonth().ordinal() + 1 + month;
        if (newMonth > MONTH_IN_YEAR) {
            date.setMonth(Month.getMountByParameter(newMonth % MONTH_IN_YEAR));
            calendar.setDate(date);
            return addYearsToDate(calendar, newMonth / MONTH_IN_YEAR);
        }
        date.setMonth(Month.getMountByParameter(newMonth));
        calendar.setDate(date);
        return calendar;

    }
    public Calendar addYearsToDate(Calendar calendar, int years) throws CalendarException {
        Date date = calendar.getDate();
        int newYear = (date.getYear() + years);
        if (newYear > MAX_YEAR) {
            throw new CalendarException("Calendar max year is 9999");
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
