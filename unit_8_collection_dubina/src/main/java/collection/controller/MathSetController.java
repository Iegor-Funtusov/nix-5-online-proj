package collection.controller;

import collection.service.MathSet;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MathSetController {

    public static String EXCEPTION_MESSAGE = "Something went wrong! Try again!";
    public static String SEPARATOR = "_____________________________________________________";
    private static MathSet<Integer> mathSet = new MathSet<>();


    public static void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            actionsList();
            String input = scanner.nextLine();
            switch (input) {
                case "1": {
                    fill();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "2": {
                    add();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "3": {
                    sort();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "4": {
                    get();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "5": {
                    getMax();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "6": {
                    getMin();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "7": {
                    getAverage();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "8": {
                    getMedian();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "9": {
                    squash();
                    System.out.println(SEPARATOR);
                    break;
                }
                case "10": {
                    clear();
                    System.out.println(SEPARATOR);
                    break;
                }
                default: {
                    System.out.println("Thank you for using this app!");
                    System.exit(0);
                }
            }
        }
    }

    public static void actionsList() {
        System.out.println(
                "Please select next number for navigating to menu option (use Enter for selecting):  " +
                        "\n 1 - Fill MathSet by random numbers" +
                        "\n 2 - Add digit to MathSet" +
                        "\n 3 - Sort MathSet" +
                        "\n 4 - Get digit from MathSet" +
                        "\n 5 - Get Max value from MathSet" +
                        "\n 6 - Get Min value from MathSet" +
                        "\n 7 - Get Average value from MathSet" +
                        "\n 8 - Get Median value from MathSet" +
                        "\n 9 - Squash MathSet from first to last index" +
                        "\n 10 - Clear MathSet" +
                        "\n 0 - Exit from application"
        );
    }

    public static void fill() {
        Random randomFill = new Random();
        int capacity = 5 + randomFill.nextInt(11);
        mathSet = new MathSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            mathSet.add(randomFill.nextInt(100));
        }
        System.out.println("MathSet created:\n" + Arrays.toString(mathSet.toArray()));
    }

    public static void add() {
        System.out.println("Enter digits what you want to add: (use space between numbers)");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int digit;
        for (int i = 0; i < input.length; i++) {
            try {
                digit = Integer.parseInt(input[i]);
                mathSet.add(digit);
            } catch (NumberFormatException e) {
                System.out.println(EXCEPTION_MESSAGE);
            }
        }
        System.out.println("Numbers added.\nNew MathSet: " + Arrays.toString(mathSet.toArray()));
    }

    public static void sort() {
        System.out.println("Please enter option how you want to sort MathSet.");
        System.out.println("1 - Sort all MathSet\n" +
                "2 - Sort from first number to entered number\n" +
                "3 - Sort between two digits\n" +
                "  - Any other digit for back to main menu");
        Scanner scanner = new Scanner(System.in);
        int sort = scanner.nextInt();

        System.out.println("How do you want to sort MathSet?\n 1 - ascending order" +
                "\n 2 - descending order ");
        int order = scanner.nextInt();

        switch (sort) {
            case 1: {
                if (order == 1) {
                    mathSet.sortAsc();
                } else {
                    mathSet.sortDesc();
                }
                System.out.println("MathSet is sorted.\n" + Arrays.toString(mathSet.toArray()));
                return;
            }
            case 2: {
                System.out.println("Please enter number: ");
                int number = scanner.nextInt();
                if (order == 1) {
                    mathSet.sortAsc(number);
                } else {
                    mathSet.sortDesc(number);
                }
                System.out.println("MathSet is sorted.\n" + Arrays.toString(mathSet.toArray()));
                return;
            }
            case 3: {
                System.out.println("Please enter first number: ");
                int firstIndex = scanner.nextInt();
                System.out.println("Please enter second number: ");
                int lastIndex = scanner.nextInt();

                if (order == 1) {
                    mathSet.sortAsc(firstIndex, lastIndex);
                } else {
                    mathSet.sortDesc(firstIndex, lastIndex);
                }
                System.out.println("MathSet is sorted.\n" + Arrays.toString(mathSet.toArray()));
                return;
            }
            default: {
                start();
            }
        }
    }

    public static void get() {
        System.out.println("Please enter an index what do you want to check:");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        int result = mathSet.get(index);
        if (result != 0) {
            System.out.println("Number with index " + index + " is " + result);
        } else {
            System.out.println(EXCEPTION_MESSAGE + "\nThis number doesn't exist.");
        }
    }

    public static void getMax() {
        if (mathSet.size() == 0) {
            System.out.println(EXCEPTION_MESSAGE + "\nMathSet doesn't exist.");
            return;
        }
        Integer max = mathSet.getMax();
        System.out.println("Max value is = " + max);
    }

    private static void getMin() {
        if (mathSet.size() == 0) {
            System.out.println(EXCEPTION_MESSAGE + "\nMathSet doesn't exist.");
            return;
        }
        Integer min = mathSet.getMin();
        System.out.println("Min value is = " + min);
    }

    private static void getAverage() {
        if (mathSet.size() == 0) {
            System.out.println(EXCEPTION_MESSAGE + "\nMathSet doesn't exist.");
            return;
        }
        Double avg = mathSet.getAverage();
        System.out.println("Average value is = " + avg);
    }

    private static void getMedian() {
        if (mathSet.size() == 0) {
            System.out.println(EXCEPTION_MESSAGE + "\nMathSet doesn't exist.");
            return;
        }
        Double median = mathSet.getMedian();
        System.out.println("Median value is = " + median);
    }

    private static void squash() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter first index: ");
        int firstIndex = scanner.nextInt();

        System.out.println("Please enter second index: ");
        int lastIndex = scanner.nextInt();

        MathSet<Integer> mathSet1 = mathSet.squash(firstIndex, lastIndex);
        if (mathSet1 == null) {
            System.out.println(EXCEPTION_MESSAGE + "\nMathSet doesn't exist.");
            return;
        }
        mathSet = mathSet1;
        System.out.println("Squash MathSet contains: " + Arrays.toString(mathSet.toArray()));
    }

    private static void clear() {
        mathSet.clear();
        System.out.println("MathSet cleared.");
    }
}
