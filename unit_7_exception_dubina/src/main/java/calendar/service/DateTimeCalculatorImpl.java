package calendar.service;

import calendar.entity.DateTime;

public class DateTimeCalculatorImpl extends DateTime {

    public DateTimeCalculatorImpl(int year, int month, int day, int hour, int minute, int second) {
        setYear(year);
        setMonth(month);
        setDay(day);
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    public DateTimeCalculatorImpl() {
    }

    public int changeYear(int year) {
        try {
            this.setYear(this.getYear() + year);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Problem with Year. Please use valid data", e);
        }
        return this.getYear();
    }


    public void changeMonth(int month) {
        int plusOrMinus = month > 0 ? 1 : -1;
        int remainingMonths = month % 12;
        try {
            this.setMonth(this.getMonth() + remainingMonths);
        } catch (IllegalArgumentException e) {
            this.setYear(this.getYear() + plusOrMinus * 1);
            this.setMonth((this.getMonth() + remainingMonths) - plusOrMinus * 12);
        }
    }

    public void changeDay(int day) {
        int plusOrMinus = day > 0 ? 1 : -1;

        for (int i = 0; i < Math.abs(day); i++) {
            try {
                this.setDay(this.getDay() + plusOrMinus * 1);
            } catch (IllegalArgumentException e) {
                try {
                    this.setDay(1);
                    this.setMonth(this.getMonth() + plusOrMinus * 1);
                    if (plusOrMinus < 0) {
                        this.setDay(daysInMonth(this.getMonth(), this.getYear()));
                    }
                } catch (IllegalArgumentException e2) {
                    this.setYear(this.getYear() + plusOrMinus * 1);
                    this.setMonth(plusOrMinus > 0 ? 1 : 12);
                    this.setDay(plusOrMinus > 0 ? 1 : daysInMonth(this.getMonth(), this.getYear()));
                }
            }
        }
    }

    public void changeHours(int hour) {
        int plusOrMinus = hour > 0 ? 1 : -1;

        int days = hour / 24;
        int remainingHours = hour % 24;
        changeDay(days);
        try {
            this.setHour(this.getHour() + remainingHours);
        } catch (IllegalArgumentException e) {
            changeDay(plusOrMinus * 1);
            this.setHour(this.getHour() + remainingHours - plusOrMinus * 24);
        }
    }

    public void changeMinute(int minute) {
        int plusOrMinus = minute > 0 ? 1 : -1;
        int hours = minute / 60;
        int remainingMinutes = minute % 60;
        changeHours(hours);
        try {
            this.setMinute(this.getMinute() + remainingMinutes);
        } catch (IllegalArgumentException e) {
            changeHours(plusOrMinus * 1);
            this.setMinute(this.getMinute() + remainingMinutes - plusOrMinus * 60);
        }
    }

    public void changeSecond(int second) {
        int plusOrMinus = second > 0 ? 1 : -1;

        int minutes = second / 60;
        int remainingSeconds = second % 60;
        changeMinute(minutes);
        try {
            this.setSecond(this.getSecond() + remainingSeconds);
        } catch (IllegalArgumentException e) {
            changeMinute(plusOrMinus * 1);
            this.setSecond(this.getSecond() + remainingSeconds - plusOrMinus * 60);
        }
    }

    private static boolean isEmpty(DateTimeCalculatorImpl dateTimeCalculatorImpl) {
        return dateTimeCalculatorImpl == null;
    }

    public static int diffInYears(DateTimeCalculatorImpl dateAndTime, DateTimeCalculatorImpl dateAndTime1) {
        if (isEmpty(dateAndTime) || isEmpty(dateAndTime1)) {
            throw new RuntimeException("Pay attention! This date doesn't exist.");
        }
        return Math.abs(dateAndTime.getYear() - dateAndTime1.getYear());
    }

    public static int diffInMonth(DateTimeCalculatorImpl dateAndTime, DateTimeCalculatorImpl dateAndTime1) {
        if (isEmpty(dateAndTime) || isEmpty(dateAndTime1)) {
            throw new RuntimeException("Pay attention! This date doesn't exist.");
        }
        return Math.abs(dateAndTime.getMonth() - dateAndTime1.getMonth());
    }

    public static int diffInDays(DateTimeCalculatorImpl dateAndTime, DateTimeCalculatorImpl dateAndTime1) {
        if (isEmpty(dateAndTime) || isEmpty(dateAndTime1)) {
            throw new RuntimeException("Pay attention! This date doesn't exist.");
        }
        return Math.abs(dateAndTime.getDay() - dateAndTime1.getDay());
    }

    public static int diffInHours(DateTimeCalculatorImpl dateAndTime, DateTimeCalculatorImpl dateAndTime1) {
        if (isEmpty(dateAndTime) || isEmpty(dateAndTime1)) {
            throw new RuntimeException("Pay attention! This date doesn't exist.");
        }
        int firstHours = dateAndTime.getHour();
        int secondHours = dateAndTime1.getHour();
        return Math.abs(firstHours - secondHours);
    }

    public static int diffInMinutes(DateTimeCalculatorImpl dateAndTime, DateTimeCalculatorImpl dateAndTime1) {
        if (isEmpty(dateAndTime) || isEmpty(dateAndTime1)) {
            throw new RuntimeException("Date does not exist");
        }
        int firstMinutes = dateAndTime.getMinute();
        int secondMinutes = dateAndTime1.getMinute();
        return Math.abs(firstMinutes - secondMinutes);
    }

    public static int diffInSeconds(DateTimeCalculatorImpl dateAndTime, DateTimeCalculatorImpl dateAndTime1) {
        if (isEmpty(dateAndTime) || isEmpty(dateAndTime1)) {
            throw new RuntimeException("Date does not exist");
        }
        int firstSeconds = dateAndTime.getSecond();
        int secondSeconds = dateAndTime1.getSecond();
        return Math.abs(firstSeconds - secondSeconds);
    }
}
