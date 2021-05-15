package ua.com.nkrasnovoronka.tests.level2.task1;

import ua.com.nkrasnovoronka.tasks.level2.task1.BracketParser;
import ua.com.nkrasnovoronka.util.UserInput;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BracketParserTest {
    public static void randomBracketParserTest() {
        System.out.println("Starting random brackets parser test: Added 100 iteration");
        Character[] bracketsArray = {'(', ')', '[', ']', '{', '}', 'a', 'b', 'c', 'd', '1', '2', '3', '4', '5', ' '};
        List<Character> characters;
        for (int i = 0; i < 100; i++) {
            characters = Arrays.asList(bracketsArray);
            Collections.shuffle(characters);
            String collect = characters.stream().map(Object::toString).collect(Collectors.joining(""));
            boolean stringValid = BracketParser.isStringValid(collect);
            if (stringValid) {
                System.out.println(collect + " is valid");
            }
        }
    }

    public static void userInputParserTest() {
        System.out.println("Starting user input brackets parser test");
        String s = UserInput.userInputString();
        boolean stringValid = BracketParser.isStringValid(s);
        System.out.println("String " + s + " is " + (stringValid ? "valid" : "not valid"));
    }
}
