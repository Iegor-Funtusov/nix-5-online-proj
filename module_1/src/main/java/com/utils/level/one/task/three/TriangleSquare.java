package com.utils.level.one.task.three;

public class TriangleSquare {

    public static double getSquare(int a_x, int a_y, int b_x, int b_y, int c_x, int c_y) {
        double side_a = getSide(a_x, a_y, b_x, b_y);
        double side_b = getSide(b_x, b_y, c_x, c_y);
        double side_c = getSide(a_x, a_y, c_x, c_y);
        double p = (side_a + side_b + side_c) / 2;
        return Math.sqrt(p * (p - side_a) * (p - side_b) * (p - side_c));
    }
    public static double getSide(int a_x, int a_y, int b_x, int b_y) {
        return Math.sqrt(Math.pow(b_x - a_x, 2) + Math.pow(b_y - a_y, 2));
    }
}
