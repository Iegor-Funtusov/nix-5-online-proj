package ua.com.module1.service;

import ua.com.module1.DetermineTriangleArea;
import ua.com.module1.entity.TriangleDot;

import java.util.Scanner;

public class TriangleArea {
    public static void getTriangleArea(){
        TriangleDot a = new TriangleDot();
        TriangleDot b = new TriangleDot();
        TriangleDot c = new TriangleDot();
        setCoordinate(a);
        setCoordinate(b);
        setCoordinate(c);
        printCoordinate(a, b, c);
        double S = DetermineTriangleArea.getArea(a, b, c);
        System.out.println("Area is " + S);
    }

    public static void setCoordinate(TriangleDot triangleDot){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter x: ");
        triangleDot.setX(scanner.nextInt());
        System.out.println("Enter y: ");
        triangleDot.setY(scanner.nextInt());
    }

    public static void getTriangleAreaAuto() {
        TriangleDot a = new TriangleDot();
        TriangleDot b = new TriangleDot();
        TriangleDot c = new TriangleDot();
        printCoordinate(a, b, c);
        double S = DetermineTriangleArea.getArea(a, b, c);
        System.out.println("Area is " + S);
    }

    public static void printCoordinate(TriangleDot a, TriangleDot b, TriangleDot c){
        System.out.println("a = (" + a.getX() + ";" +  a.getY() + ")" +
                " b = (" + b.getX() + ";" +  b.getY() + ") " +
                "c = (" + c.getX() + ";" +  c.getY() + ") ");
    }
}
