package util;

import java.io.IOException;
import java.util.Scanner;

import static level1.task1.UniqueNumber.*;
import static level1.task2.KnightMoveValidator.moveValidator;
import static level1.task3.AreaOfTriangle.areaTriangle;
import static level2.task1.StringGenerator.inputStringByUser;
import static level2.task1.StringGenerator.inputStringRandom;
import static level2.task1.StringValidator.stringValidator;

public class ApplicationManager {
    public static void start() throws IOException {
        System.out.println("Hello User :)");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please select level what you want to check: \n" +
                    "- 1 - Enter 1 and press Enter if you want to check first level \n" +
                    "- 2 - Enter 2 and press Enter if you want to check second level \n" +
                    "- 3 - Enter 3 and press Enter if you want to check third level\n" +
                    "Input any other number for finish session.");

            switch (scanner.next()) {
                case "1": {
                    System.out.println("Level 1 selected!");
                    System.out.println("Please enter number of task what you want to check: \n" +
                            "- 1 - Find quantity of unique numbers in an array \n" +
                            "- 2 - Validate chess Knight move \n" +
                            "- 3 - Find area of triangle by coordinates\n" +
                            "Input any other number for finish session.");
                    switch (scanner.next()) {
                        case "1": {
                            System.out.println("Task 1 selected: Find quantity of unique numbers in an array");
                            System.out.println("Select option to check: \n" +
                                    "- 1 - Enter 1 and press Enter if you want enter array manually \n" +
                                    "- 2 - Enter 2 and press Enter if you want enter random array  \n" +
                                    "Input any other number for finish session.");
                            switch (scanner.next()) {
                                case "1": {
                                    findUnique(makeArrayOfNumbersByUser());
                                }
                                case "2": {
                                    findUnique(makeArrayOfNumbersRandom());
                                }
                                default:
                                    System.exit(0);
                            }
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        case "2": {
                            System.out.println("Task 2 selected: Validate chess Knight move");
                            moveValidator();
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        case "3": {
                            System.out.println("Task 3 selected: Find area of triangle by coordinates");
                            areaTriangle();
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        default:
                            System.exit(0);
                    }
                    break;
                }
                case "2": {
                    System.out.println("Level 2 selected!");
                    System.out.println("Please enter number of option what you want to check: \n" +
                            "- 1 - Check 1 task: Check the correct sequence of brackets into string \n" +
                            "Input any other number for finish session.");
                    switch (scanner.next()) {
                        case "1": {
                            System.out.println("Task 1 selected: Check the correct sequence of brackets into string");
                            System.out.println("Select option to check: \n" +
                                    "- 1 - Enter 1 and press Enter if you want enter string manually \n" +
                                    "- 2 - Enter 2 and press Enter if you want see random string  \n" +
                                    "Input any other number for finish session.");
                            switch (scanner.next()) {
                                case "1": {
                                    stringValidator(inputStringByUser());
                                    break;
                                }
                                case "2": {
                                    stringValidator(inputStringRandom());
                                    break;
                                }
                                default:
                                    System.exit(0);
                            }
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        default:
                            System.exit(0);
                    }
                    System.out.println("___________________________________________________\n");
                    break;
                }
                case "3": {
                    System.out.println("Sorry :( This part in progress");
                    System.out.println("___________________________________________________\n");
                    break;
                }
                default:
                    System.exit(0);
            }
        }
    }
}
