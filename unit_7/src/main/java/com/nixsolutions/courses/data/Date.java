package com.nixsolutions.courses.data;

import java.util.Objects;

public class Date implements Comparable<Date>{

    private Integer day;
    private Integer month;
    private Integer year;
    private Time time;

    public Date() {
        this.time = new Time();
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", " + time.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day.equals(date.day) && month.equals(date.month) && year.equals(date.year) && time.equals(date.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year, time);
    }

    @Override
    public int compareTo(Date o) {
        int result = this.getYear().compareTo(o.getYear());
        if (result == 0) {
            result = this.getMonth().compareTo(o.getMonth());
            if (result == 0) {
                result = this.getDay().compareTo(o.getDay());
                if (result == 0) {
                    result = this.getTime().compareTo(o.getTime());
                }
            }
        }
        return result;
    }
}
