package com.k4rnaj1k;

public class MyDate {

    public MyDate(MyDate myDate){
        setYears(myDate.getYears());
        setMonths(myDate.getMonths());
        setDays(myDate.getDays());
        setHours(myDate.getHours());
        setMinutes(myDate.getMinutes());
        setSeconds(myDate.getSeconds());
    }

    public void clean(){
        this.years=1;
        this.months=1;
        this.days=1;
        this.hours=0;
        this.minutes=0;
        this.seconds=0;
    }

    private Integer years, months, days, hours, minutes, seconds;

    public MyDate(){
        this.years=0;
        this.months=1;
        this.days=1;
        this.hours=0;
        this.minutes=0;
        this.seconds=0;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
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
        return "{" +
                "years=" + years +
                ", months=" + months +
                ", days=" + days +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }
}
