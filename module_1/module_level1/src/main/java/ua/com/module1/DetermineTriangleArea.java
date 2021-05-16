package ua.com.module1;

import ua.com.module1.entity.TriangleDot;

public class DetermineTriangleArea {
    public static double getArea(TriangleDot a, TriangleDot b,
                                 TriangleDot c ){
        double [][] matrix = new double[2][2];
        matrix[0][0] = a.getX() - c.getX();
        matrix[0][1] = a.getY() - c.getY();
        matrix[1][0] = b.getX() - c.getX();
        matrix[1][1] = b.getY() - c.getY();
        double determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        return Math.abs(determinant/2);
    }
}
