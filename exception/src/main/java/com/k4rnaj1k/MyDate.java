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
        this.years= 0L;
        this.months=1;
        this.days=1L;
        this.hours=0L;
        this.minutes=0L;
        this.seconds=0L;
    }

    private Long years, days, hours, minutes, seconds;
    private Integer months;

    public MyDate(){
        clean();
    }

    public Long getYears() {
        return years;
    }

    public void setYears(Long years) {
        this.years = years;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
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
