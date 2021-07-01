package task1;

public class CalendarService {
    public static boolean checkDate(int year, int month, int day){
        return validYear(year) &&
                validMonth(month) &&
                validDay(day, month, year);
    }

    private static boolean validYear(int year) throws RuntimeException{
        return year >= 0 && year <= 10000;
    }

    private static boolean validMonth(int month){
        return month > 0 && month <= 12;
    }

    private static boolean validDay(int day, int month, int year){
        return day > 0 && day <= getMonthCount(month, year);
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
