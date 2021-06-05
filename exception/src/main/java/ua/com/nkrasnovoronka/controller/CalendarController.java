package ua.com.nkrasnovoronka.controller;

import ua.com.nkrasnovoronka.exception.CalendarException;
import ua.com.nkrasnovoronka.exception.DataFormatException;
import ua.com.nkrasnovoronka.exception.TimeFormatException;
import ua.com.nkrasnovoronka.formatter.DateFormatter;
import ua.com.nkrasnovoronka.formatter.TimeFormatter;
import ua.com.nkrasnovoronka.model.Calendar;
import ua.com.nkrasnovoronka.model.Date;
import ua.com.nkrasnovoronka.model.Time;
import ua.com.nkrasnovoronka.service.CalendarAddingService;
import ua.com.nkrasnovoronka.service.CalendarDifferenceService;
import ua.com.nkrasnovoronka.service.CalendarSortingService;
import ua.com.nkrasnovoronka.service.CalendarSubtractService;
import ua.com.nkrasnovoronka.util.Constants;
import ua.com.nkrasnovoronka.util.UserInput;

import java.util.ArrayList;
import java.util.List;

public class CalendarController {
    private DateFormatter dateFormatter = new DateFormatter();
    private TimeFormatter timeFormatter = new TimeFormatter();
    private CalendarAddingService calendarAddingService = new CalendarAddingService();
    private CalendarSubtractService calendarSubtractService = new CalendarSubtractService();
    private CalendarDifferenceService calendarDifferenceService = new CalendarDifferenceService();

    private int parserFormat = 1;

    public void chooseParserFormat() {
        parserFormat = UserInput.userInputNumber("Pleas chose date format\n1 - dd/mm/yy\n2 - m/d/yyyy\n3 - mmm-d-yy\n4 - dd-mmmm-yyyy");
    }

    public int getParserFormat() {
        return parserFormat;
    }

    public Calendar enterDate() {
        System.out.println("Attention!!! Date format is " + Constants.datePatterns.get(parserFormat - 1));
        String[] userInput = UserInput.userInputString().split(" ");
        try {
            Date date = null;
            Time time = null;
            if (userInput.length == 2) {
                date = dateFormatter.formatStringToDate(parserFormat, userInput[0]);
                time = timeFormatter.formatStringToTime(userInput[1]);
            }
            if (userInput.length == 1) {
                date = dateFormatter.formatStringToDate(parserFormat, userInput[0]);
                time = timeFormatter.formatStringToTime("");
            }
            return new Calendar(date, time);
        } catch (DataFormatException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        } catch (TimeFormatException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return enterDate();
    }

    public Calendar addingService(Calendar calendar) {
        int typeAdding = UserInput.userInputNumber("Pleas chose number to add\n1 - seconds\n2 - minutes\n3 - hours\n4 - days\n5 - month\n6 - years\n0 - change date format");
        switch (typeAdding) {
            case 1 -> {
                return addingSeconds(calendar);
            }
            case 2 -> {
                return addingMinutes(calendar);
            }
            case 3 -> {
                return addingHours(calendar);
            }
            case 4 -> {
                return addingDays(calendar);
            }
            case 5 -> {
                return addingMonth(calendar);
            }
            case 6 -> {
                return addingYears(calendar);
            }
            case 0 -> {
                chooseParserFormat();
                return addingService(calendar);
            }
            default -> {
                System.err.println("Wrong input!!! Pleas try again");
                return addingService(calendar);
            }
        }
    }

    public Calendar subtractService(Calendar calendar) {
        int typeSubtract = UserInput.userInputNumber("Pleas chose number to subtract\n1 - seconds\n2 - minutes\n3 - hours\n4 - days\n5 - month\n6 - years\n0 - change date format");
        switch (typeSubtract) {
            case 1 -> {
                return subtractingSeconds(calendar);
            }
            case 2 -> {
                return subtractingMinutes(calendar);
            }
            case 3 -> {
                return subtractingHours(calendar);
            }
            case 4 -> {
                return subtractingDays(calendar);
            }
            case 5 -> {
                return subtractingMonth(calendar);
            }
            case 6 -> {
                return subtractingYears(calendar);
            }
            case 0 -> {
                chooseParserFormat();
                return subtractService(calendar);
            }
            default -> {
                System.err.println("Wrong input!!! Pleas try again");
                return subtractService(calendar);
            }
        }
    }

    public void differenceService(Calendar calendar1, Calendar calendar2) {
        int typeSubtract = UserInput.userInputNumber("Pleas chose difference in\n1 - seconds\n2 - minutes\n3 - hours\n4 - days\n5 - month\n6 - years\n0 - change date format");
        switch (typeSubtract) {
            case 1 -> differenceSeconds(calendar1, calendar2);
            case 2 -> differenceMinutes(calendar1, calendar2);
            case 3 -> differenceHours(calendar1, calendar2);
            case 4 -> differenceDays(calendar1, calendar2);
            case 5 -> differenceMonth(calendar1, calendar2);
            case 6 -> differenceYears(calendar1, calendar2);
            case 0 -> {
                chooseParserFormat();
                differenceService(calendar1, calendar2);
            }
            default -> {
                System.err.println("Wrong input!!! Pleas try again");
                differenceService(calendar1, calendar2);
            }
        }
    }

    private void differenceYears(Calendar calendar1, Calendar calendar2) {
        int i = calendarDifferenceService.differenceInYear(calendar1, calendar2);
        System.out.println("Difference in years is " + i);
    }

    private void differenceMonth(Calendar calendar1, Calendar calendar2) {
        int i = calendarDifferenceService.differenceInMonth(calendar1, calendar2);
        System.out.println("Difference in month is " + i);
    }

    private void differenceDays(Calendar calendar1, Calendar calendar2) {
        int i = calendarDifferenceService.differenceInDays(calendar1, calendar2);
        System.out.println("Difference in days is " + i);
    }

    private void differenceHours(Calendar calendar1, Calendar calendar2) {
        int i = calendarDifferenceService.differenceInHours(calendar1, calendar2);
        System.out.println("Difference in hours is " + i);
    }

    private void differenceMinutes(Calendar calendar1, Calendar calendar2) {
        int i = calendarDifferenceService.differenceInMinutes(calendar1, calendar2);
        System.out.println("Difference in minutes is " + i);
    }

    private void differenceSeconds(Calendar calendar1, Calendar calendar2) {
        int i = calendarDifferenceService.differenceInSeconds(calendar1, calendar2);
        System.out.println("Difference in seconds is " + i);
    }

    private Calendar subtractingYears(Calendar calendar) {
        int value = UserInput.userInputNumber("Pleas enter years to subtract");
        try {
            return calendarSubtractService.subtractYearsToDate(calendar, value);
        } catch (CalendarException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return subtractingYears(calendar);
    }

    private Calendar subtractingMonth(Calendar calendar) {
        int value = UserInput.userInputNumber("Pleas enter month to subtract");
        try {
            return calendarSubtractService.subtractMonthToDate(calendar, value);
        } catch (CalendarException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return subtractingMonth(calendar);
    }

    private Calendar subtractingDays(Calendar calendar) {
        int value = UserInput.userInputNumber("Pleas enter days to subtract");
        try {
            return calendarSubtractService.subtractDaysToDate(calendar, value);
        } catch (CalendarException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return subtractingDays(calendar);
    }

    private Calendar subtractingHours(Calendar calendar) {
        int value = UserInput.userInputNumber("Pleas enter hours to subtract");
        try {
            return calendarSubtractService.subtractHoursToDate(calendar, value);
        } catch (CalendarException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return subtractingHours(calendar);
    }

    private Calendar subtractingMinutes(Calendar calendar) {
        int value = UserInput.userInputNumber("Pleas enter minutes to subtract");
        try {
            return calendarSubtractService.subtractMinutesToDate(calendar, value);
        } catch (CalendarException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return subtractingMinutes(calendar);
    }

    private Calendar subtractingSeconds(Calendar calendar) {
        int value = UserInput.userInputNumber("Pleas enter seconds to subtract");
        try {
            return calendarSubtractService.subtractSecondsToDate(calendar, value);
        } catch (CalendarException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return subtractingSeconds(calendar);
    }

    private Calendar addingSeconds(Calendar calendar) {
        int numberOfSeconds = UserInput.userInputNumber("Pleas enter seconds to add");
        try {
            return calendarAddingService.addSecondsToDate(calendar, numberOfSeconds);
        } catch (CalendarException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return addingSeconds(calendar);
    }

    private Calendar addingMinutes(Calendar calendar) {
        int numberOfMinutes = UserInput.userInputNumber("Pleas enter minutes to add");
        try {
            return calendarAddingService.addMinutesToDate(calendar, numberOfMinutes);
        } catch (CalendarException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return addingMinutes(calendar);
    }

    private Calendar addingHours(Calendar calendar) {
        int numberOfHours = UserInput.userInputNumber("Pleas enter hours to add");
        try {
            return calendarAddingService.addHoursToDate(calendar, numberOfHours);
        } catch (CalendarException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return addingHours(calendar);
    }

    private Calendar addingDays(Calendar calendar) {
        int numberOfDays = UserInput.userInputNumber("Pleas enter days to add");
        try {
            return calendarAddingService.addDaysToDate(calendar, numberOfDays);
        } catch (CalendarException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return addingDays(calendar);
    }

    private Calendar addingMonth(Calendar calendar) {
        int numberOfMonth = UserInput.userInputNumber("Pleas enter month to add");
        try {
            return calendarAddingService.addMonthToDate(calendar, numberOfMonth);
        } catch (CalendarException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return addingMonth(calendar);
    }

    private Calendar addingYears(Calendar calendar) {
        int numberOfYears = UserInput.userInputNumber("Pleas enter years to add");
        try {
            return calendarAddingService.addYearsToDate(calendar, numberOfYears);
        } catch (CalendarException e) {
            System.err.println(e.getMessage() + "\nPleas start again");
        }
        return addingYears(calendar);
    }

    public List<Calendar> calendarsSorting() {
        int type = UserInput.userInputNumber("Enter sorting method 1 to ASC 2 to DESC");
        System.out.println("Attention!!! Date format is " + Constants.datePatterns.get(parserFormat - 1));
        int number = UserInput.userInputNumber("Enter number of dates to be sorted");
        List<Calendar> calendars = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            calendars.add(enterDate());
        }
        if (type == 1) {
            return new CalendarSortingService(calendars).sortASC();
        }
        if(type == 2){
            return new CalendarSortingService(calendars).sortDESC();
        }else {
            System.err.println("Wrong sorting type!!! Pleas try again");
            return calendarsSorting();
        }

    }

}
