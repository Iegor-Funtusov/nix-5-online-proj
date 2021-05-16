package com.nixcource.level1;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Triangle {
    private final Dot first;
    private final Dot second;
    private final Dot third;

    public Triangle(Dot first, Dot second, Dot third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Triangle(int firstX, int firstY, int secondX, int secondY, int thirdX, int thirdY) {
        this.first = new Dot(firstX, firstY);
        this.second = new Dot(secondX, secondY);
        this.third = new Dot(thirdX, thirdY);
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

    public static record Dot(int x, int y) {}

    @Override
    public String toString() {
        return String.format("{%s, %s}, {%s, %s}, {%s, %s}",
                first.x, first.y,
                second.x, second.y,
                third.x, third.y);
    }
}
