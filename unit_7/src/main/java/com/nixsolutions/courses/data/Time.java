package com.nixsolutions.courses.data;

public class Time implements Comparable<Time> {

    private Integer hours;
    private Integer minutes;
    private Integer seconds;

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
