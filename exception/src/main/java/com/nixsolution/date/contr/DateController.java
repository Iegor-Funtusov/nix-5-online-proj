package com.nixsolution.date.contr;

import com.nixsolution.date.calcul.DateCalculator;
import com.nixsolution.date.data.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.nixsolution.date.util.UserInput;
public class DateController {
    private static final BufferedReader reader;
    private static final String REGEX_DD_MM_YYYY = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/(\\d{4})$";
    private static final String REGEX_M_D_YYYY = "^(1[0-2]|[1-9])\\/(3[01]|[12][0-9]|[1-9])\\/(\\d{4})$";
    private static final String FORMAT_DD_MM_YYYY = "dd/mm/yyyy";
    private static final String FORMAT_M_D_YYYY = "m/d/yyyy";


    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void selectFormat(DateCalculator date) {
        System.out.println("Please, choose a format of date (input 1 or 2) end press Enter:\n" +
                "1. dd/mm/yyyy\n" +
                "2. m/d/yyyy");
        switch (UserInput.printMainSelection()) {
            case "1": {
                parseDayMonthYear(UserInput.printDateInput(FORMAT_DD_MM_YYYY, REGEX_DD_MM_YYYY), date);
                break;
            }
            case "2": {
                parseMonthDayYear(UserInput.printDateInput(FORMAT_M_D_YYYY, REGEX_M_D_YYYY), date);
            }
        }
    }

    public void printDatesDifference() {

        System.out.println("Please, select the output format of the difference" +
                " (input from 1 to 6) and press Enter.\n" +
                "Difference in: \n" +
                "1. Years\n" +
                "2. Months\n" +
                "3. Days\n" +
                "4. Hours\n" +
                "5. Minutes\n" +
                "6. Seconds");

        switch (UserInput.printMainSelection()) {
            case "1": {
                System.out.println(createDate().yearDifference(createDate()));
                break;
            }
            case "2": {
                System.out.println(createDate().monthDifference(createDate()));
                break;
            }
            case "3": {
                System.out.println(createDate().dayDifference(createDate()));
                break;
            }
            case "4": {
                System.out.println(createDate().hourDifference(createDate()));
                break;
            }
            case "5": {
                System.out.println(createDate().minuteDifference(createDate()));
                break;
            }
            case "6": {
                System.out.println(createDate().secondDifference(createDate()));
                break;
            }
        }
    }

    public void calculateDate(int selection) {

        DateCalculator date = createDate();

        System.out.println("Please, choose the item you want to change " +
                "(input from 1 to 6) and press Enter.\n" +
                "Change format of: \n" +
                "1. Years\n" +
                "2. Months\n" +
                "3. Days\n" +
                "4. Hours\n" +
                "5. Minutes\n" +
                "6. Seconds");

        switch (UserInput.printMainSelection()) {
            case "1": {
                if (selection == 1) {
                    date.plusYear(UserInput.printNumberInput("years"));

                } else if (selection == 2) {
                    date.minusYear(UserInput.printNumberInput("years"));
                }
                System.out.println("New date: " + date);
                break;
            }
            case "2": {
                if (selection == 1) {
                    date.plusMonth(UserInput.printNumberInput("months"));

                } else if (selection == 2) {
                    date.minusMonth(UserInput.printNumberInput("months"));
                }
                System.out.println("New date: " + date);
                break;
            }
            case "3": {
                if (selection == 1) {
                    date.plusDay(UserInput.printNumberInput("days"));

                } else if (selection == 2) {
                    date.minusDay(UserInput.printNumberInput("days"));
                }
                System.out.println("New date: " + date);
                break;
            }
            case "4": {
                if (selection == 1) {
                    date.plusHour(UserInput.printNumberInput("hours"));

                } else if (selection == 2) {
                    date.minusHour(UserInput.printNumberInput("hours"));
                }
                System.out.println("New date: " + date);
                break;
            }
            case "5": {
                if (selection == 1) {
                    date.plusMinute(UserInput.printNumberInput("minutes"));

                } else if (selection == 2) {
                    date.minusMinute(UserInput.printNumberInput("minutes"));
                }
                System.out.println("New date: " + date);
                break;
            }
            case "6": {
                if (selection == 1) {
                    date.plusSecond(UserInput.printNumberInput("seconds"));

                } else if (selection == 2) {
                    date.minusSecond(UserInput.printNumberInput("seconds"));
                }
                System.out.println("New date: " + date);
                break;
            }
        }
    }

    private void selectSort(List<DateCalculator> list) {

        System.out.println("Please, choose a sort method (input 1 or 2) and press Enter:\n" +
                "1. Ascending sort\n" +
                "2. Descending sort");
        switch (UserInput.printMainSelection()) {
            case "1": {
                list.sort(Comparator.comparing(Date::getDateInSeconds));
                break;
            }
            case "2": {
                list.sort(Comparator.comparing(Date::getDateInSeconds).reversed());
                break;
            }
            case "0": {
            }
        }
    }

    public void sortDate() {

        List<DateCalculator> list = new ArrayList<>();
        int x = UserInput.printNumberInput("dates");

        DateCalculator date;

        for (int i = 0; i < x; i++) {
            date = createDate();
            list.add(date);
        }

        selectSort(list);
        System.out.println(list);
    }

    private DateCalculator createDate() {

        DateCalculator date = new DateCalculator();
        selectFormat(date);
        System.out.println("Date created successfully");
        System.out.println("Do you want to add a time to your date?\n" +
                "Please, choose your option 0-1 and press Enter.\n" +
                "1. Add time to date.\n" +
                "2. Skipp time adding.\n");
        if (UserInput.printMainSelection().equals("1")) {
            UserInput.printTimeInput(date);
        }
        return date;
    }

    private void parseDayMonthYear(String text, DateCalculator date) {

        String[] array = text.split("/");
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].charAt(0) == '0') {
                array[i] = array[i].substring(1);
            }
        }
        while (array[2].charAt(0) == '0') {
            array[2] = array[2].substring(1);
        }
        date.setDay(Integer.parseInt(array[0]));
        date.setMonth(Integer.parseInt(array[1]));
        date.setYear(Integer.parseInt(array[2]));
    }

    private void parseMonthDayYear(String text, DateCalculator date) {

        String[] array = text.split("/");
        while (array[2].charAt(0) == '0') {
            array[2] = array[2].substring(1);
        }
        date.setDay(Integer.parseInt(array[1]));
        date.setMonth(Integer.parseInt(array[0]));
        date.setYear(Integer.parseInt(array[2]));
    }
}
