package ua.com.exceptions.entity;

import java.util.Objects;

public class Calendar implements Comparable <Calendar> {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public Calendar(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return year == calendar.year &&
                month == calendar.month &&
                day == calendar.day &&
                hour == calendar.hour &&
                minute == calendar.minute &&
                second == calendar.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day, hour, minute, second);
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                '}';
    }

    @Override
    public int compareTo(Calendar c) {
        int comp = Integer.compare(year, c.year);
        if(comp == 0){
            comp = Integer.compare(month, c.month);
            if(comp == 0){
                comp = Integer.compare(day, c.day);
                if(comp == 0){
                    comp = Integer.compare(hour, c.hour);
                    if(comp == 0){
                        comp = Integer.compare(minute, c.minute);{
                            if(comp == 0){
                                comp = Integer.compare(second, c.second);
                            }
                        }
                    }
                }
            }
        }
        return comp;
    }
}

