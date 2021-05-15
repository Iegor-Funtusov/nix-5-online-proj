package com.nixsolutions.courses.app;

import com.nixsolutions.courses.lib.ReverseString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ReverseController {

    public static String reverseWithBoundaries(String src, BufferedReader reader) throws IOException {
        System.out.println("Enter start/end in:\n1 - indexes\n2 - chars\n3 - strings");
        String option = reader.readLine();
        System.out.println("Enter space-separated not included boundaries in chosen form(index from 0):");
        StringTokenizer in = new StringTokenizer(reader.readLine(), " ");
        switch (option) {
            case "1":
                return ReverseString.reverse(src, Integer.parseInt(in.nextToken()), Integer.parseInt(in.nextToken()));
            case "2":
                return ReverseString.reverse(src, in.nextToken().charAt(0), in.nextToken().charAt(0));
            case "3":
                return ReverseString.reverse(src, in.nextToken(), in.nextToken());
            default:
                System.out.println("Wrong option");
        }
        return null;
    }

    public static void printOptions() {
        System.out.println("Choose option:\n0 - exit\n1 - reverse the whole string\n2 - reverse the substring\n3 - reverse the substring defined by indexes (chars, strings)");
    }

    public static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string;
        try {
            while (true) {
                System.out.println("Enter your string:");
                string = reader.readLine();
                while (string != null) {
                    System.out.println("Input string: " + string);
                    printOptions();
                    switch (reader.readLine()) {
                        case "0":
                            string = null;
                            break;
                        case "1":
                            System.out.println("Result string:\n" + ReverseString.reverse(string));
                            break;
                        case "2":
                            System.out.println("Enter the substring to reverse:");
                            System.out.println("Result string:\n" + ReverseString.reverse(string, reader.readLine()));
                            break;
                        case "3":
                            System.out.println("Result string:\n" + reverseWithBoundaries(string, reader));
                            break;
                        default:
                            System.out.println("Wrong option");
                    }
                }

                System.out.println("Do you want to quit?\n0 - no\n1 - yes");
                if (reader.readLine().equals("1")) {
                    System.exit(0);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
