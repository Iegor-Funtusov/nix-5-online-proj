package ua.com.nkrasnovoronka.model;

import ua.com.nkrasnovoronka.formatter.DateFormatter;
import ua.com.nkrasnovoronka.formatter.TimeFormatter;

import java.util.Objects;

public class Calendar implements Comparable<Calendar> {
    private Date date;
    private Time time;

    public Calendar(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "date=" + date +
                ", time=" + time +
                '}';
    }

    public void printCalendar(int dateFormat){
        String date = new DateFormatter().formatDateToString(dateFormat, this.date);
        String time = new TimeFormatter().formatTimeToString(this.time);
        System.out.println(date + " " + time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Calendar)) return false;
        Calendar calendar = (Calendar) o;
        return Objects.equals(date, calendar.date) && Objects.equals(time, calendar.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time);
    }

    @Override
    public int compareTo(Calendar o) {
        int compare = date.compareTo(o.date);
        if (compare == 0) {
            compare = time.compareTo(o.time);
        }
        return compare;
    }
}
