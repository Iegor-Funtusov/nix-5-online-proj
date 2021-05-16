package app;

import lib.ReverseString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringController {
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public void userInterface() throws IOException {
        do{
            System.out.println("What do you want to do? \n " +
                    "1-reverse full string \n " +
                    "2-reverse substring \n " +
                    "3-reverse from index to index \n " +
                    "4-reverse from char to char \n " +
                    "5-reverse from string to string");

            switch (Integer.parseInt(bf.readLine())){
                case 1 -> reverseFullString();
                case 2 -> reverseSubstring();
                case 3 -> reverseFromIndexToIndex();
                case 4 -> reverseFromCharToChar();
                case 5 -> reverseFromStringToString();
                default -> System.out.println("Incorrect value entered");
            }
            System.out.println("Do you want to continue? 1-yes, else-no");

        } while (Integer.parseInt(bf.readLine()) == 1);
    }


    private void reverseFullString() throws IOException{
        System.out.println("Enter string which you want to reverse");
        String str = bf.readLine();
        str = ReverseString.reverse(str);

        printString(str);
    }


    private void reverseSubstring() throws IOException{
        System.out.println("Enter string");
        String str = bf.readLine();

        System.out.println("Enter substring which which you want to reverse");
        String subStr = bf.readLine();

        str = ReverseString.reverse(str, subStr);

        printString(str);
    }


    private void reverseFromIndexToIndex() throws IOException{
        System.out.println("Enter string");
        String str = bf.readLine();

        System.out.println("Enter first index counting from zero");
        int first = Integer.parseInt(bf.readLine());

        System.out.println("Enter last index");
        int last = Integer.parseInt(bf.readLine());

        str = ReverseString.reverse(str, first, last);
        printString(str);
    }

    private void reverseFromCharToChar() throws IOException{
        System.out.println("Enter string");
        String str = bf.readLine();

        System.out.println("Enter first character");
        char firstChar = bf.readLine().charAt(0);

        System.out.println("Enter last index");
        char lastChar = bf.readLine().charAt(0);

        str = ReverseString.reverse(str, firstChar, lastChar);
        printString(str);
    }


    private void reverseFromStringToString() throws IOException{
        System.out.println("Enter string");
        String str = bf.readLine();

        System.out.println("Enter first substring that will be start");
        String start = bf.readLine();

        System.out.println("Enter first substring that will be finish");
        String finish = bf.readLine();

        str = ReverseString.reverse(str, start, finish);
        printString(str);
    }


    private void printString(String str){
        if(str != null){
            System.out.println("Result: " + str);
            return;
        }
        System.out.println("Error!");
    }
}
