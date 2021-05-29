package DatePackage;

import DateHelpers.CountDate;
import DateHelpers.DateParser;

public class DateService {
    private Date date;
    private DateParser parser;

    DateService(){
        parser = new DateParser();
    }

    public void createDate(String dateToCreate){
        date = new Date();
        int []parameters = parser.parseDate(dateToCreate);
        date.setValues(parameters);
    }


    public Date getDate(){
        try {
            isNotEmpty();
            return date;
        } catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void addSeconds(int seconds){
        isNotEmpty();
        validation(seconds);
        date = CountDate.addSeconds(date, seconds);
    }

    public void addMinutes(int minutes){
        isNotEmpty();
        validation(minutes);
        date = CountDate.addMinutes(date, minutes);
    }


    public void addHours(int hours){
        isNotEmpty();
        validation(hours);
        date = CountDate.addHours(date, hours);
    }

    public void addDays(int days){
        isNotEmpty();
        validation(days);
        date = CountDate.addDays(date, days);
    }

    public void addMonths(int months){
        isNotEmpty();
        validation(months);
        date = CountDate.addMonths(date, months);
    }

    public void addYears(int years){
        isNotEmpty();
        validation(years);
        date = CountDate.addYears(date, years);
    }


    private void validation(int timeOrDate){
        if(timeOrDate < 0){
            throw new RuntimeException("Incorrect value entered");
        }
    }

    private void isNotEmpty(){
        if(date == null){
            throw new RuntimeException("Date is not exist");
        }
    }
}
