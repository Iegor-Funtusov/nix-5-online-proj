package ua.com.alevel;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppMain {

    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {
        while (true)
            callInterface();
    }

    private static void callInterface() {
        String stringForValidation;
        while (true) {
            printFirstTaskMenu();
            stringForValidation = createString();
            if (stringForValidation == null) {
                System.out.println("Invalid entering!");
                continue;
            }
            if (StringUtils.isStringValid(stringForValidation)) {
                System.out.println("String : \"" + stringForValidation + "\" . Valid!");
            }else{
                System.out.println("String : \"" + stringForValidation + "\" . Invalid!");
            }
        }
    }

    @SneakyThrows
    private static String createString() {
        String string = null;
        switch (reader.readLine()){
            case "1":{
                string = getManualString();
                break;
            }
            case "2":{
                string = getRandomString();
                break;
            }
            case "0":{
                break;
            }
            default:{
                System.out.println("Invalid choice! Try again!");
            }
        }
        return string;
    }

    private static String getRandomString() {
        char[] generatedArray = new char[10];
        char[] symbols = {'(', ')', '{', '}', '[', ']',
                            'a', 'b', 'c' , 'd', 'f', 'g', 'h'};
        int symbolPosition;
        for (int i = 0; i < generatedArray.length ; i++) {
            symbolPosition = (int) (Math.random() * (symbols.length - 1));
            generatedArray[i] = symbols[symbolPosition];
        }
        return new String(generatedArray);
    }

    private static String getManualString() {
        String string = null;
        System.out.println("Enter your string: ");
        try {
            string = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    private static void printFirstTaskMenu() {
        System.out.print("String validator!" +
                "\nFirst task menu!" +
                "\nChoose type of string creating :" +
                "\n 1 - manual" +
                "\n 2 - random" +
                "\n 0 - exit menu" +
                "\n -->");
    }
}
