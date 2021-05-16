package app;

import libs.ReverseString;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Control {
    public static void consoleControl (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your string");
        String string = scanner.nextLine();
        while (true) {
            System.out.println("Choose the type of inverse\n" +
                    "1 >> usual inverse (e.g. Input: hello | Output: olleh)\n" +
                    "2 >> inverses with substring (e.g Input: hello world / wor | Output: hello rowld)\n" +
                    "3 >> inverses with indexes (e.g. Input: hello world / 3 / 7 | Output: helol owrld\n" +
                    "4 >> inverses with symbols (e.g. Input: hello world / l / r | Output: heoll rowld)\n" +
                    "5 >> exit from the program");
            String reverse = scanner.nextLine();
            switch (reverse) {
                case "1": {
                    System.out.println(ReverseString.reverse(string));
                }
                break;
                case "2": {
                    choiceSubstring(string);
                }
                break;
                case "3": {
                    choiceIndexes(string);
                }
                break;
                case "4": {
                    choiceSymbols(string);
                }
                break;
                case "5":{
                    System.exit(0);
                }
                default: {
                    System.out.println("Incorrect input. Input again");
                }
            }
            System.out.println("Would you like to change your string? (yes, no)");
            String input = scanner.nextLine();
            switch (input){
                case "yes":{
                    System.out.println("Input new string");
                    string = scanner.nextLine();
                }
            }
        }
    }

    private static void choiceSubstring(String string){
        Scanner scanner = new Scanner(System.in);
        String substring;
        while (true) {
            System.out.println("Input substring");
            substring = scanner.nextLine();
            System.out.println("Choose an action:\n" +
                    "1 >> reverse all substrings in you main string\n" +
                    "2 >> reverse all before such substring in main string (with substring inclusively)\n" +
                    "3 >> reverse all after such substring in main string(with substring inclusively)\n");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": {
                    System.out.println(ReverseString.reverse(string, substring));
                }
                break;
                case "2": {
                    System.out.println(ReverseString.reverseBeforeString(string, substring));
                }
                break;
                case "3": {
                    System.out.println(ReverseString.reverseFromString(string, substring));
                }
                break;
                default: {
                    System.out.println("Incorrect input");
                }
            }
            if(Return() == true){
                return;
            }
        }
    }

    private static void choiceIndexes(String string){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Choose an action:\n" +
                    "1 >> reverse from the index to index\n" +
                    "2 >> reverse from the beginning to index\n" +
                    "3 >> reverse from the index to end\n");
            String choice = scanner.nextLine();
            int len = string.length();
            switch (choice) {
                case "1": {
                    int index1, index2;
                    System.out.println("Input indexes");
                    System.out.print("First index: ");
                    index1 = checkIndex(len);
                    System.out.print("Second index: ");
                    index2 = checkIndex(len);
                    while (index1 > index2) {
                        System.out.println("Second index must be more than first. Input again");
                        System.out.print("First index: ");
                        index1 = checkIndex(string.length());
                        System.out.print("Second index: ");
                        index2 = checkIndex(string.length());
                    }
                    System.out.println(ReverseString.reverse(string, index1, index2));
                }
                break;
                case "2": {
                    System.out.println("Enter an index");
                    int index = checkIndex(len);
                    System.out.println(ReverseString.reverse(string, 0, index));
                }
                break;
                case "3": {
                    System.out.println("Enter an index");
                    int index = checkIndex(len);
                    System.out.println(ReverseString.reverse(string, index, len - 1));
                }
                break;
                default: {
                    System.out.println("Incorrect input");
                }
            }
            if(Return() == true){
                return;
            }
        }
    }

    private static void choiceSymbols(String string){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an action:\n" +
                    "1 >> reverse from the symbol to symbol\n" +
                    "2 >> reverse from the beginning to symbol\n" +
                    "3 >> reverse from the symbol to end\n");
            String choice = scanner.nextLine();
            int len = string.length();
            switch (choice) {
                case "1": {
                    char symbol1, symbol2;
                    System.out.println("Input symbols");
                    System.out.print("First symbol: ");
                    symbol1 = symbol();
                    System.out.print("Second symbol: ");
                    symbol2 = symbol();
                    System.out.println(ReverseString.reverse(string, symbol1, symbol2));
                }
                break;
                case "2": {
                    System.out.println("Enter a symbol");
                    char symbol = symbol();
                    System.out.println(ReverseString.reverse(string, string.charAt(0), symbol));
                }
                break;
                case "3": {
                    System.out.println("Enter a symbol");
                    char symbol = symbol();
                    System.out.println(ReverseString.reverse(string, symbol, string.charAt(len - 1)));
                }
                break;
                default: {
                    System.out.println("Incorrect input");
                }
            }
            if(Return() == true){
                return;
            }
        }
    }

    private static char symbol(){
        Scanner scanner = new Scanner(System.in);
        String string;
        string = scanner.nextLine();
        if(string.length() != 1){
            System.out.println("Input one symbol");
            return symbol();
        }
        char symbol = string.charAt(0);
        return symbol;
    }

    private static int checkIndex (int len){
        Scanner scanner = new Scanner(System.in);
        int num;
        while (true) {
            try {
                num = scanner.nextInt();
                if(num < 0 || num >= len){
                    System.out.println("Index is beyond the borders of your string. Input index again");
                    return checkIndex(len);
                }
                return num;
            } catch (InputMismatchException ex) {
                System.out.println("Wrong input. Input integer. Try again");
                return checkIndex(len);
            }
        }
    }

    private static boolean Return(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to return to choice of the way reversing or change the string? (yes, no)");
        String input = scanner.next();
        switch (input) {
            case "yes": {
                return true;
            }
        }
        return false;
    }
}
