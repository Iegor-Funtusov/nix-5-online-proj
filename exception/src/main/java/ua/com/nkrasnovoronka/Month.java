package ua.com.nkrasnovoronka;

public enum Month {
    JANUARY("JAN", 31), FEBRUARY("FEB", 28), MARCH("MAR", 31),
    APRIL("APR", 30), MAY("MAY", 31), JUNE("JUN", 30),
    JULY("JUL", 31), AUGUST("AUG", 31), SEPTEMBER("SEP", 30),
    OCTOBER("OCT", 31), NOVEMBER("NOV", 30), DECEMBER("DEC", 31);
    private String shortName;
    private int numberOfDays;

    Month(String shortName, int numberOfDays) {
        this.shortName = shortName;
        this.numberOfDays = numberOfDays;
    }

    public String getShortName() {
        return shortName;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public static Month getMountByParameter(int number){
        for(Month month: Month.values()){
            if(number == month.ordinal() + 1){
                return month;
            }
        }
        return null;
    }

    public static Month getMountByParameter(String name){
        for(Month month: Month.values()){
            if(name.equalsIgnoreCase(month.getShortName()) || name.equalsIgnoreCase(month.name())){
                return month;
            }
        }
        return JANUARY;
    }

}
