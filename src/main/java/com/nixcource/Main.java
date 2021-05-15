package com.nixcource;

import com.nixcource.level1.UniqueSymbols;
import com.nixcource.level1.Triangle;

public class Main {
    public static void main(String[] args) {
        int[] data = { 1, 4, 5, 1, 1, 3 };
        System.out.println(UniqueSymbols.countUniqueSymbolsInArray(data));

        Triangle.Dot firstDot = new Triangle.Dot(4, 3);
        Triangle.Dot secondDot = new Triangle.Dot(5, 3);
        Triangle.Dot thirdDot = new Triangle.Dot(2, 0);

        Triangle triangle = new Triangle(firstDot, secondDot, thirdDot);
        System.out.println(triangle.calculateArea());
    }
}
