package ua.com.alevel.app.controller;

import ua.com.alevel.app.entity.Calendar;
import ua.com.alevel.app.entity.Date;
import ua.com.alevel.app.entity.Time;
import ua.com.alevel.app.exception.OutOfBoundsException;
import ua.com.alevel.app.exception.FormatException;
import ua.com.alevel.app.service.AddingService;
import ua.com.alevel.app.service.DifferenceFindingService;
import ua.com.alevel.app.service.SortingService;
import ua.com.alevel.app.service.SubtractingService;
import ua.com.alevel.app.util.FormatUtils;
import ua.com.alevel.app.util.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CalendarController {

    private final FormatUtils formatUtils = new FormatUtils();
    private final AddingService addingService = new AddingService();
    private final SubtractingService subtractingService = new SubtractingService();
    private final DifferenceFindingService differenceFindingService = new DifferenceFindingService();
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public int format = 1;

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) { this.format = format; }

    protected Calendar addSeconds(Calendar date) {
        String input;
        int num;
        System.out.println("Enter number of seconds to add:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                return addingService.addSeconds(date, num);
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException | OutOfBoundsException e) {
            System.err.println("Something went wrong, please try again");
        }
        return addSeconds(date);
    }

    protected Calendar addMinutes(Calendar date) {
        String input;
        int num;
        System.out.println("Enter number of minutes to add:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                return addingService.addMinutes(date, num);
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException | OutOfBoundsException e) {
            System.err.println("Something went wrong, please try again");
        }
        return addMinutes(date);
    }

    protected Calendar addHours(Calendar date) {
        String input;
        int num;
        System.out.println("Enter number of hours to add:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                return addingService.addHours(date, num);
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException | OutOfBoundsException e) {
            System.err.println("Something went wrong, please try again");
        }
        return addHours(date);
    }

    protected Calendar addDays(Calendar date) {
        String input;
        int num;
        System.out.println("Enter number of days to add:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                return addingService.addDays(date, num);
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException | OutOfBoundsException e) {
            System.err.println("Something went wrong, please try again");
        }
        return addDays(date);
    }

    protected Calendar addMonths(Calendar date) {
        String input;
        int num;
        System.out.println("Enter number of months to add:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                return addingService.addMonths(date, num);
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException | OutOfBoundsException e) {
            System.err.println("Something went wrong, please try again");
        }
        return addMonths(date);
    }

    protected Calendar addYears(Calendar date) {
        String input;
        int num;
        System.out.println("Enter number of years to add:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                return addingService.addYears(date, num);
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException | OutOfBoundsException e) {
            System.err.println("Something went wrong, please try again");
        }
        return addYears(date);
    }

    protected Calendar subSeconds(Calendar date) {
        String input;
        int num;
        System.out.println("Enter number of seconds to subtract:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                return subtractingService.subSeconds(date, num);
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException | OutOfBoundsException e) {
            System.err.println("Something went wrong, please try again");
        }
        return subSeconds(date);
    }

    protected Calendar subMinutes(Calendar date) {
        String input;
        int num;
        System.out.println("Enter number of minutes to subtract:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                return subtractingService.subMinutes(date, num);
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException | OutOfBoundsException e) {
            System.err.println("Something went wrong, please try again");
        }
        return subMinutes(date);
    }

    protected Calendar subHours(Calendar date) {
        String input;
        int num;
        System.out.println("Enter number of hours to subtract:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                return subtractingService.subHours(date, num);
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException | OutOfBoundsException e) {
            System.err.println("Something went wrong, please try again");
        }
        return subHours(date);
    }

    protected Calendar subDays(Calendar date) {
        String input;
        int num;
        System.out.println("Enter number of days to subtract:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                return subtractingService.subDays(date, num);
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException | OutOfBoundsException e) {
            System.err.println("Something went wrong, please try again");
        }
        return subDays(date);
    }

    protected Calendar subMonths(Calendar date) {
        String input;
        int num;
        System.out.println("Enter number of months to subtract:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                return subtractingService.subMonths(date, num);
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException | OutOfBoundsException e) {
            System.err.println("Something went wrong, please try again");
        }
        return subMonths(date);
    }

    protected Calendar subYears(Calendar date) {
        String input;
        int num;
        System.out.println("Enter number of years to subtract:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                return subtractingService.subYears(date, num);
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException | OutOfBoundsException e) {
            System.err.println("Something went wrong, please try again");
        }
        return subYears(date);
    }

    protected void findDiffSeconds(Calendar date1, Calendar date2) {
        int res = differenceFindingService.diffInSeconds(date1, date2);
        System.out.println("Difference in seconds: " + res);
    }

    protected void findDiffMinutes(Calendar date1, Calendar date2) {
        int res = differenceFindingService.diffInMinutes(date1, date2);
        System.out.println("Difference in minutes: " + res);
    }

    protected void findDiffHours(Calendar date1, Calendar date2) {
        int res = differenceFindingService.diffInHours(date1, date2);
        System.out.println("Difference in hours: " + res);
    }

    protected void findDiffDays(Calendar date1, Calendar date2) {
        int res = differenceFindingService.diffInDays(date1, date2);
        System.out.println("Difference in days: " + res);
    }

    protected void findDiffMonth(Calendar date1, Calendar date2) {
        int res = differenceFindingService.diffInMonths(date1, date2);
        System.out.println("Difference in month: " + res);
    }

    protected void findDiffYears(Calendar date1, Calendar date2) {
        int res = differenceFindingService.diffInYears(date1, date2);
        System.out.println("Difference in years: " + res);
    }

    protected List<Calendar> sortDates(int type) {
        String input;
        int num;
        List<Calendar> calendars = new ArrayList<>();
        System.out.println("Enter number of days to sort:");
        try {
            if(Utils.tryParseInt(input = bufferedReader.readLine())){
                num = Integer.parseInt(input);
                for (int i = 0; i < num; i++) {
                    calendars.add(inputDate());
                }
                if (type == 1) {
                    return new SortingService(calendars).sortASC();
                }
                if (type == 2) {
                    return new SortingService(calendars).sortDESC();
                }
            }
            else {
                System.err.println("Sorry, your input is wrong. Please try again");
            }
        } catch (IOException e) {
            System.err.println("Something went wrong, please try again");
        }
        return sortDates(1);
    }

    public Calendar inputDate() {
        System.out.println("Chosen format: " + FormatUtils.patterns[format -1]);
        String[] userInput = Utils.inputStr().split(" ");
        try {
            Date date = null;
            Time time = null;
            if (userInput.length == 2) {
                date = formatUtils.stringToDate(format, userInput[0]);
                time = formatUtils.formatStringToTime(userInput[1]);
            }
            if (userInput.length == 1) {
                date = formatUtils.stringToDate(format, userInput[0]);
                time = formatUtils.formatStringToTime("");
            }
            return new Calendar(date, time);
        } catch (FormatException e) {
            System.err.println("Something went wrong, please try again");
        }
        return inputDate();
    }
}