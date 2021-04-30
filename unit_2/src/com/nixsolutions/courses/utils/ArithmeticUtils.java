package com.nixsolutions.courses.utils;


public class ArithmeticUtils {

    public static void swapNeighbors() {
        int[] array = ArrayUtils.inputArray();
        int tmp;
        for (int i = 0; i < array.length - 1; i = i + 2) {
            tmp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = tmp;
        }
        ArrayUtils.printArray(array);
    }

    public static void reverseOrder() {
        int[] array = ArrayUtils.inputArray();
        int tmp;
        for (int i = 0; i < array.length / 2; i++) {
            tmp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = tmp;
        }
        ArrayUtils.printArray(array);
    }

    public static void countWithSmallerNeighbors() {
        int[] array = ArrayUtils.inputArray();
        int count = 0;
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i - 1] < array[i] && array[i] > array[i + 1]) {
                count++;
            }
        }
        System.out.print(count);
    }

    public static void countNextGreater() {
        int[] array = ArrayUtils.inputArray();
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                count++;
            }
        }
        System.out.print(count);
    }

    public static void countPositiveNumbers() {
        int[] array = ArrayUtils.inputArray();
        int count = 0;
        for (int num : array) {
            if (num > 0) {
                count++;
            }
        }
        System.out.print(count);
    }

    public static void printEvenNumbers() {
        int[] array = ArrayUtils.inputArray();
        for (int num : array) {
            if (num % 2 == 0) {
                System.out.print(num + " ");
            }
        }

    }
}
