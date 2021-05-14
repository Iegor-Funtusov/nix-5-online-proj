package com.nixsolutions.courses.utils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class Level1Utils {

    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 100;
    private static final Random random = new Random();

    public static Point createPoint(BufferedReader reader) throws IOException {
        System.out.println("Do you want to input position?\n0 - no\n1 - yes");
        Point current;
        if (reader.readLine().equals("0")) {
            current = generatePoint();
        } else {
            System.out.println("Enter x:");
            int x = Integer.parseInt(reader.readLine());
            if(Math.abs(x) > RANDOM_MAX) {
                x = x % RANDOM_MAX;
            }
            System.out.println("Enter y:");
            int y = Integer.parseInt(reader.readLine());
            if(Math.abs(y) > RANDOM_MAX) {
                y = y % RANDOM_MAX;
            }
            current = new Point(x,y);
        }
        return current;
    }

    public static Point generatePoint() {
        int x = random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN;
        int y = random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN;

        return new Point(x,y);
    }

    public static int[] createArray(BufferedReader reader) throws IOException {
        System.out.println("Do you want to input array?\n0 - no\n1 - yes");
        int[] array;
        if (reader.readLine().equals("0")) {
            array = generateArray();
        } else {
            System.out.println("Enter size of array:");
            array = new int[Integer.parseInt(reader.readLine())];
            System.out.println("Enter array elements space-separated:");
            String[] arr = reader.readLine().split(" ");
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt(arr[i]);
            }
        }
        return array;
    }

    public static int[] generateArray() {
        int n = random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN;
        System.out.println("Size of array:\n" + n);
        System.out.println("Array of random numbers:");
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN;
            System.out.print(array[i] + " ");
        }
        System.out.println("");
        return array;
    }

    public static void printOptions() {
        System.out.println("Choose task:\n0 - exit\n1 - count unique elements of array\n2 - knight's move");
    }

}
