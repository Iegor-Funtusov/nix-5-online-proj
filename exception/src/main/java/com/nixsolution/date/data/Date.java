package com.nixsolution.date.data;

public class Date {
    protected int hour;
    protected int minute;
    protected int second;
    protected int year;
    protected int month;
    protected int day;

    public void setHour(int hour) {
        if (hour > 24) {
            throw new IllegalArgumentException("Hours can't be more then 24");
        }
        this.hour = hour;
    }

    public void setMinute(int minute) {
        if (minute > 59) {
            throw new IllegalArgumentException("Minute can't be more then 59");
        }
        this.minute = minute;
    }

    public void setYear(int year) {
        if (year < 100) {
            this.year = 1900 + year;
        } else {
            this.year = year;
        }
    }

    public void setMonth(int month) {
        if (month > 12) {
            throw new IllegalArgumentException("Month can't be more then 12");
        }
        this.month = month;
    }

    public void setDay(int day) {
        if (day > 31) {
            throw new IllegalArgumentException("Day can't be more then 31");
        }
        this.day = day;
    }


    public int yearDifference(Date date) {
        return this.year - date.year;
    }

    public int monthDifference(Date date) {
        return getDateInMonths() - date.getDateInMonths();
    }

    public int dayDifference(Date date) {
        return getDateInDays() - date.getDateInDays();
    }

    public long hourDifference(Date date) {
        return getDateInHours() - date.getDateInHours();
    }

    public long minuteDifference(Date date) {
        return getDateInMinutes() - date.getDateInMinutes();
    }

    public long secondDifference(Date date) {
        return getDateInSeconds() - date.getDateInSeconds();
    }

    public int getDateInMonths() {
        return year * 12 + month;
    }

    public int getDateInDays() {
        int y = year;
        int m = month;
        if (month < 3) {
            y--;
            m += 12;
        }
        return 365 * y + y / 4 - y / 100 + y / 400 + (153 * m - 457) / 5 + day - 306;
    }

    public long getDateInHours() {
        return getDateInDays() * 24L;
    }

    public long getDateInMinutes() {
        return getDateInHours() * 60L;
    }

    public long getDateInSeconds() {
        return getDateInMinutes() * 60L;
    }

    @Override
    public String toString() {
        return "Date/Time: " +
                day +
                "/" + month +
                "/" + year +
                " " + hour +
                ":" + minute;
    }
}
