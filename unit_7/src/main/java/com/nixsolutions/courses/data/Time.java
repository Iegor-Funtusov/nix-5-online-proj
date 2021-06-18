package com.nixsolutions.courses.data;

import java.util.Objects;

public class Time implements Comparable<Time> {

    private Integer hours;
    private Integer minutes;
    private Integer seconds;

    public Time() {
        hours = 0;
        minutes = 0;
        seconds = 0;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "Time{" +
                "hours=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hours.equals(time.hours) && minutes.equals(time.minutes) && seconds.equals(time.seconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes, seconds);
    }

    @Override
    public int compareTo(Time o) {
        int result = this.getHours().compareTo(o.getHours());
        if (result == 0) {
            result = this.getMinutes().compareTo(o.getMinutes());
            if (result == 0) {
                result = this.getSeconds().compareTo(o.getSeconds());
            }
        }
        return result;
    }
}
