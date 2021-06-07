package DatePackage;

import DateHelpers.*;
import java.util.ArrayList;
import java.util.List;

public class DateService {
    private final DateParser parser;
    private final DateDB dateDB;


    DateService(){
        parser = new DateParser();
        dateDB = new DateDB();
    }


    public Date createDate(String dateToCreate){
        Date dateToAdd = new Date();
        int []parameters = parser.parseDate(dateToCreate);
        dateToAdd.setValues(parameters);
        dateDB.add(dateToAdd);
        return dateToAdd;
    }



    public List<Date> getDate(){
        return dateDB.get();
    }


    public Date getDate(int index){
        return dateDB.get(index);
    }



    public void addSeconds(Date date, int seconds){
        isNotEmptyDate(date);
        validation(seconds);
        date = CountDate.addSeconds(date, seconds);
    }


    public void addMinutes(Date date, int minutes){
        isNotEmptyDate(date);
        validation(minutes);
        date = CountDate.addMinutes(date, minutes);
    }


    public void addHours(Date date, int hours){
        isNotEmptyDate(date);
        validation(hours);
        date = CountDate.addHours(date, hours);
    }

    public void addDays(Date date, int days){
        isNotEmptyDate(date);
        validation(days);
        date = CountDate.addDays(date, days);
    }

    public void addMonths(Date date, int months){
        isNotEmptyDate(date);
        validation(months);
        date = CountDate.addMonths(date, months);
    }

    public void addYears(Date date, int years){
        isNotEmptyDate(date);
        validation(years);
        date = CountDate.addYears(date, years);
    }



    public void subtractSeconds(Date date, int seconds){
        isNotEmptyDate(date);
        validation(seconds);
        date = CountDate.subtractSeconds(date, seconds);
    }


    public void subtractMinutes(Date date, int minutes){
        isNotEmptyDate(date);
        validation(minutes);
        date = CountDate.subtractMinutes(date, minutes);
    }


    public void subtractHours(Date date, int hours){
        isNotEmptyDate(date);
        validation(hours);
        date = CountDate.subtractHours(date, hours);
    }


    public void subtractDays(Date date, int days){
        isNotEmptyDate(date);
        validation(days);
        date = CountDate.subtractDays(date, days);
    }


    public void subtractMonths(Date date, int months){
        isNotEmptyDate(date);
        validation(months);
        date = CountDate.subtractMonths(date, months);
    }


    public void subtractYears(Date date, int years){
        isNotEmptyDate(date);
        validation(years);
        date = CountDate.subtractYears(date, years);
    }


    public int differenceSeconds(Date first, Date seconds){
        isNotEmptyDate(first);
        isNotEmptyDate(seconds);
        return DifferenceDate.findDifferenceSeconds(first, seconds);
    }


    public int differenceMinutes(Date first, Date second){
        isNotEmptyDate(first);
        isNotEmptyDate(second);
        return DifferenceDate.findDifferenceMinutes(first, second);
    }


    public int differenceHours(Date first, Date second){
        isNotEmptyDate(first);
        isNotEmptyDate(second);
        return DifferenceDate.findDifferenceHours(first, second);
    }


    public int differenceDays(Date first, Date second){
        isNotEmptyDate(first);
        isNotEmptyDate(second);
        return DifferenceDate.findDifferenceDays(first, second);
    }


    public int differenceMonths(Date first, Date second){
        isNotEmptyDate(first);
        isNotEmptyDate(second);
        return DifferenceDate.findDifferenceMonths(first, second);
    }


    public int differenceYears(Date first, Date second){
        isNotEmptyDate(first);
        isNotEmptyDate(second);
        return DifferenceDate.findDifferenceYears(first, second);
    }


    public ArrayList<Date> ascendingSort(List<Date> datesToSort) {
        return SortDate.ascendingSort(datesToSort);
    }


    public static ArrayList<Date> descendingSort(List<Date> datesToSort) {
        return SortDate.descendingSort(datesToSort);
    }


    //Служебные методы
    private void validation(int timeOrDate){
        if(timeOrDate < 0){
            throw new RuntimeException("Incorrect value entered");
        }
    }


    private void isNotEmptyDate(Date dateToCheck){
        if(dateToCheck == null){
            throw new RuntimeException("Date is not exist");
        }
    }


    public String getFormattedTime(Date date){
        return (byte) (date.getTime() / 3600) + ":" + (byte) ((date.getTime() / 60) % 60) + ":" + (byte) (date.getTime() % 60);
    }


    public MonthEnum getFormattedMonth(int month){
        validation(month);
        return MonthEnum.getFormattedMonth(month);
    }
}
