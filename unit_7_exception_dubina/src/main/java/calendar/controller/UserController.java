package calendar.controller;

import calendar.formatter.DateTimeValidator;
import calendar.formatter.MyFormatter;
import calendar.service.DateTimeCalculatorImpl;

import java.util.*;

import static calendar.service.DateTimeCalculatorImpl.*;
import static calendar.service.DateTimeCalculatorImpl.diffInSeconds;

public class UserController {

    private static List<DateTimeCalculatorImpl> dateTimeList = new ArrayList<>();
    private static MyFormatter.DateFormats format = MyFormatter.DateFormats.DD_MMM_YYYY;
    public static final String SEPARATOR = "_______________________________________________";
    public static final String SUCCESS_MESSAGE = "Your action successfully done.";

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            showDateList();
            System.out.println(SEPARATOR);
            actionMainMenuList();
            System.out.println(SEPARATOR);

            switch (scanner.nextLine()) {
                case "1": {
                    fillTimeList();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "2": {
                    addDateToDateList();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "3": {
                    remove();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "4": {
                    differenceBetweenDate();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "5": {
                    changeDateTime();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "6": {
                    sortDatesList();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "7": {
                    setFormat();
                    System.out.println(SEPARATOR);
                    break;
                }
                default: {
                    System.out.println("Thank you for using this app!");
                    System.exit(0);
                }
            }
        }
    }


    public static void actionMainMenuList() {
        System.out.println("Please select option: (for e.x. 1) ");
        System.out.println(
                "- 1 - Add existing dates to Calendar automatically (press Enter to confirm)\n" +
                        "- 2 - Add date to dates list\n" +
                        "- 3 - Remove date\n" +
                        "- 4 - Find differences between dates\n" +
                        "- 5 - Add years, dates, hours and other to existing date\n" +
                        "- 6 - Sort dates list\n" +
                        "- 7 - Change format of existing date\n" +
                        "- 0 - Exit from application");
    }

    public static void showDateList() {
        if (dateTimeList.isEmpty()) {
            System.out.println("Dates list is empty.");
        } else {
            System.out.println("Your date List:");
            for (int i = 1; i <= dateTimeList.size(); i++) {
                System.out.println(i + ". " + DateTimeValidator.format(dateTimeList.get(i - 1), format));
            }
        }
    }

    public static void dateFormatsSelector() {
        System.out.println("Select format: ");
        MyFormatter.DateFormats[] values = MyFormatter.DateFormats.values();
        for (int i = 1; i <= MyFormatter.DateFormats.values().length; i++) {
            System.out.println(i + ". " + values[i - 1].format + " ");
        }
    }

    public static void addDateToDateList() {
        try {
            System.out.println("Input your date following to next format: \n" +
                    "dd/mm/yyyy  or  dd/mm/yy hh:mm:ss (Use '/' for separating date and ':' for separating time " +
                    "e.g. 20/06/1995 19:20:12)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            plus(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect input date: " + e.getMessage());
        }
    }

    private static void plus(String input) {
        String[] parts = input.split("\\s");
        if (parts.length != 1 && parts.length != 2) {
            throw new IllegalArgumentException("Use correct quantity of elements.");
        }
        DateTimeCalculatorImpl parsed = MyFormatter.parserDateTime(parts[0], parts.length == 2 ? parts[1] : null);
        dateTimeList.add(parsed);
        System.out.println(SUCCESS_MESSAGE);
    }

    public static void remove() {
        System.out.println("Select number of date what you want to remove: (e.g. 1)");
        showDateList();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt() - 1;
        try {
            dateTimeList.remove(input);
            System.out.println(SUCCESS_MESSAGE);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println("This date number doesn't exists: " + e.getMessage());
        }
    }

    public static void differenceBetweenDate() {
        System.out.println("Enter two numbers of dates which you want to compare: (use Space between them)");
        showDateList();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        showDifference(input);
        System.out.println(SUCCESS_MESSAGE);
    }

    private static void showDifference(String input) {
        try {
            String[] parts = input.split("\\s");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Wrong numbers of date. Please enter 2 numbers.");
            }
            int index1 = Integer.parseInt(parts[0]);
            int index2 = Integer.parseInt(parts[1]);
            if (index1 < 0 || index1 >= dateTimeList.size() || index2 < 0 || index2 >= dateTimeList.size()) {
                throw new IndexOutOfBoundsException("No date with such index");
            }
            System.out.println("Difference between " + DateTimeValidator.format(dateTimeList.get(index1), format)
                    + " and " + DateTimeValidator.format(dateTimeList.get(index2), format));
            System.out.println("Years: " + diffInYears(dateTimeList.get(index1), dateTimeList.get(index2)));
            System.out.println("Months: " + diffInMonth(dateTimeList.get(index1), dateTimeList.get(index2)));
            System.out.println("Days: " + diffInDays(dateTimeList.get(index1), dateTimeList.get(index2)));
            System.out.println("Hours: " + diffInHours(dateTimeList.get(index1), dateTimeList.get(index2)));
            System.out.println("Minutes: " + diffInMinutes(dateTimeList.get(index1), dateTimeList.get(index2)));
            System.out.println("Seconds: " + diffInSeconds(dateTimeList.get(index1), dateTimeList.get(index2)));
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void changeDateTime() {
        try {
            System.out.println("Enter number of date what you want to change: ");
            showDateList();
            Scanner scanner = new Scanner(System.in);
            int dateNumber = scanner.nextInt();

            System.out.println("Enter section of date what you want to change: ");
            System.out.println("- 1 - year; \n- 2 - month; \n- 3 - day; \n- 4 - hour; \n- 5 - minute; \n- 6 - second");
            int sectionNumber = scanner.nextInt();

            System.out.println("Enter scale of date what you want to change: ");
            int scale = scanner.nextInt();

            int input[] = {dateNumber, sectionNumber, scale};
            add(input);

            System.out.println(SUCCESS_MESSAGE);
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect input date: " + e.getMessage());
        }
    }

    private static void add(int[] input) {
        if (input.length != 3) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        int index = input[0] - 1;
        int section = input[1];
        int scale = input[2];
        switch (section) {
            case 1: {
                dateTimeList.get(index).changeYear(scale);
                break;
            }
            case 2: {
                dateTimeList.get(index).changeMonth(scale);
                break;
            }
            case 3: {
                dateTimeList.get(index).changeDay(scale);
                break;
            }
            case 4: {
                dateTimeList.get(index).changeHours(scale);
                break;
            }
            case 5: {
                dateTimeList.get(index).changeMinute(scale);
                break;
            }
            case 6: {
                dateTimeList.get(index).changeSecond(scale);
                break;
            }
            default: {
                throw new IllegalArgumentException("Something went wrong.");
            }
        }
    }

    private static void sortDatesList() {
        showDateList();
        System.out.println("Please select option how do you want to sort Dates list: (for e.x. 1) ");
        System.out.println(
                "- 1 - Ascending sorting\n" +
                        "- 2 - Descending sorting\n");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s");
        if (input.length != 1) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        if (dateTimeList.isEmpty()) {
            throw new IllegalArgumentException("No dates yet");
        } else {
            try {
                int sortOption = Integer.parseInt(input[0]);
                sort(sortOption);
            } catch (NumberFormatException nfe) {
                System.out.println("NumberFormatException: " + nfe.getMessage());
            }
        }
    }

    private static void sort(int input) {
        Set<DateTimeCalculatorImpl> datesSet;
        switch (input) {
            case 1: {
                datesSet = new TreeSet<>(dateTimeList);
                break;
            }
            case 2: {
                datesSet = new TreeSet<>(dateTimeList).descendingSet();
                break;
            }
            default:
                throw new IllegalArgumentException("Sorting method incorrect");
        }
        System.out.println(SUCCESS_MESSAGE);
        System.out.println("Dates sorted.");
    }

    private static void setFormat() {
        System.out.println("Choose format of displayed Dates list: ");
        dateFormatsSelector();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parts = input.split("\\s");
        if (parts.length != 1) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        int index = Integer.parseInt(parts[0]);
        MyFormatter.DateFormats[] values = MyFormatter.DateFormats.values();
        if (index < 1 || index > 4) {
            throw new IllegalArgumentException("Incorrect index");
        }
        format = values[index - 1];
        System.out.println(SUCCESS_MESSAGE);
    }

    private static void fillTimeList() {
        dateTimeList = new ArrayList<>();
        dateTimeList.add(new DateTimeCalculatorImpl(2015, 1, 14, 11, 11, 45));
        dateTimeList.add(new DateTimeCalculatorImpl(2000, 7, 8, 6, 28, 18));
        dateTimeList.add(new DateTimeCalculatorImpl(1465, 8, 18, 1, 44, 25));
        dateTimeList.add(new DateTimeCalculatorImpl(321, 6, 11, 14, 14, 11));
        System.out.println(SUCCESS_MESSAGE);
    }
}
