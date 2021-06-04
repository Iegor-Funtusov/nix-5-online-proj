package ua.com.exceptions.services;

import ua.com.exceptions.entity.Date;

public class DateService{

    public Date checkDate(int year, int month, int day){
        try{
            if(validYear(year) && validMonth(month) && validDay(day, month, year)){
                return new Date(year, month, day);
            }
        }
        catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private boolean validYear(int year){
        if(year > 0 && year <=10000){
            return true;
        }
        else{
            throw new RuntimeException("Error! Year should be in a range 1-10_000");
        }
    }

    private boolean validMonth(int month){
        if(month > 0 && month <= 12){
            return true;
        }
        else{
            throw new RuntimeException("Error! Month should be in a range 1-12");
        }
    }

    private boolean validDay(int day, int month, int year){
        if(day > 0 && day <= getMonthCount(month, year)){
            return true;
        }
        else {
            throw new RuntimeException("Error! Day should be in a range 1-(28/29/30/31) depends on month!");
        }
    }

    public static int getMonthCount(int month, int year){
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8
    || month == 10 || month == 12) {
            return 31;
        }
        if(month == 2) {
            return 28 + leapYearCheck(year);
        }
        else {
            return 30;
        }
    }

    public static int leapYearCheck(int year){
        if(year%4 == 0 || (year%100 == 0 && year%400 == 0)){
            return 1;
        }
        else {
            return 0;
        }
    }
}
