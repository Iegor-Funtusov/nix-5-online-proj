package ua.com.alevel.app.entity;

public enum Month {

    JANUARY(31),
    FEBRUARY(28),
    MARCH(31),
    APRIL(30),
    MAY(31),
    JUNE(30),
    JULY(31),
    AUGUST(31),
    SEPTEMBER(30),
    OCTOBER(31),
    NOVEMBER(30),
    DECEMBER(31);

    private int numberOfDays;

    Month(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public static Month getMonthByParameter(int number){
        for(Month month: Month.values()){
            if(number == month.ordinal() + 1){
                return month;
            }
        }
        return null;
    }

    public static Month getMonthByParameter(String name){
        for(Month month: Month.values()){
            if(name.equalsIgnoreCase(month.name())){
                return month;
            }
        }
        return JANUARY;
    }

    public static void addDayIfLeapYear(boolean isLeapYear){
        if(isLeapYear){
            FEBRUARY.numberOfDays += 1;
        }
    }

}