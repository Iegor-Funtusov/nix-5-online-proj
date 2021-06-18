package com.nixsolutions.courses.service;

import com.nixsolutions.courses.data.Date;
import com.nixsolutions.courses.util.CalendarUtils;

import java.time.DateTimeException;

public class SubtractService {

    public Date subtractSeconds(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int hours = date.getTime().getHours();
        int minutes = date.getTime().getMinutes();
        int seconds = date.getTime().getSeconds();
        for (int i = seconds; i >= 0; i--) {
            if (value > 0) {
                if (i == 0) {
                    i = 60;
                    if (--minutes < 0) {
                        minutes = 60 + minutes;
                        if (--hours < 0) {
                            hours = 24 + hours;
                            if (--day == 0) {
                                day = CalendarUtils.daysInMonth(--month, year);
                                if (month == 0) {
                                    month = 12;
                                    year--;
                                }
                            }
                        }
                    }
                }
                value--;
            } else {
                seconds = i;
                break;
            }
        }
        date.getTime().setSeconds(seconds);
        date.getTime().setMinutes(minutes);
        date.getTime().setHours(hours);
        date.setDay(day);
        date.setMonth(month);
        date.setYear(year);

        return date;
    }

    public Date subtractMinutes(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int hours = date.getTime().getHours();
        int minutes = date.getTime().getMinutes();
        for (int i = minutes; i >= 0; i--) {
            if (value > 0) {
                if (i == 0) {
                    i = 60;
                    if (--hours < 1) {
                        hours = 24 + hours;
                        if (--day == 0) {
                            day = CalendarUtils.daysInMonth(--month, year);
                            if (month == 0) {
                                month = 12;
                                year--;
                            }
                        }
                    }
                }
                value--;
            } else {
                minutes = i;
                break;
            }
        }
        date.getTime().setMinutes(minutes);
        date.getTime().setHours(hours);
        date.setDay(day);
        date.setMonth(month);
        date.setYear(year);
        return date;
    }

    public Date subtractHours(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int hours = date.getTime().getHours();
        for (int i = hours; i >= 0; i--) {
            if (value > 0) {
                if (i == 0) {
                    i = 24;
                    if (--day == 0) {
                        day = CalendarUtils.daysInMonth(--month, year);
                        if (month == 0) {
                            month = 12;
                            year--;
                        }
                    }
                }
                value--;
            } else {
                hours = i;
                break;
            }
        }
        date.getTime().setHours(hours);
        date.setDay(day);
        date.setMonth(month);
        date.setYear(year);
        return date;
    }

    public Date subtractDays(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        for (int i = day; i >= 0; i--) {
            if (value > 0) {
                if (i == 0) {
                    if (--month == 0) {
                        month = 12;
                        year--;
                    }
                    i = CalendarUtils.daysInMonth(month, year);
                }
                value--;
            } else {
                day = i;
                break;
            }
        }
        date.setDay(day);
        date.setMonth(month);
        date.setYear(year);
        return date;
    }

    public Date subtractMonths(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        while (value > 0) {
            month--;
            if (month < 1) {
                month = 12;
                year--;
            }
            value--;
        }
        date.setMonth(month);
        date.setYear(year);
        return date;
    }

    public Date subtractYears(Date date, int value) {
        if (value <= date.getYear()) {
            date.setYear(date.getYear() - value);
            return date;
        }
        throw new DateTimeException("Entered value is bigger than date");
    }
}
