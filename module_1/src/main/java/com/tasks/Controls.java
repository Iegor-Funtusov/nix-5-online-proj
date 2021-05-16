package com.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import static com.utils.level.one.task.one.UniqueElements.InitArray;
import static com.utils.level.one.task.one.UniqueElements.getAmountOfUnique;
import static com.utils.level.one.task.three.TriangleSquare.getSquare;
import static com.utils.level.one.task.two.KnightMove.Move;
import static com.utils.level.two.StringValidation.CheckValidation;

public class Controls {

    private static final int r_MAX = 100;
    private static final int r_MIN = 0;

    public static BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));

    public static void task1() throws IOException {
        System.out.println("\nLevel 1 task 1");
        int[] array = InitArray(reader);
        System.out.print("Amount of unique elements is: " + getAmountOfUnique(array));
        System.out.println("");
    }

    public static void task2() throws IOException {
        System.out.println("\nLevel 1 task 2");
        System.out.print("Press '0' if you want to write coordinates manually and '1' to generate random values: ");
        int[] position = new int[2];
        int[] move = new int[2];
        if (reader.readLine().equals("0")) {
            System.out.print("Enter the start position of Knight: ");
            String[] pos= reader.readLine().split("\\s+");
            for (int i = 0; i < 2; i++) {
                position[i] = Integer.parseInt(pos[i]);
            }
            System.out.print("Enter the move coordinates of Knight: ");
            pos= reader.readLine().split("\\s+");
            for (int i = 0; i < 2; i++) {
                move[i] = Integer.parseInt(pos[i]);
            }
        } else {
            Random random = new Random();
            for (int i = 0; i < 2; i++) {
                position[i] = random.nextInt(r_MAX - r_MIN) + r_MIN;
                move[i] = random.nextInt(r_MAX - r_MIN) + r_MIN;
            }
            System.out.println("The start Knight position: " + position[0] +" " +position[1]);
            System.out.println("The move coordinates: " + move[0] + " " + move[1]);
        }
        Move(position[0], position[1], move[0], move[1]);
    }

    public static void task3() throws IOException {
        System.out.print("\nLevel 1 task 3\n");
        System.out.print("Press '0' if you want to write coordinates manually and '1' to generate random values: ");
        int size = 6;
        int[] array = new int[size];
        if (reader.readLine().equals("0")) {
            System.out.print("Enter the coordinates in 1 line with space splitting: ");
            String[] values = reader.readLine().split("\\s+");
            for (int i = 0; i < size; i++) {
                array[i] = Integer.parseInt(values[i]);
            }
        } else {
            Random random = new Random();
            for (int i = 0 ; i < size; i++) {
                array[i] = random.nextInt(r_MAX - r_MIN) + r_MIN;
            }
        }
        System.out.println("The pair coordinates of each point: ");
        for (int i = 0; i < size; i = i + 2) {
            System.out.println("(" +array[i] + ", " + array[i+1] + ")" );
        }
        System.out.println("The square of triangle is: " + getSquare(array[0], array[1], array[2],
                array[3], array[4], array[5]));
    }

    public static void task4() throws IOException {
        System.out.print("\nLevel 2 task 1\n");
        System.out.print("Press '0' if you want to string manually and '1' to generate random string: ");
        if (reader.readLine().equals("0")) {
            System.out.print("Enter the string: ");
            String string = reader.readLine();
            System.out.print("String validation: " + CheckValidation(string));
        } else {
            int left_limit = 40;
            int right_limit = 126;
            int string_length = 10;
            Random random = new Random();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < string_length; i++) {
                int randomLimitedInt = left_limit + (int)
                        (random.nextFloat() * (right_limit - left_limit + 1));
                builder.append((char) randomLimitedInt);
            }
            String generated = builder.toString();
            System.out.println("The generated string: " + generated);
            System.out.println("String validation: " + CheckValidation(generated));
        }
    }
}
