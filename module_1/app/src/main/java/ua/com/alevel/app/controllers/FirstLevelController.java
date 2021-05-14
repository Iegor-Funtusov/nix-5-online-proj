package ua.com.alevel.app.controllers;

import ua.com.alevel.lib.KnightTurnProcess;
import ua.com.alevel.lib.TriangleAreaFinder;
import ua.com.alevel.lib.UniqueSymbolsSearcher;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FirstLevelController {

    private static BufferedReader reader;

    public static void firstLevelMenu() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        try {
            System.out.println("\nChoose the level:\n" +
                    "1 -> Count unique elements in array\n" +
                    "2 -> Knight turn validator\n" +
                    "3 -> Find triangle area\n" +
                    "0 -> Back to main menu");
            while ((input = reader.readLine())!= null) {
                switch (input) {
                    case "1" : {
                        findUnique();
                    } break;
                    case "2" : {
                        knightTurnIsValid();
                    } break;
                    case "3" : {
                        findTriangleArea();
                    } break;
                    case "0": {
                        return;
                    }
                    default: {
                        System.out.println("Wrong input");
                    }break;
                }
                System.out.println("\nChoose the level:\n" +
                        "1 -> Count unique elements in array\n" +
                        "2 -> Knight turn validator\n" +
                        "3 -> Find triangle area\n" +
                        "0 -> Back to main menu");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findUnique() throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Choose the way of input:\n" +
                "1 -> Do it by yourself\n" +
                "2 -> Random input");
        String input = reader.readLine();

        if (input.equals("1")) {
            System.out.print("Input array size: ");
            int size = s.nextInt();
            int[] arr = new int[size];
            System.out.print("Input array elements: ");
            for (int i = 0; i < size; i++) {
                int el = s.nextInt();
                arr[i] = el;
            }
            System.out.print("Unique symbols count: " + UniqueSymbolsSearcher.countUnique(arr));
        }
        else if (input.equals("2")) {
            System.out.print("Input array size: ");
            int size = s.nextInt();
            int[] arr = new int[size];
            System.out.print("Array elements: ");
            for (int i = 0; i < size; i++) {
                arr[i] = (int) Math.round((Math.random() * 100) - 50);
                System.out.print(arr[i] + " ");
            }
            System.out.print("\nUnique symbols count: " + UniqueSymbolsSearcher.countUnique(arr));
        }
    }

    public static void knightTurnIsValid() {
        Scanner s = new Scanner(System.in);
        System.out.println("Input knight beginning kords:");
        System.out.print("x: ");
        int x = s.nextInt();
        System.out.print("y: ");
        int y = s.nextInt();
        System.out.println("Input knight destination kords:");
        System.out.print("x: ");
        int xDest = s.nextInt();
        System.out.print("y: ");
        int yDest = s.nextInt();
        System.out.println("Is turn valid: " + KnightTurnProcess.move(x,y,xDest,yDest));
    }

    public static void findTriangleArea() throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Choose the way of input:\n" +
                "1 -> Do it by yourself\n" +
                "2 -> Random input");
        String input = reader.readLine();

        if (input.equals("1")) {
            System.out.println("Input A kords");
            System.out.print("x: ");
            int ax = s.nextInt();
            System.out.print("y: ");
            int ay = s.nextInt();
            System.out.println("Input B kords");
            System.out.print("x: ");
            int bx = s.nextInt();
            System.out.print("y: ");
            int by = s.nextInt();
            System.out.println("Input C kords");
            System.out.print("x: ");
            int cx = s.nextInt();
            System.out.print("y: ");
            int cy = s.nextInt();
            Point a = new Point(ax, ay);
            Point b = new Point(bx, by);
            Point c = new Point(cx, cy);
            System.out.println("Area of a triangle: " + TriangleAreaFinder.calculateArea(a,b,c));
        }
        else if (input.equals("2")) {
            Point a = new Point((int) Math.round((Math.random() * 100) - 50),
                    (int) Math.round((Math.random() * 100) - 50));
            Point b = new Point((int) Math.round((Math.random() * 100) - 50),
                    (int) Math.round((Math.random() * 100) - 50));
            Point c = new Point((int) Math.round((Math.random() * 100) - 50),
                    (int) Math.round((Math.random() * 100) - 50));
            System.out.println("Point A: " + a.x + " " + a.y);
            System.out.println("Point B: " + b.x + " " + b.y);
            System.out.println("Point C: " + c.x + " " + c.y);
            System.out.println("Area of a triangle: " + TriangleAreaFinder.calculateArea(a,b,c));
        }
    }
}
