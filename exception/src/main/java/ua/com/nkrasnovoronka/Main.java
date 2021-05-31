package ua.com.nkrasnovoronka;

import ua.com.nkrasnovoronka.exception.CalendarException;
import ua.com.nkrasnovoronka.exception.DataFormatException;
import ua.com.nkrasnovoronka.model.Calendar;
import ua.com.nkrasnovoronka.model.Date;
import ua.com.nkrasnovoronka.model.Month;
import ua.com.nkrasnovoronka.model.Time;
import ua.com.nkrasnovoronka.service.CalendarAddingService;
import ua.com.nkrasnovoronka.service.CalendarDifferenceService;
import ua.com.nkrasnovoronka.service.CalendarSubtractService;

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
            Date date = new Date(21, Month.NOVEMBER, 1900);
            Time time = new Time(00,00,00);
            Calendar calendar = new Calendar(date, time);
            CalendarSubtractService calendarService = new CalendarSubtractService();
            Calendar calendar1 = calendarService.subtractMonthToDate(calendar, 12);
            System.out.println(calendar1.getDate());
            System.out.println(calendar1.getTime());
//            CalendarDifferenceService calendarDifferenceService = new CalendarDifferenceService();
//            int i = calendarDifferenceService.differenceInSeconds(new Calendar(new Date(28, Month.FEBRUARY, 1990), new Time(0,0,0)),
//                    new Calendar(new Date(28, Month.FEBRUARY, 1991), new Time(00,00,00)));
//            System.out.println(i);

        } catch (CalendarException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        }

//        System.out.println(36/12);
//        System.out.println(36%12);


    }
}
