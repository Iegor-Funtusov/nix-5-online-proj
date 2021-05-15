package ua.com.nkrasnovoronka.tasks.level1.task3;

import java.awt.*;

public class TriangleArea {
    public static double getTriangleArea(Point a, Point b, Point c) {
        double by = b.getY() - c.getY();
        double ca = c.getY() - a.getY();
        double ab = a.getY() - b.getY();
        return Math.abs((a.getX() * by + b.getX() * ca + c.getX() * ab) / 2);
    }
}
