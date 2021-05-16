package com.nixcource.util;

import com.nixcource.level1.Triangle;
import com.nixcource.level1.UniqueSymbols;
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
        } else {
            System.out.println("Write a line with numbers separated by comma (for example: 1,2,3,4,5,6)");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            for (var i : userInput.replace(" ", "").split(",")) {
                data.add(Integer.parseInt(i));
            }
        }
        System.out.println(data);
        System.out.println(UniqueSymbols.countUniqueSymbolsInArray(data));
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

    public static void stringParser() {
        StringVerifier sv = new StringVerifier("{test, [what, the, hell]}");
        System.out.println(sv.getData());
        System.out.println(sv.isValid());
    }

    public static void runGame() {
        GameRunner game = new GameRunner(10, 10);
        game.play();
    }

    public static boolean isRandomSelected() {
        Scanner scanner = new Scanner(System.in);
        String description = """
                Specify how you want to run task (for example: 1)
                1) Random
                2) User input""";
        System.out.println(description);
        int userInput = scanner.nextInt();
        return userInput == 1;
    }

    public static void printTaskDescription() {
        System.out.println("""
                Write number of one task below:
                1) Find unique element in array
                2) Calculate triangle area
                3) Validate string
                4) Play game of life
                
                To exit write q and press Enter""");
    }
}
