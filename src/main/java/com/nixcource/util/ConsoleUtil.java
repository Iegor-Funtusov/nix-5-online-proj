package com.nixcource.util;

import com.nixcource.level1.Triangle;
import com.nixcource.level1.UniqueSymbols;
import com.nixcource.level2.ChessBoard;
import com.nixcource.level2.StringVerifier;
import com.nixcource.level3.GameRunner;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConsoleUtil {

    public static void uniqueSymbols(boolean isRandom) {
        ArrayList<Integer> data = new ArrayList<>();
        if (isRandom) {
            Random random = new Random();
            int size = random.nextInt(10);
            for (int i = 0; i < size; ++i) {
                data.add(random.nextInt(10));
            }
            System.out.println(data);
        } else {
            System.out.println("Write a line with numbers separated by comma (for example: 1,2,3,4,5,6)");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            for (var i : userInput.replace(" ", "").split(",")) {
                data.add(Integer.parseInt(i));
            }
        }
        System.out.println(UniqueSymbols.countUniqueSymbolsInArray(data));
    }

    public static void horseMove() {
        ChessBoard board = new ChessBoard(8, 8);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            board.displayBoard();
            System.out.print("""
                    To exit from game write q and press Enter...
                    FIRST WRITE ROW, THEN COLUMN
                    Write coordinate which on you want to horse to move (for example 00):""");
            String userInput = scanner.next();
            System.out.println(userInput);
            if (userInput.equals("q")) {
                break;
            }
            board.move(Character.getNumericValue(userInput.charAt(0)),
                    Character.getNumericValue(userInput.charAt(1)));
        }
    }

    public static void triangleArea(boolean isRandom) {
        Triangle triangle;
        if (isRandom) {
            Random random = new Random();
            int bound = 20;
            triangle = new Triangle(
                    random.nextInt(bound), random.nextInt(bound),
                    random.nextInt(bound), random.nextInt(bound),
                    random.nextInt(bound), random.nextInt(bound)
            );
        } else {
            System.out.println("You need to input dot's coordinates three times...");
            Scanner scanner = new Scanner(System.in);
            ArrayList<Triangle.Dot> dots = new ArrayList<>();
            for (int i = 0; i < 3; ++i) {
                System.out.print("Write dot's coordinates (for example 3,4): ");
                String userInput = scanner.next();
                String coordinates = userInput
                        .replace(" ", "")
                        .replace(",", "");
                Triangle.Dot dot = new Triangle.Dot(
                        Character.getNumericValue(coordinates.charAt(0)),
                        Character.getNumericValue(coordinates.charAt(1)));
                dots.add(dot);
            }
            triangle = new Triangle(dots.get(0), dots.get(1), dots.get(2));
        }
        System.out.println(triangle);
        System.out.println(triangle.calculateArea());
    }

    public static void stringParser(boolean isRandom) {
        Scanner scanner = new Scanner(System.in);
        StringVerifier sv;
        if (isRandom) {
            Random random = new Random();
            char[] pattern = "{}()[]abcdefghijklmnopqrstuvwxyz".toCharArray();
            ArrayList<Character> randomSequence = new ArrayList<>();
            int randomStringLength = random.nextInt(20);
            for (int i = 0; i < randomStringLength; ++i) {
                randomSequence.add(pattern[random.nextInt(pattern.length)]);
            }
            sv = new StringVerifier(randomSequence
                    .toString()
                    .replace(",", "")
                    .replace(" ", ""));
            System.out.println(sv.getData());
        } else {
            System.out.println("Write line. For example: {this : [is, line]}");
            String userInput = scanner.nextLine();
            sv = new StringVerifier(userInput);
        }
        System.out.println(sv.isValid());
    }

    public static void runGame() {
        GameRunner game = new GameRunner(10, 10);
        game.play();
    }

    public static boolean isRandomSelected() {
        Scanner scanner = new Scanner(System.in);
        String description = """
                Specify how you want to run the task (for example: 1)
                1) Random
                2) User input""";
        System.out.println(description);
        int userInput = scanner.nextInt();
        return userInput == 1;
    }

    public static void printTaskDescription() {
        System.out.println("""
                Choose number of one task below:
                1) Find unique element in array
                2) Horse move
                3) Calculate triangle area
                4) Validate string (user input or NOT random pattern)
                5) Play game of life (random only)
                
                To exit write q and press Enter
                Write number of task. For input example: 1""");
        System.out.print("Your choice is: ");
    }
}
