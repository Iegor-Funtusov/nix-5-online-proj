package ua.com.nkrasnovoronka;

import ua.com.nkrasnovoronka.exception.DataFormatException;
import ua.com.nkrasnovoronka.exception.TimeFormatException;
import ua.com.nkrasnovoronka.formatter.DateFormatter;
import ua.com.nkrasnovoronka.formatter.TimeFormatter;
import ua.com.nkrasnovoronka.model.Date;
import ua.com.nkrasnovoronka.model.Time;
import ua.com.nkrasnovoronka.util.UserInput;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Pleas chose date format\n1 - dd/mm/yy\n2 - m/d/yyyy\n3 - mmm-d-yy\n4 - dd-mmmm-yyyy");
//        int dateFormat = UserInput.userInputNumber();
//        System.out.println("Plea enter date");
//        String inputString = UserInput.userInputString();
        System.out.println("Plea enter time");
        String inputString = UserInput.userInputString();
//
//        DateFormatter dateFormatter = new DateFormatter();
//        Date date;
//        try {
//            date = dateFormatter.formatStringToDate(dateFormat, inputString);
//            System.out.println(date);
//        } catch (DataFormatException e) {
//            e.printStackTrace();
//        }
        TimeFormatter timeFormatter = new TimeFormatter();
        try {
            Time time = timeFormatter.formatStringToTime(inputString);
            System.out.println(time);
        } catch (TimeFormatException e) {
            e.printStackTrace();
        }


    }
}
