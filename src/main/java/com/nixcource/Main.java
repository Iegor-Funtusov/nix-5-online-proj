package com.nixcource;

import com.nixcource.level1.Triangle;
import com.nixcource.level1.UniqueSymbols;
import com.nixcource.level2.StringVerifier;
import com.nixcource.level3.GameRunner;

public class Main {

    public static void main(String[] args) {
        // LEVEL 1
        // first task
        int[] data = { 1, 4, 5, 1, 1, 3 };
        System.out.println(UniqueSymbols.countUniqueSymbolsInArray(data));

        // third task
        Triangle.Dot firstDot = new Triangle.Dot(4, 3);
        Triangle.Dot secondDot = new Triangle.Dot(5, 3);
        Triangle.Dot thirdDot = new Triangle.Dot(2, 0);

        Triangle triangle = new Triangle(firstDot, secondDot, thirdDot);
        System.out.println(triangle.calculateArea());

        // LEVEL 2
        // first task
        StringVerifier sv = new StringVerifier("{test, [what, the, hell]}");
        System.out.println(sv.isValid());

        sv.setData("");
        System.out.println(sv.isValid());
        sv.setData("[[{)]]");
        System.out.println(sv.isValid());

        // LEVEL 3
        GameRunner game = new GameRunner(10, 10);
        game.play();
    }
}
