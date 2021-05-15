package com.nixsolutions.courses.utils;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class Level2Utils {

    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 100;
    private static final int CHAR_MIN = 40;
    private static final int CHAR_MAX = 125;

    public static String createString(BufferedReader reader) throws IOException {
        System.out.println("Do you want to input string?\n0 - no\n1 - yes");
        String result;
        if(reader.readLine().equals("0")){
            result = generateString();
        } else {
            System.out.println("Enter string:");
            result = reader.readLine();
        }
        return result;
    }

    private static String generateString() {
        Random random = new Random();
        int targetStringLength = random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN;


        String generatedString = random.ints(CHAR_MIN, CHAR_MAX + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println("Generated string:\n" + generatedString);

        return generatedString;
    }

    public static void printOptions() {
        System.out.println("Continue?\n0 - no\n1 - yes");
    }

}
