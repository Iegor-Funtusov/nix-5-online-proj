package ua.com.exceptions.entity;

public class Calendar {
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
}
