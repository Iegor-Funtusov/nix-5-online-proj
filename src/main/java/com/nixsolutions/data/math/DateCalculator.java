package com.nixsolutions.data.math;

import com.nixsolutions.data.model.Date;

public class DateCalculator extends Date implements DateMath {

    @Override
    public void plusYear(int year) {

        this.year += year;
    }

    @Override
    public void plusMonths(int month) {

        int y = month / 12;
        int m = month - y * 12;
        plusYear(y);
        this.month += m;
    }

    @Override
    public void plusDay(int day) {

        int monthDay;

        for (int i = 1; i <= day; i++) {
            monthDay = 28 + (month + (month / 8)) % 2 + 2 % month + 2 * (1 / month);
            if (month == 2 && year % 4 == 0) {
                monthDay++;
            }
            this.day++;
            if (this.day > monthDay) {
                this.day = 1;
                month++;
            }
            if (month > 12) {
                month = 1;
                year++;
            }
        }
    }

    @Override
    public void plusHour(int hour) {

        int d = hour / 24;
        int h = hour - d * 24;
        plusDay(d);
        this.hour += h;
    }

    @Override
    public void plusMinute(int minute) {

        int h = minute / 60;
        int m = minute - h * 60;
        plusHour(h);
        this.minute += m;
    }

    @Override
    public void plusSecond(int second) {

        int m = second / 60;
        int s = second - m * 60;
        plusMinute(m);
        this.second += s;
    }

    @Override
    public void minusYear(int years) {

        this.year -= years;
    }

    @Override
    public void minusMonth(int month) {

        int y = month / 12;
        int m = month - y * 12;
        minusYear(y);
        this.month -= m;
    }

    @Override
    public void minusDay(int day) {

        int lastMonth;
        int monthDay;

        for (int i = 1; i <= day; i++) {
            lastMonth = month - 1;
            if (lastMonth == 0) {
                lastMonth = 12;
            }

            monthDay = 28 + (lastMonth + (lastMonth / 8)) % 2 + 2 % lastMonth + 2 * (1 / lastMonth);

            if (lastMonth == 2 && year % 4 == 0) {
                monthDay++;
            }
            this.day--;
            if (this.day < 1) {
                this.day = monthDay;
                month--;
            }
            if (month < 1) {
                month = 12;
                year--;
            }
        }
    }

    @Override
    public void minusHour(int hour) {

        int d = hour / 24;
        int h = hour - d * 24;

        minusDay(d);

        int minusHour = this.hour - h;
        if (minusHour < 0) {
            minusDay(1);
            this.hour = 24 - Math.abs(minusHour);
        } else {
            this.hour -= h;
        }
    }

    @Override
    public void minusMinute(int minute) {

        int h = minute / 60;
        int m = minute - h * 60;

        minusHour(h);

        int minusMinute = this.minute - m;

        if (minusMinute < 0) {
            minusHour(1);
            this.minute = 60 - Math.abs(minusMinute);
        } else {
            this.minute -= m;
        }
    }

    @Override
    public void minusSecond(int second) {

        int m = second / 60;
        int s = second - m * 60;

        minusMinute(m);

        int minusSecond = this.second - s;

        if (minusSecond < 0) {
            minusMinute(1);
            this.second = 60 - Math.abs(minusSecond);
        } else {
            this.second -= s;
        }
    }
}
