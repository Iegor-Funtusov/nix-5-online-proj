package lev1.triangle;

import java.awt.*;

public class TriangleSq {



    public static void Square(Point a, Point b, Point c) {
        double sq = Math.abs(((a.getX()-c.getX())*(b.getY()-a.getY())-
                (a.getX()-b.getX())*(c.getY()-a.getY()))*0.5);
        String strResult = Double.toString(sq);
        System.out.println("Square = "+sq);
    }


}
