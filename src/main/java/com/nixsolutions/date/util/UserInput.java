package com.nixsolutions.date.util;

import com.nixsolutions.date.math.DateCalculator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {

    private static final String REGEX_NUMBER = "^[0-9]+$";
    private static final String REGEX_TIME = "^(\\d\\d:\\d\\d)$";
    private static final String REGEX_CONST = "^[0-9]$";
    private static final BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static String printDateInput(String pattern, String regex) {

        String input;
        do {
            System.out.println("Please, input a date e.g.: " + pattern + " (use '/' to separate dates)");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Wrong date!", e);
            }
        } while (!input.matches(regex));
        return input;
    }

    public static void printTimeInput(DateCalculator date) {

        String input;
        String[] array;

        do {
            do {
                System.out.println("Please, input a time e.g.: '10:10'" +
                        "(use ':' to separate hours and minutes) and press Enter:");
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException("Can't read date", e);
                }
            } while (!input.matches(REGEX_TIME));

            array = input.split(":");
            for (int i = 0; i < array.length; i++) {
                if (array[i].charAt(0) == '0') {
                    array[i] = String.valueOf(array[i].charAt(1));
                }
            }
        } while (Integer.parseInt(array[0]) > 23 || Integer.parseInt(array[1]) > 59);
        date.setHour(Integer.parseInt(array[0]));
        date.setMinute(Integer.parseInt(array[1]));
    }

    public static int printNumberInput(String time) {

        String num;
        do {
            try {
                System.out.println("How many " + time + " do you want to enter?");
                num = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Can't read user input", e);
            }
        } while (!num.matches(REGEX_NUMBER));
        return Integer.parseInt(num);
    }

    public static String printMainSelection() {

        String constant;
        do {
            System.out.println("Your choice: ");
            try {
                constant = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Can't read user input!", e);
            }
        } while (!constant.matches(REGEX_CONST));
        return constant;
    }

}
