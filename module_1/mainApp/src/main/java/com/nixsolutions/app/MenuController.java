package com.nixsolutions.app;

import com.nixsolutions.firsttask.ChessRunner;
import com.nixsolutions.secondtask.StringValidator;
import com.nixsolutions.firsttask.TriangleAreaCalculator;
import com.nixsolutions.firsttask.UniqueCounter;
import com.nixsolutions.*;

import java.awt.*;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class MenuController {

    public static void runMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean cycle = true;
        //boolean internalCycle = true;

        while (cycle) {
            System.out.println("Please, choose and input the number of Level from 1 to 3 or input 4 to Exit");
            System.out.println("Level 1: Unique Elements Counter, Chess, Triangle Square Calculator");
            System.out.println("Level 2: String validation");
            System.out.println("Level 3: Game of Life");
            System.out.println("4 -> exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: {
                    boolean internalCycle = true;
                    while (internalCycle) {
                        System.out.println("Please, choose Task from 1 to 3 or input 4 to Exit");
                        System.out.println(" 1. Unique Elements Counter"
                                + System.lineSeparator() + " 2. Chess"
                                + System.lineSeparator() + " 3. Triangle Square Calculator"
                                + System.lineSeparator() + "4 -> exit");
                        int internalChoice = scanner.nextInt();
                        switch (internalChoice) {
                            case 1: {
                                System.out.println("Fill the array automatically(input 1) or manually(input 2)?");
                                int fillChoice = scanner.nextInt();

                                if (fillChoice == 1) {
                                    System.out.println("Please, input size of Array :");
                                    int arraySize = scanner.nextInt();
                                    int[] array = new int[arraySize];
                                    for (int i = 0; i < array.length; i++) {
                                        array[i] = (int) Math.round((Math.random() * 60) - 30);
                                        System.out.println(array[i]);
                                    }
                                    UniqueCounter.uniqueCount(array);
                                } else if (fillChoice == 2) {
                                    System.out.println("Please, input size of Array :");
                                    int arraySize = scanner.nextInt();
                                    int[] array = new int[arraySize];
                                    System.out.println("Please, input " + arraySize + " elements" +
                                            System.lineSeparator() + "(use Enter after each): ");
                                    for (int i = 0; i < arraySize; i++) {
                                        int arrayElement = scanner.nextInt();
                                        array[i] = arrayElement;
                                    }
                                    UniqueCounter.uniqueCount(array);
                                }
                                break;
                            }
                            case 2: {
                                ChessRunner.runChess();
                                break;
                            }
                            case 3: {
                                System.out.println("To calculate the square of triangle you need to input"
                                        + System.lineSeparator() + " the coordinates of three points on the plane"
                                        + System.lineSeparator() + "(use Enter after each): ");

                                System.out.println("Please, input point ax");
                                int ax = scanner.nextInt();
                                System.out.println("Please, input point ay");
                                int ay = scanner.nextInt();
                                Point a = new Point(ax, ay);

                                System.out.println("Please, input point bx");
                                int bx = scanner.nextInt();
                                System.out.println("Please, input point by");
                                int by = scanner.nextInt();
                                Point b = new Point(bx, by);

                                System.out.println("Please, input point cx");
                                int cx = scanner.nextInt();
                                System.out.println("Please, input point cy");
                                int cy = scanner.nextInt();
                                Point c = new Point(cx, cy);
                                TriangleAreaCalculator.findTriangleArea(a, b, c);
                                break;
                            }
                            case 4: {
                                internalCycle = false;
                                break;
                            }
                            default: {
                                System.out.println("Please, enter number from 1 to 4 to make a choice : ");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    Map<Character, Character> brackets = new Hashtable<>();
                    brackets.put(']', '[');
                    brackets.put('}', '{');
                    brackets.put(')', '(');
                    Scanner s = new Scanner(System.in);
                    System.out.println("Please, input a string and press Enter: ");
                    StringValidator.isBalanced(s.nextLine(), brackets);
                    System.out.println(" ");
                    break;
                }
                case 3: {
                    GameOfLifeRunner.PrintGame();
                    break;
                }
                case 4: {
                    cycle = false;
                    break;
                }
                default: {
                    System.out.println("Please, enter number from 1 to 5 to make a choice: ");
                    break;
                }
            }
        }
    }
}