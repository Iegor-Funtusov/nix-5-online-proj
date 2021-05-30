package ua.com.nkrasnovoronka;

import ua.com.nkrasnovoronka.exception.CalendarException;
import ua.com.nkrasnovoronka.exception.DataFormatException;
import ua.com.nkrasnovoronka.exception.TimeFormatException;
import ua.com.nkrasnovoronka.formatter.DateFormatter;
import ua.com.nkrasnovoronka.formatter.TimeFormatter;
import ua.com.nkrasnovoronka.model.Calendar;
import ua.com.nkrasnovoronka.model.Date;
import ua.com.nkrasnovoronka.model.Month;
import ua.com.nkrasnovoronka.model.Time;
import ua.com.nkrasnovoronka.service.CalendarService;
import ua.com.nkrasnovoronka.util.UserInput;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Pleas chose date format\n1 - dd/mm/yy\n2 - m/d/yyyy\n3 - mmm-d-yy\n4 - dd-mmmm-yyyy");
//        int dateFormat = UserInput.userInputNumber();
//        System.out.println("Plea enter date");
//        String inputString = UserInput.userInputString();
////        System.out.println("Plea enter time");
////        String inputString = UserInput.userInputString();
//
//        DateFormatter dateFormatter = new DateFormatter();
//        Date date;
//        try {
//            date = dateFormatter.formatStringToDate(dateFormat, inputString);
//            int dd = UserInput.userInputNumber();
//            System.out.println(dateFormatter.formatDateToString(dd, date));
//        } catch (DataFormatException e) {
//            e.printStackTrace();
//        }
//        TimeFormatter timeFormatter = new TimeFormatter();
//        try {
//            Time time = timeFormatter.formatStringToTime(inputString);
//            System.out.println(timeFormatter.formatTimeToString(time));
//        } catch (TimeFormatException e) {
//            e.printStackTrace();
//        }

        try {
            Date date = new Date(21, Month.NOVEMBER, 1245);
            Time time = new Time(00,00,00);
            Calendar calendar = new Calendar(date, time);
            CalendarService calendarService = new CalendarService();
            Calendar calendar1 = calendarService.addSecondsToDate(calendar, 24 * 60 * 60 * 365);
            System.out.println(calendar1.getDate());
            System.out.println(calendar1.getTime());

        } catch (CalendarException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        }

//        System.out.println(36/12);
//        System.out.println(36%12);


    }
}
