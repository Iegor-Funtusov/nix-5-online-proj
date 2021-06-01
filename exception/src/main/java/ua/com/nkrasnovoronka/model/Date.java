package ua.com.nkrasnovoronka.model;

import ua.com.nkrasnovoronka.exception.DataFormatException;

import java.util.Objects;

import static ua.com.nkrasnovoronka.util.Constants.isLeapYear;

public class Date implements Comparable<Date> {
    private Integer day;
    private Month month;
    private Integer year;

    public Date(int day, Month month, int year) throws DataFormatException {
        this.month = month;
        Month.addDayIfLeapYear(isLeapYear(year));
        if (day <= month.getNumberOfDays()) {
            this.day = day;
        } else {
            throw new DataFormatException(String.format("%s can have only %d days", month.name(), month.getNumberOfDays()));
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
        return Objects.equals(day, date.day) && month == date.month && Objects.equals(year, date.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    @Override
    public int compareTo(Date o) {
        int compare = year.compareTo(o.year);
        if (compare == 0) {
            compare = Integer.compare(month.ordinal(), o.month.ordinal());
        }
        if (compare == 0) {
            compare = day.compareTo(o.day);
        }
        return compare;
    }
}
