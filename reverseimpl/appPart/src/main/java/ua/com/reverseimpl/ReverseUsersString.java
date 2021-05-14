package ua.com.reverseimpl;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class ReverseUsersString {

   private static final Scanner scanner = new Scanner(System.in);

    public static void processUserString(){
        boolean checker = true;
        while(checker) {
            System.out.println("Would you like to reverse some string? y/n");
            String result = scanner.next();
            switch (result){
                case "y": {
                    ReverseUsersString.chosenMethod();
                    processUserString();
                }
                case "n": {
                    checker = false;
                    break;
                }
                default: {
                    System.out.println("Please, make Your choice!");
                }
            }
        }
    }

    public static void chosenMethod() {
        boolean checker = true;
        while (checker) {
            System.out.println("Choose the function: ");
            System.out.println("1 -> Usual reverse ");
            System.out.println("2 -> Reverse substring in a string");
            System.out.println("3 -> Reverse symbols between 'a' and 'b' indexes in a string:");
            String result = scanner.next();
            switch (result) {
                case "1": {
                    scanner.nextLine();
                    usualReverse();
                    checker = false;
                    break;
                }
                case "2": {
                    scanner.nextLine();
                    reverseSubstring();
                    checker = false;
                    break;
                }
                case "3": {
                    scanner.nextLine();
                    reverseInARange();
                    checker = false;
                    break;
                }
                default: {
                    System.out.println("Please, make Your choice!");
                }
            }
        }
    }

    public static void usualReverse(){
        System.out.println("Enter you string");
        String src = scanner.nextLine();
        if(src.length() >=2){
            System.out.println(reverseString.reverse(src));
        }
        else{
            System.out.println("Invalid string -> too short");
            usualReverse();
        }
    }

    public static void reverseSubstring(){
        System.out.println("Enter string ");
        String s = scanner.nextLine();
        System.out.println("Enter substring ");
        String dest = scanner.nextLine();
        if(StringUtils.isNoneBlank(s) && StringUtils.isNoneBlank(dest)
                && s.length() > dest.length()){
            System.out.println(reverseString.reverse(s, dest));
        }
        else {
            System.out.println("Invalid string/substring -> empty or has invalid length");
            reverseSubstring();
        }
    }

    public static void reverseInARange(){
        System.out.println("Enter string ");
        String s = scanner.nextLine();
        System.out.println("Enter first index ");
        int from = scanner.nextInt();
        System.out.println("Enter last index ");
        int to = scanner.nextInt();
        if(StringUtils.isNoneBlank(s) && from > 0 && from < to
                && from < s.length() && to <= s.length()-1){
            System.out.println(reverseString.reverse(s, from, to));
        }
        else {
            System.out.println("Invalid data -> empty string, invalid range");
            scanner.nextLine();
            reverseInARange();
        }
    }
}
