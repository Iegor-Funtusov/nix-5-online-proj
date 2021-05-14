package ua.com.alevel.app;

import ua.com.alevel.lib.StringValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondLevelController {

    public static void stringValidator() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Input string you want to validate: ");
        String input = reader.readLine();

        System.out.println("Is this string valid: " + StringValidator.isValid(input));
    }
}
