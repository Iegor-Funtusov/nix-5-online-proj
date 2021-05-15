package ua.com.src;

import java.util.ArrayList;

import static ua.com.src.Utils.getEdge;

public class Task {

    public static int task1(int[] arr) {
        ArrayList<Integer> sum = new ArrayList<>();
        int end_sum = 0;
        for (int j : arr) {
            sum.set(j, 1);
        }
        for (Integer integer : sum) {
            end_sum += integer;
        }
        return end_sum;
    }

    public static void task2() {

    }

    public static double task3(int a, int b, int c) {
        double p = (getEdge(a, b) + getEdge(c, b) + getEdge(a, c)) / 2;
        return Math.sqrt(p * (p - getEdge(a, b)) * (p - getEdge(c, b)) * (p - getEdge(a, c)));
    }
}
