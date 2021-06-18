package com.nixsolutions.courses.service;

import com.nixsolutions.courses.data.Date;
import com.nixsolutions.courses.util.CalendarUtils;

import java.time.DateTimeException;

public class DifferenceService {

    protected double differenceInSeconds(Date from, Date to) {
        double result = differenceInMinutes(from, to);
        if (result != 0 || (to.getTime().getSeconds() - from.getTime().getSeconds() >= 0)) {
            result = result * 60 + (to.getTime().getSeconds() - from.getTime().getSeconds());
            return result;
        } else {
            throw new DateTimeException("Value of to date is less than of from date");
        }
    }

    protected double differenceInMinutes(Date from, Date to) {
        double result = differenceInHours(from, to);
        if (result != 0 || (to.getTime().getMinutes() - from.getTime().getMinutes() >= 0)) {
            result = result * 60 + (to.getTime().getMinutes() - from.getTime().getMinutes());
            return result;
        } else {
            throw new DateTimeException("Value of to date is less than of from date");
        }
    }

    protected double differenceInHours(Date from, Date to) {
        double result = differenceInDays(from, to);
        if (result != 0 || (to.getTime().getHours() - from.getTime().getHours() >= 0)) {
            result = result * 24 + (to.getTime().getHours() - from.getTime().getHours());
            return result;
        } else {
            throw new DateTimeException("Value of to date is less than of from date");
        }
    }


    protected double differenceInDays(Date from, Date to) throws DateTimeException {
        double result = differenceInMonths(from, to);
        if (result != 0) {
            if (result > 12) {
                result = 0;
                for (int i = from.getYear() + 1; i <= to.getYear() - 1; i++) {
                    result += 365;
                    if (CalendarUtils.isLeapYear(i)) result++;
                }
                for (int i = from.getMonth(); i < 13; i++) {
                    result += CalendarUtils.daysInMonth(i, from.getYear());
                }
                for (int i = to.getMonth(); i > 0; i--) {
                    result += CalendarUtils.daysInMonth(i, from.getYear());
                }
            } else {
                result = 0;
                if (from.getYear().equals(to.getYear())) {
                    for (int i = from.getMonth(); i < to.getMonth() + 1; i++) {
                        result += CalendarUtils.daysInMonth(i, from.getYear());
                    }
                } else {
                    for(int i = from.getMonth(); i < 13; i++) {
                        result += CalendarUtils.daysInMonth(i, from.getYear());
                    }
                    for(int i = 1; i < to.getMonth() + 1; i++) {
                        result += CalendarUtils.daysInMonth(i, to.getYear());
                    }
                }
            }
            result = result - from.getDay() - (CalendarUtils.daysInMonth(to.getMonth(), to.getYear()) - to.getDay());

        }
        return result;
    }

    protected double differenceInMonths(Date from, Date to) {
        double result = differenceInYears(from, to);
        if (result != 0 || (to.getMonth() - from.getMonth() >= 0)) {
            result = result * 12 + (double) (to.getDay() - from.getDay()) / 31;
            return result;
        } else {
            throw new DateTimeException("Value of to date is less than of from date");
        }
    }

    protected double differenceInYears(Date from, Date to) {
        double result = to.getYear() - from.getYear();
        if (result >= 0) {
            result = result + (double) (to.getMonth() - from.getMonth()) / 12;
            return result;
        } else {
            throw new DateTimeException("Value of to date is less than of from date");
        }
    }
}
