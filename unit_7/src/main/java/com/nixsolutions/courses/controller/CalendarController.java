package com.nixsolutions.courses.controller;

import com.nixsolutions.courses.data.Date;
import com.nixsolutions.courses.service.CalendarService;
import com.nixsolutions.courses.util.DateInputFormatter;
import com.nixsolutions.courses.util.DateOutputFormatter;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.util.List;
import java.util.zip.DataFormatException;

public class CalendarController {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final CalendarService calendarService = new CalendarService();
    private static final String FORMAT_REGEX = "[1-4]";
    private static final String ORDER_REGEX = "[1-2]";

    public void findDifference() {
        while (true) {
            try {
                String format = printFormats();
                System.out.println("Enter from date:");
                Date from = DateInputFormatter.formatDate(reader.readLine(), format);
                System.out.println("Enter to date:");
                Date to = DateInputFormatter.formatDate(reader.readLine(), format);
                String scope = printScopes();
                double result = calendarService.findDifference(from, to, scope);
                String str = String.format("%.1f ", result);
                System.out.println(str + scope);
                break;
            } catch (DataFormatException | DateTimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Sorry, something went wrong with reading from console. Try again");
            }
        }
    }

    public void addToDate() {
        while (true) {
            try {
                String format = printFormats();
                System.out.println("Enter date:");
                Date date = DateInputFormatter.formatDate(reader.readLine(), format);
                String scope = printScopes();
                System.out.println("Enter value:");
                String value = reader.readLine();
                if (StringUtils.isEmpty(value)) throw new IOException();
                date = calendarService.addToDate(date, Integer.parseInt(value), scope);

                String output = DateOutputFormatter.formatDate(date, printFormats());
                System.out.println(output);
                break;
            } catch (IOException e) {
                System.out.println("Sorry, something went wrong. Try again");
            } catch (DataFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void subtractFromDate() {
        while (true) {
            try {
                String format = printFormats();
                System.out.println("Enter date:");
                Date date = DateInputFormatter.formatDate(reader.readLine(), format);
                String scope = printScopes();
                System.out.println("Enter value:");
                String value = reader.readLine();
                if (StringUtils.isEmpty(value)) throw new IOException();
                date = calendarService.subtractFromDate(date, Integer.parseInt(value), scope);

                String output = DateOutputFormatter.formatDate(date, printFormats());
                System.out.println(output);
                break;
            } catch (IOException e) {
                System.out.println("Sorry, something went wrong. Try again");
            } catch (DataFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void compareDates() {
        while (true) {
            try {
                String format = printFormats();
                System.out.println("Enter comma-separated dates:");
                List<Date> dates = DateInputFormatter.formatDateList(reader.readLine(), format);
                dates = calendarService.compareDates(dates, printOrder());

                System.out.println(DateOutputFormatter.formatDateList(dates, printFormats()));
                break;
            } catch (IOException e) {
                System.out.println("Sorry, something went wrong. Try again");
            } catch (DataFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String printOrder() throws IOException {
        String input;
        do {
            System.out.println("Choose order:\n" +
                    "1 - ascending\n" +
                    "2 - descending");
        } while (!(input = reader.readLine()).matches(ORDER_REGEX));
        return input;
    }

    private String printScopes() throws IOException {
        while (true) {
            System.out.println("Choose scope:\n" +
                    "1 - seconds\n" +
                    "2 - minutes\n" +
                    "3 - hours\n" +
                    "4 - days\n" +
                    "5 - months\n" +
                    "6 - years");
            switch (reader.readLine()) {
                case "1":
                    return "seconds";
                case "2":
                    return "minutes";
                case "3":
                    return "hours";
                case "4":
                    return "days";
                case "5":
                    return "months";
                case "6":
                    return "years";
                default:
                    System.out.println("Wrong option. Try again");
            }
        }
    }

    private String printFormats() throws IOException {
        String input;
        do {
            System.out.println("Choose input-output format:\n" +
                    "1 - dd/mm/yy hh:mm:ss\n" +
                    "2 - m/d/yyyy hh:mm:ss\n" +
                    "3 - mmm-d-yy hh:mm:ss\n" +
                    "4 - dd-mmm-yyyy hh:mm:ss");
        } while (!(input = reader.readLine()).matches(FORMAT_REGEX));
        return input;
    }
}
