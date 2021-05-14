package com.nixsolutions.firsttask;

import java.awt.*;

public class TriangleAreaCalculator {

    // task 1.3
    public static String findTriangleArea(Point a, Point b, Point c) {
        double result = Math.abs(((a.getX()-c.getX())*(b.getY()-a.getY())-
                (a.getX()-b.getX())*(c.getY()-a.getY()))*0.5);
        String strResult = Double.toString(result);
        System.out.println("Area of a triangle = " + result);
        return strResult;
    }
}
