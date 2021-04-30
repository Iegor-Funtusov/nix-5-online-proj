package com.nixsolutions.courses.utils;

import java.util.Scanner;

public class ArrayUtils {

    public static void printArray(int[] array) {
        for (int num: array) {
            System.out.print(num + " ");
        }
    }

    public static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of array and its elements:");
        final int N = Integer.parseInt(scanner.nextLine());
        int[] array = new int[N];
        String[] s = scanner.nextLine().split(" ");
        for(int i =0 ;i < s.length;i++){
            array[i]= Integer.parseInt(s[i]);
        }
        return array;
    }

}
