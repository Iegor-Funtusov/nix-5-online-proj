package ua.com.nkrasnovoronka;

import ua.com.nkrasnovoronka.exception.DataFormatException;
import ua.com.nkrasnovoronka.model.Calendar;
import ua.com.nkrasnovoronka.model.Date;
import ua.com.nkrasnovoronka.model.Month;
import ua.com.nkrasnovoronka.model.Time;
import ua.com.nkrasnovoronka.service.CalendarSortingService;

import java.util.List;

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
            Date date1 = new Date(21, Month.NOVEMBER, 1900);
            Time time1 = new Time(2,02,00);
            Date date2 = new Date(21, Month.NOVEMBER, 1900);
            Time time2 = new Time(2,02,01);
            Calendar calendar1 = new Calendar(date1, time1);
            Calendar calendar2 = new Calendar(date2, time2);

            CalendarSortingService calendarSortingService = new CalendarSortingService(List.of(calendar1, calendar2));
            System.out.println(calendarSortingService.sortASC());


        } catch (DataFormatException e) {
            e.printStackTrace();
        }

//        System.out.println(36/12);
//        System.out.println(36%12);


    }
}
