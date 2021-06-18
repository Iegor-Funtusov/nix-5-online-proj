package com.nixsolutions.courses.service;

import com.nixsolutions.courses.data.Date;
import com.nixsolutions.courses.util.CalendarUtils;

public class AddService {

    public Date addSeconds(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int hours = date.getTime().getHours();
        int minutes = date.getTime().getMinutes();
        int seconds = date.getTime().getSeconds();
        for (int i = seconds; i <= 60; i++) {
            if (value > 0) {
                if (i == 60) {
                    i = 0;
                    if (++minutes == 60) {
                        minutes = 0;
                        if (++hours == 24) {
                            hours = 0;
                            if (++day > CalendarUtils.daysInMonth(month, year)) {
                                day = 1;
                                if (++month > 12) {
                                    month = 1;
                                    year++;
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

    public Date addMinutes(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int hours = date.getTime().getHours();
        int minutes = date.getTime().getMinutes();
        for (int i = minutes; i <= 60; i++) {
            if (value > 0) {
                if (i == 60) {
                    i = 0;
                    if (++hours == 24) {
                        hours = 0;
                        if (++day > CalendarUtils.daysInMonth(month, year)) {
                            day = 1;
                            if (++month > 12) {
                                month = 1;
                                year++;
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

    public Date addHours(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int hours = date.getTime().getHours();
        for (int i = hours; i <= 24; i++) {
            if (value > 0) {
                if (i == 24) {
                    i = 0;
                    if (++day > CalendarUtils.daysInMonth(month, year)) {
                        day = 1;
                        if (++month > 12) {
                            month = 1;
                            year++;
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

    public Date addDays(Date date, int value) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        for (int i = day; i <= CalendarUtils.daysInMonth(month, year); i++) {
            if (value > 0) {
                if (i == CalendarUtils.daysInMonth(month, year)) {
                    i = 0;
                    if (++month > 12) {
                        month = 1;
                        year++;
                    }
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

    public Date addMonths(Date date, int value) {
        int month = date.getMonth();
        int year = date.getYear();
        while (value > 0) {
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
            value--;
        }
        date.setMonth(month);
        date.setYear(year);
        return date;
    }

    public Date addYears(Date date, int value) {
        date.setYear(date.getYear() + value);
        return date;
    }
}
