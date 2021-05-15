package com.tasks;

import java.util.ArrayList;

public class UniqueElements {
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
}
