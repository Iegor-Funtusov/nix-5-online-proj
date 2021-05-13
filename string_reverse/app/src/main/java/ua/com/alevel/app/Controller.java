package ua.com.alevel.app;

import ua.com.alevel.lib.ReverseString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    public static BufferedReader reader;

    public static void menu () {
        reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        try {
            System.out.println("Choose the action:\n" +
                    "1 -> Auto-test for all functions\n" +
                    "2 -> Reverse string\n" +
                    "3 -> Reverse substring in string\n" +
                    "4 -> Reverse from index to index\n" +
                    "5 -> Reverse from char to char\n" +
                    "6 -> Reverse from string to string\n" +
                    "7 -> Reverse from char sequence to char sequence\n" +
                    "0 -> Stop the program");
            while ((input = reader.readLine())!= null) {
                switch (input) {
                    case "1" : {
                        AutoTest();
                    } break;
                    case "2" : {
                        reverseStr();
                    } break;
                    case "3" : {
                        reverseSubStr();
                    } break;
                    case "4" : {
                        reverseIndToInd();
                    } break;
                    case "5": {
                        reverseCharToChar();
                    } break;
                    case "6": {
                        reverseSubStrToSubStr();
                    } break;
                    case "7": {
                        reverseCharSeqToCharSeq();
                    } break;
                    case "0": {
                        System.exit(0);
                    }
                    default: {
                        System.out.println("Wrong input");
                    }break;

                }
                System.out.println("Choose the action:\n" +
                        "1 -> Auto-test for all functions\n" +
                        "2 -> Reverse string\n" +
                        "3 -> Reverse substring in string\n" +
                        "4 -> Reverse from index to index\n" +
                        "5 -> Reverse from char to char\n" +
                        "6 -> Reverse from string to string\n" +
                        "7 -> Reverse from char sequence to char sequence\n" +
                        "0 -> Stop the program");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AutoTest() {
        CharSequence first = "me";
        CharSequence second = "xt";
        System.out.println("Ordinary reverse\n" + "some text information = " + ReverseString.reverse("some text information"));
        System.out.println("Reversing substring \"inform\"\n" + "some text information = " + ReverseString.reverse("some text information", "inform"));
        System.out.println("Reversing from index 1 to index 4\n" + "some text information = " + ReverseString.reverse("some text information", 1, 4));
        System.out.println("Reversing from char m to char x\n" + "some text information = " + ReverseString.reverse("some text information", 'm', 'x'));
        System.out.println("Reversing from substring \"me\" to substring \"xt\"\n" + "some text information = " + ReverseString.reverse("some text information", "me", "xt"));
        System.out.println("Reversing from char sequence \"me\" to char sequence \"xt\"\n" + "some text information = " + ReverseString.reverse("some text information", first, second));
    }

    public static void reverseStr() throws IOException {
        System.out.print("Input string: ");
        String str = reader.readLine();
        System.out.println(ReverseString.reverse(str));
    }

    public static void reverseSubStr() throws IOException {
        System.out.print("Input string: ");
        String str = reader.readLine();
        System.out.print("Input substring you want to reverse: ");
        String subStr = reader.readLine();
        System.out.println(ReverseString.reverse(str, subStr));
    }

    public static void reverseIndToInd() throws IOException {
        System.out.print("Input string: ");
        String str = reader.readLine();
        System.out.print("Input first index: ");
        int firstInd = Integer.parseInt(reader.readLine());
        System.out.print("Input second index: ");
        int secondInd = Integer.parseInt(reader.readLine());
        System.out.println(ReverseString.reverse(str, firstInd, secondInd));
    }

    public static void reverseCharToChar() throws IOException {
        System.out.print("Input string: ");
        String str = reader.readLine();
        System.out.print("Input first symbol: ");
        char firstCh = reader.readLine().charAt(0);
        System.out.print("Input second symbol: ");
        char secondCh = reader.readLine().charAt(0);
        System.out.println(ReverseString.reverse(str, firstCh, secondCh));
    }

    public static void reverseSubStrToSubStr() throws IOException {
        System.out.print("Input string: ");
        String str = reader.readLine();
        System.out.print("Input first substring: ");
        String firstSubStr = reader.readLine();
        System.out.print("Input second substring: ");
        String secondSubStr = reader.readLine();
        System.out.println(ReverseString.reverse(str, firstSubStr, secondSubStr));
    }

    public static void reverseCharSeqToCharSeq() throws IOException {
        System.out.print("Input string: ");
        String str = reader.readLine();
        System.out.print("Input first char sequence: ");
        CharSequence firstChSeq = reader.readLine();
        System.out.print("Input second char sequence: ");
        CharSequence secondChSeq = reader.readLine();
        System.out.println(ReverseString.reverse(str, firstChSeq, secondChSeq));
    }
}
