package ua.com.alevel.app.entity;

import ua.com.alevel.app.exception.FormatException;

import java.util.Objects;

import static ua.com.alevel.app.util.Utils.isLeapYear;

public class Date implements Comparable<Date> {

    private int day;
    private Month month;
    private int year;

    public Date(int day, Month month, int year) throws FormatException {
        this.month = month;
        Month.addDayIfLeapYear(isLeapYear(year));
        if (day <= month.getNumberOfDays()) {
            this.day = day;
        } else {
            throw new FormatException(String.format("%s can have only %d days", month.name(), month.getNumberOfDays()));
        }
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return day == date.day && month == date.month && year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    @Override
    public int compareTo(Date o) {
        int compare = Integer.compare(year, o.year);
        if (compare == 0) {
            compare = Integer.compare(month.ordinal(), o.month.ordinal());
        }
        if (compare == 0) {
            compare = Integer.compare(day, o.day);
        }
        return compare;
    }
}