package org.example.level1.task1;

import java.util.Arrays;

public class ArrayUtil {

    public int returnNumberOfUniqueVal(int[] arr) {
        int count = 1;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                count++;
            }
        }
        return count;
    }
}