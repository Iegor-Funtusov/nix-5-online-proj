package domain;

public class Date {
    private int date;
    private int month;
    private int year;
    public long time;
    private boolean normalized;

    public boolean isNormalized() {
        return normalized;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Date() {
    }

    public Date(int date, int month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

  @Override
    public String toString() {
        return "Date{" +
            "date=" + date +
            ", month=" + month +
            ", year=" + year +
            ", time=" + time +
          //  ", normalized=" + normalized +
            '}';
    }
}
