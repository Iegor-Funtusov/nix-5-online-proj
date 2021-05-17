package ua.com.alevel;

public class TriangleUtils {

    public static double calculateArea(Triangle triangle) {
        double area = 0;
        double halfPerimeter;
        double ab = getLength(triangle.getA(), triangle.getB());
        double bc = getLength(triangle.getA(), triangle.getB());
        double ca = getLength(triangle.getA(), triangle.getB());
        halfPerimeter = (ab + bc + ca) / 2;
        area = getArea(halfPerimeter, ab, bc, ca);
        return area;
    }

    private static double getArea(double halfPerimeter, double ab, double bc, double ca){
        return Math.sqrt(halfPerimeter
                * (halfPerimeter - ab)
                * (halfPerimeter - bc)
                * (halfPerimeter - ca));
    }

    private static double getLength(Point a, Point b) {
        int xSub = b.getX() - a.getX();
        int ySub = b.getY() - a.getY();
        return Math.sqrt(xSub * xSub + ySub * ySub);
    }
}
