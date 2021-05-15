package org.example.level1.task3;

public class Triangle {

    public double getSide(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getArea(int ax, int ay, int bx, int by, int cx, int cy) {
        double ab = getSide(new Point(ax, ay), new Point(bx, by));
        double bc = getSide(new Point(bx, by), new Point(cx, cy));
        double ca = getSide(new Point(cx, cy), new Point(ax, ay));

        double s = (ab + bc + ca) / 2;
        return Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));
    }
}