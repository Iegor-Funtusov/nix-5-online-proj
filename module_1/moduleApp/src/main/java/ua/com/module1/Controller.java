package ua.com.module1;

import ua.com.module1.service.*;

import java.io.IOException;
import java.util.Scanner;

public class Controller {

    private static final Scanner scanner = new Scanner(System.in);

    public static void cycle() throws IOException {
        boolean checker = true;
        while(checker) {
            System.out.println("Would you like to continue? y/n");
            String result = scanner.next();
            switch (result){
                case "y": {
                    Controller.chosenMethod();
                    cycle();
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

    public static void chosenMethod() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean checker = true;
        while (checker) {
            System.out.println("Choose the function: ");
            System.out.println("1 -> Get unique numbers");
            System.out.println("2 -> Check knight step");
            System.out.println("3 -> Get triangle area");
            System.out.println("4 -> Check string");
            System.out.println("5 -> Play game");
            String result = scanner.next();
            switch (result) {
                case "1": {
                    scanner.nextLine();
                    while(true) {
                        System.out.println("Would you like to enter array (1) or no (2)");
                        int res = scanner.nextInt();
                        if (res == 1){
                            UniqueNumbers.getUniqueNumber();
                            break;
                        }
                        else if (res == 2) {
                            UniqueNumbers.getUniqueNumberAuto();
                            break;
                        }
                        else {
                            System.out.println("Please, enter your chiose");
                            continue;
                        }
                    }
                    checker = false;
                    break;
                }
                case "2": {
                    scanner.nextLine();
                    while(true) {
                        System.out.println("Would you like to enter coordinate (1) or no (2)");
                        int res = scanner.nextInt();
                        if (res == 1){
                            KnightStep.getKnightStep();
                            break;
                        }
                        else if (res == 2) {
                            KnightStep.getKnightStepAuto();
                            break;
                        }
                        else {
                            System.out.println("Please, enter your chiose");
                            continue;
                        }
                    }
                    checker = false;
                    break;
                }
                case "3": {
                    scanner.nextLine();
                    while(true) {
                        System.out.println("Would you like to enter coordinate (1) or no (2)");
                        int res = scanner.nextInt();
                        if (res == 1){
                            TriangleArea.getTriangleArea();
                            break;
                        }
                        else if (res == 2) {
                            TriangleArea.getTriangleAreaAuto();
                            break;
                        }
                        else {
                            System.out.println("Please, enter your chiose");
                            continue;
                        }
                    }
                    checker = false;
                    break;
                }
                case "4": {
                    scanner.nextLine();
                    while(true) {
                        System.out.println("Would you like to enter string (1) or no (2)");
                        int res = scanner.nextInt();
                        if (res == 1){
                            CheckerString.getStingChecker();
                            break;
                        }
                        else if (res == 2) {
                            CheckerString.getStingCheckerAuto();
                            break;
                        }
                        else {
                            System.out.println("Please, enter your chiose");
                            continue;
                        }
                    }
                    checker = false;
                    break;
                }
                case "5": {
                    PlayLifeGame.getBoard();
                    break;
                }
                default: {
                    System.out.println("Please, make Your choice!");
                }
            }
        }
    }
}
