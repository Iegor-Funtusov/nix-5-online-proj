package com.nixsolutions.courses.service;

import com.nixsolutions.courses.data.Date;
import com.nixsolutions.courses.util.CalendarUtils;

import java.time.DateTimeException;

public class DifferenceService {

    public double differenceInSeconds(Date from, Date to) {
        return differenceInMinutes(from, to) * 60 + (to.getTime().getSeconds() - from.getTime().getSeconds());
    }

    public double differenceInMinutes(Date from, Date to) {
        return differenceInHours(from, to) * 60 + (to.getTime().getMinutes() - from.getTime().getMinutes());
    }

    public double differenceInHours(Date from, Date to) {
        return differenceInDays(from, to) * 24
                + (to.getTime().getHours() - from.getTime().getHours())
                + (double)(to.getTime().getMinutes() - from.getTime().getMinutes())/60;
    }


    public double differenceInDays(Date from, Date to) throws DateTimeException {
        double result = differenceInMonths(from, to);
        if (result != 0) {
            if (result > 12) {
                result = 0;
                for (int i = from.getYear() + 1; i <= to.getYear() - 1; i++) {
                    result += 365;
                    if (CalendarUtils.isLeapYear(i)) result++;
                }
            } else {
                result = 0;
                if (from.getYear().equals(to.getYear())) {
                    for (int i = from.getMonth(); i < to.getMonth() + 1; i++) {
                        result += CalendarUtils.daysInMonth(i, from.getYear());
                    }
                    return result - from.getDay() - (CalendarUtils.daysInMonth(to.getMonth(), to.getYear()) - to.getDay());
                }
            }
            for (int i = from.getMonth(); i < 13; i++) {
                result += CalendarUtils.daysInMonth(i, from.getYear());
            }
            for (int i = to.getMonth(); i > 0; i--) {
                result += CalendarUtils.daysInMonth(i, to.getYear());
            }
            result = result - from.getDay() - (CalendarUtils.daysInMonth(to.getMonth(), to.getYear()) - to.getDay());
        }
        return result;
    }

    public double differenceInMonths(Date from, Date to) {
        return differenceInYears(from, to) * 12
                + (double) (to.getDay() - from.getDay()) / 31;
    }

    public double differenceInYears(Date from, Date to) throws DateTimeException {
        if (CalendarUtils.validDatePair(from, to))
            return to.getYear() - from.getYear()
                    + (double) (to.getMonth() - from.getMonth()) / 12;
        throw new DateTimeException("Value of to date is less than of from date");
    }
}
