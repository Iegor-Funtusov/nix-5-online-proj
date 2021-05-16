package com.utils.level.one.task.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class UniqueElements {

    private static final int  r_MAX = 100;
    private static final int  r_MIN = 0;

    public static int[] InitArray(BufferedReader reader) throws IOException {
        System.out.print("Enter the '0' if you want to input array manually and '1' if want to generate random values: ");
        int[] array;
        if (reader.readLine().equals("0")) {
            System.out.print("Enter size of array: ");
            int size = Integer.parseInt(reader.readLine());
            array = new int[size];
            System.out.print("Enter the array values: ");
            String[] values = reader.readLine().split("\\s+");
            for (int i = 0; i < size; i++) {
                array[i] = Integer.parseInt(values[i]);
            }
        } else {
            Random random = new Random();
            int size = 10;
            System.out.println("Array size: " + size);
            System.out.print("Array values: ");
            array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(r_MAX - r_MIN) + r_MIN;
                System.out.print(array[i] + " ");
            }
            System.out.println("");
        }
        return array;
    }

    public static int getMax(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int getAmountOfUnique(int[] arr) {
        int[] values = new int[getMax(arr) + 1];
        int end_sum = 0;
        for (int j : arr) {
            values[j] = 1;
        }
        for (int i : values) {
            end_sum += i;
        }
        return end_sum;
    }


}
