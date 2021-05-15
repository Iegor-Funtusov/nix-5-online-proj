package ua.com.nkrasnovoronka.tests.level1.task3;

import ua.com.nkrasnovoronka.tasks.level1.task3.TriangleArea;
import ua.com.nkrasnovoronka.util.UserInput;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class TriangleAreaTest {
    public static void randomTriangleAreaTest(){
        System.out.println("Starting random triangle area test");
        Random random = new Random();
        Point a = new Point(random.nextInt(20) + 1, random.nextInt(20) + 1);
        Point b = new Point(random.nextInt(20) + 1, random.nextInt(20) + 1);
        Point c = new Point(random.nextInt(20) + 1, random.nextInt(20) + 1);
        double triangleArea = TriangleArea.getTriangleArea(a, b, c);
        System.out.printf("Area of triangle %s %s %s = %.0f%n", a, b, c, triangleArea);
    }

    public static void userInputTriangleTest(){
        System.out.println("Starting user input triangle area test");
        System.out.println("Enter exactly 6 numbers like 1 2 3 5 6 1\nwere 1 2 is A point, 3 5 is B point, 6 1 is C point");
        List<Integer> integers = UserInput.userInputNumbers();
        if(integers.size() != 6){
            throw new IllegalArgumentException("You must enter 6 numbers only. Pleas restart program");
        }
        Point a = new Point(integers.get(0), integers.get(1));
        Point b = new Point(integers.get(2), integers.get(3));
        Point c = new Point(integers.get(4), integers.get(5));
        double triangleArea = TriangleArea.getTriangleArea(a, b, c);
        System.out.printf("Area of triangle %s %s %s = %.0f%n", a, b, c, triangleArea);
    }
}
