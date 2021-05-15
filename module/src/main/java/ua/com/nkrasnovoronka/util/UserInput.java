package ua.com.nkrasnovoronka.util;

import ua.com.nkrasnovoronka.tests.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UserInput {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static List<Integer> userInputNumbers() {
        List<Integer> userInput = new ArrayList<>();
        System.out.println("Pleas enter numbers split by space");
        try {
            userInput = Stream.of(bufferedReader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Sorry wrong input!!!Pleas restart program");
        }
        return userInput;
    }

    public static int userInputNumber(){
        int num = 0;
        System.out.println("Pleas enter number");
        try {
            num = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            System.err.println("Sorry wrong input!!!Pleas restart program");
        }
        return num;
    }

    public static void taskAction(Test test, boolean random) {
        if (random) {
            test.randomTest();
        } else {
            test.userTest();
        }
    }

    public static String userInputString(){
        System.out.println("Pleas enter string ");
        String res = "";
        try {
            res = bufferedReader.readLine();
        } catch (IOException e) {
            System.err.println("Sorry wrong input!!!Pleas restart program");
        }
        return res;
    }

    public static String readMovePosition() {
        System.out.println("Pleas enter move position like(a1 a2)");
        String position = "";
        try {
            position = bufferedReader.readLine().toLowerCase(Locale.ROOT);
        } catch (IOException e) {
            System.err.println("Oops!! Please check your input");
        }
        return position;
    }
}
