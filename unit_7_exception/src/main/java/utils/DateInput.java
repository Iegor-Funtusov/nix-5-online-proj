package utils;

import domain.Calendar;
import exception.InvalidDateFormatException;

import java.util.Scanner;

public class DateInput {

    public static Calendar readConsole(String format) throws InvalidDateFormatException {

        Calendar date = null;
        Scanner scanner = new Scanner(System.in);
        if (format.contains(" ")) {
                date = new Calendar(scanner.next(), scanner.next(), format);
        } else {
                date = new Calendar(scanner.next(), format);
        }
        return date;
    }
}
