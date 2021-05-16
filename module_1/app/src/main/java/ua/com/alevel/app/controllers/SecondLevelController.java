package ua.com.alevel.app.controllers;

import ua.com.alevel.lib.StringValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class SecondLevelController {

    public static void stringValidator() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inp;

        System.out.println("Choose the way of input:\n" +
                "1 -> Do it by yourself\n" +
                "2 -> Random input");
        if(reader.readLine().equals("1")){
            System.out.print("Input string you want to validate: ");
            String input = reader.readLine();
            System.out.println("Is this string valid: " + StringValidator.isValid(input));
        }
        else{
            String result = generateString();
            System.out.println("Generated string: " + result);
            System.out.println("Is this string valid: " + StringValidator.isValid(result));
        }
    }

    private static String generateString() {
        Random random = new Random();
        int length = random.nextInt(75);
        return random.ints(40, 125 + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
