package ua.com.exceptions.services;

public class CalendarService {

    public static boolean checkDate(int year, int month, int day, int hours, int minutes, int seconds) throws RuntimeException{
            if(validYear(year) &&
                    validMonth(month) &&
                    validDay(day, month, year) &&
                    validHour(hours) &&
                    validMinute(minutes)
                    && validSeconds(seconds)
            ){
                return true;
            }
            return false;
    }

    private static boolean validYear(int year) throws RuntimeException{
        if(year >= 0 && year <=10000){
            return true;
        }
        else{
            throw new RuntimeException("Error! Year should be in a range 0-10_000");
        }
    }

    private static boolean validMonth(int month){
        if(month > 0 && month <= 12){
            return true;
        }
        else{
            throw new RuntimeException("Error! Month should be in a range 1-12");
        }
    }

    private static boolean validDay(int day, int month, int year){
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

    private static boolean  validHour(int hours){
        if(hours >=0 && hours <= 23){
            return true;
        }
        else{
            throw new RuntimeException("Error! Hour should be in a range of 0-23");
        }
    }

    private static boolean validMinute(int minutes){
        if(minutes >=0 && minutes <= 59){
            return true;
        }
        else {
            throw new RuntimeException("Error! Minutes should be in a range of 0-59");
        }
    }

    private static boolean validSeconds(int seconds){
        if(seconds >=0 && seconds <= 59){
            return true;
        }
        else {
            throw new RuntimeException("Error! Seconds should be in a range of 0-59");
        }
    }

}
