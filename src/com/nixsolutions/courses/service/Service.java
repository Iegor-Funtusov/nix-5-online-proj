package com.nixsolutions.courses.service;

import com.nixsolutions.courses.utils.ArithmeticUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Service {

    public static void printOptions() {
        System.out.println("\n0 - exit\n1 - find even numbers\n2 - count positive numbers\n"
                + "3 - count elements greater than previous\n4 - count elements that have two smaller neighbors\n"
                + "5 - reverse order\n6 - swap neighbors");
    }

    public static void main(String[] args) {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose option:");
        printOptions();
        String read;
        try {
            while ((read = input.readLine()) != null) {
                switch (read) {
                    case "0":
                        System.exit(0);
                        break;
                    case "1":
                        ArithmeticUtils.printEvenNumbers();
                        break;
                    case "2":
                        ArithmeticUtils.countPositiveNumbers();
                        break;
                    case "3":
                        ArithmeticUtils.countNextGreater();
                        break;
                    case "4":
                        ArithmeticUtils.countWithSmallerNeighbors();
                        break;
                    case "5":
                        ArithmeticUtils.reverseOrder();
                        break;
                    case "6":
                        ArithmeticUtils.swapNeighbors();
                        break;
                }
                printOptions();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
