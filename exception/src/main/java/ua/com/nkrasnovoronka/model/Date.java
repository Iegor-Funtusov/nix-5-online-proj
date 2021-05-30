package ua.com.nkrasnovoronka.model;

import ua.com.nkrasnovoronka.exception.DataFormatException;

import static ua.com.nkrasnovoronka.util.Constants.isLeapYear;

public class Date {
    private int day;
    private Month month;
    private int year;

    public Date(int day, Month month, int year) throws DataFormatException {
        this.month = month;
        Month.addDayIfLeapYear(isLeapYear(year));
        if(day <= month.getNumberOfDays()){
            this.day = day;
        }else {
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
}
