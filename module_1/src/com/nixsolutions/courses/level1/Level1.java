package com.nixsolutions.courses.level1;

import com.nixsolutions.courses.utils.Level1Utils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Level1 {

    static BufferedReader reader;

    public static void triangleArea() throws IOException {
        Point[] points = Level1Utils.createPointsOfTriangle(reader);
        double area = Math.abs((points[0].getX() * (points[1].getY() - points[2].getY()) + points[1].getX() * (points[2].getY() - points[0].getY()) + points[2].getX() * (points[0].getY() - points[1].getY())) / 2);
        System.out.println("Area of triangle:\n" + area);
    }

    public static void knightMove() throws IOException {
        System.out.println("Current position");
        Point current = Level1Utils.createPoint(reader);
        while(true) {
            System.out.println("Current position is x=" + (int)current.getX() + " y=" + (int)current.getY());
            System.out.println("Move to position");
            Point moveTo = Level1Utils.createPoint(reader);
            System.out.println("Move to x=" + (int)moveTo.getX() + " y=" + (int)moveTo.getY());
            int x = Math.abs((int) current.getX() - (int) moveTo.getX());
            int y = Math.abs((int) current.getY() - (int) moveTo.getY());

            if (x * y == 2) {
                System.out.println("Knight moved");
                current.setLocation(moveTo);
            } else {
                System.out.println("Not valid move");
            }
            System.out.println("Continue?\n0 - no\n1 - yes");
            if (reader.readLine().equals("0")) {
                break;
            }
        }

    }

    public static void countUniqueElements() throws IOException {
        int count = 0;
        int[] array = Level1Utils.createArray(reader);
        int max = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        int[] countElementsRepeat = new int[max+1];
        for (int i : array) {
            countElementsRepeat[i]++;
        }
        for (int repeats : countElementsRepeat) {
            if(repeats != 0) {
                count++;
            }
        }
        System.out.println("Number of unique elements:\n" + count);
    }

    public static void run() {
        System.out.println("Level1.run");
        reader = new BufferedReader((new InputStreamReader(System.in)));
        Level1Utils.printOptions();
        String input;
        try {
            while (!(input = reader.readLine()).equals("0")) {

                switch(input) {
                    case "1":
                        countUniqueElements();
                        break;
                    case "2":
                        knightMove();
                        break;
                    case "3":
                        triangleArea();
                        break;
                }
                Level1Utils.printOptions();
            }
        } catch(IOException e) {
            System.out.println("Something went wrong");
        }
    }

}
