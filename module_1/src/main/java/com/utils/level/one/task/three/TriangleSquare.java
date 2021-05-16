package com.utils.level.one.task.three;

public class TriangleSquare {

    public static double getSuare(int a, int b, int c) {
        double side_a = getSide(a, b);
        double side_b = getSide(b, c);
        double side_c = getSide(a, c);
        double p = (side_a + side_b + side_c) / 2;
        return Math.sqrt(p * (p - side_a) * (p - side_b) * (p - side_c));
    }
    public static double getSide(int a, int b) {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }
}
