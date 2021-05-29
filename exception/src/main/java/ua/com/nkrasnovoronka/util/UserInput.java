package ua.com.nkrasnovoronka.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

    public static int userInputNumber() {
        int num = 0;
        System.out.println("Pleas enter number");
        try {
            num = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            System.err.println("Sorry wrong input!!!Pleas restart program");
        }
        return num;
    }

    public static String userInputString() {
        System.out.println("Pleas enter string ");
        String res = "";
        try {
            res = bufferedReader.readLine();
        } catch (IOException e) {
            System.err.println("Sorry wrong input!!!Pleas restart program");
        }
        return res;
    }

}
