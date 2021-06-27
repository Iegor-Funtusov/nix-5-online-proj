package com.nixsolutions.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {

    private static final String REGEX_NUM = "^[0-5]$";

    private static final BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static String selectionIntInput() {

        String numInput;

        do {
            System.out.println("Your choice: ");
            try {
                numInput = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Can't read user input!", e);
            }
        } while (!numInput.matches(REGEX_NUM));
        return numInput;
    }

    public static String stringInput() {
        String str = "";
        try {
            str = reader.readLine();
        } catch (IOException e) {
            System.err.println("Wrong Input!");
        }
        return str;
    }

}
