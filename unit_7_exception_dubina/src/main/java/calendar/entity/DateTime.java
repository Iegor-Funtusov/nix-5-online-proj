package calendar.entity;

import java.util.stream.IntStream;

public class DateTime implements Comparable<DateTime> {

    private int year;
    private int month;
    private int day;

    private int hour;
    private int minute;
    private int second;

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setYear(int year) {
        if (year < 0) {
            throw new IllegalArgumentException("Please input valid year!");
        }
        this.year = year;
    }

    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Please input valid month: from 1 to 12.");
        }
        this.month = month;
    }

    public void setDay(int day) {
        if (day <= 0) {
            throw new IllegalArgumentException("Please input valid day: from 1 to 31 (28,29,30).");
        }
        if (day > daysInMonth(getMonth(), getYear()) || !isLeapYear(year) && getMonth() == 2 && getDay() == 29) {
            throw new IllegalArgumentException("This day doesn't exist in current month.");
        }
        this.day = day;
    }

    public void setHour(int hour) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Please input valid hour: from 0 to 23.");
        }
        this.hour = hour;
    }

    public void setMinute(int minute) {
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Please input valid minute: from 0 to 59.");
        }
        this.minute = minute;
    }

    public void setSecond(int second) {
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("Please input valid hour: from 0 to 59.");
        }
        this.second = second;
    }

    public int daysInMonth(int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Please input valid month: from 1 to 12.");
        }
        if (IntStream.of(4, 6, 9, 11).anyMatch(m -> m == month)) {
            return 30;
        }
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }
        return 31;
    }

    public boolean isLeapYear(int year) {
        if (year < 0) {
            throw new IllegalArgumentException("Please input valid year! Try to create positive year.");
        }
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    @Override
    public int compareTo(DateTime dateTime) {
        int compareResult = Integer.compare(this.year, dateTime.getYear());

        if (compareResult == 0) {

            compareResult = Integer.compare(this.month, dateTime.getMonth());

            if (compareResult == 0) {

                compareResult = Integer.compare(this.day, dateTime.getDay());

                if (compareResult == 0) {

                    compareResult = Integer.compare(this.hour, dateTime.getHour());

                    if (compareResult == 0) {

                        compareResult = Integer.compare(this.minute, dateTime.getMinute());

                        if (compareResult == 0) {

                            compareResult = Integer.compare(this.second, dateTime.getSecond());
                        }
                    }
                }
            }
        }
        return compareResult;
    }

    @Override
    public boolean equals(Object dateTime) {
        if (this == dateTime) {
            return true;
        }
        if (dateTime == null || getClass() != dateTime.getClass()) {
            return false;
        }
        DateTime that = (DateTime) dateTime;
        boolean result = year == that.year &&
                month == that.month &&
                day == that.day &&
                hour == that.hour &&
                minute == that.minute &&
                second == that.second;
        return result;
    }

    @Override
    public String toString() {
        return "Date And Time: " + year +
                "/" + day +
                "/" + month +
                " " + hour +
                ":" + minute +
                ":" + second;
    }
}
