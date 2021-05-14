package com.nixsolutions.courses.utils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class Level1Utils {

    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 100;
    private static final Random random = new Random();

    public static Point[] createPointsOfTriangle(BufferedReader reader) throws IOException {
        System.out.println("Do you want to input triangle points?\n0 - no\n1 - yes");
        Point[] points;
        if(reader.readLine().equals("0")) {
            points = generatePointsOfTriangle();
        } else {
            while(true) {
                System.out.println("Enter point A (x y):");
                String[] a = reader.readLine().split(" ");
                Point A = new Point(Integer.parseInt(a[0]), Integer.parseInt(a[1]));

                System.out.println("Enter point B (x y):");
                String[] b = reader.readLine().split(" ");
                Point B = new Point(Integer.parseInt(b[0]), Integer.parseInt(b[1]));

                System.out.println("Enter point C (x y):");
                String[] c = reader.readLine().split(" ");
                Point C = new Point(Integer.parseInt(c[0]), Integer.parseInt(c[1]));
                if ((A.getX() == B.getX() && A.getX() == C.getX()) || ((A.getY() == B.getY() && A.getY() == C.getY()))) {
                    System.out.println("Not valid triangle");
                } else {
                    points = new Point[]{A, B, C};
                    break;
                }
            }
        }

        return points;
    }

    public static Point[] generatePointsOfTriangle() {
        Point A = new Point(random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN, random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN);
        Point B = new Point(random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN, random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN);
        Point C = new Point(random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN, random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN);
        System.out.println("A: (" + (int)A.getX() + "; " + (int)A.getY() + ")");
        System.out.println("B: (" + (int)B.getX() + "; " + (int)B.getY() + ")");
        System.out.println("C: (" + (int)C.getX() + "; " + (int)C.getY() + ")");
        return new Point[]{A, B, C};
    }

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
        System.out.println("Choose task:\n0 - exit\n1 - count unique elements of array\n2 - knight's move\n3 - count area of triangle");
    }

}
