package ua.com.alevel.lib;

import java.awt.*;

public class TriangleAreaFinder {

    public static double calculateArea(Point a, Point b, Point c) {
        return Math.abs(((a.getX() - c.getX()) *
                        (b.getY() - a.getY()) -
                        (a.getX() - b.getX()) *
                        (c.getY() - a.getY())) *
                        0.5);
    }
}
