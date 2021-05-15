package com.nixcource.level1;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Triangle {
    private Dot first;
    private Dot second;
    private Dot third;

    public Triangle(Dot first, Dot second, Dot third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public float calculateArea() {
        ArrayList<Integer> matrix = new ArrayList<>();
        float resultArea;

        matrix.add(first.x - third.x);
        matrix.add(first.y - third.y);
        matrix.add(second.x - third.x);
        matrix.add(second.y - third.y);

        resultArea = matrix.get(0) * matrix.get(3) - matrix.get(1) * matrix.get(2);

        return abs(resultArea * 1/2);
    }

    public static record Dot(int x, int y) {

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
