package ua.com.threadedcode.array;

import java.util.Arrays;

public class ArrayUtil {


    public void printEvenNumbers(int[] arr) {

        for (int i : arr) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }

    public void printNumOfPositiveNumbers(int[] arr) {
        int count = 0;
        for (int i : arr) {
            if (i >= 0) {
                count++;
            }
        }
        System.out.println(count);
    }

    public void printCountOfElemetsBiggerPrevious(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                count++;
            }
        }
        System.out.println(count);
    }

    public void printCountOfElBiggerPrevAndNext(int[] arr) {
        int tmp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 != 0 && arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                tmp++;
            }
        }
        System.out.println(tmp);
    }

    public void revertArray(int[] arr) {
        int tmp;


        for (int i = 0; i < arr.length; i++) {
            tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }
        System.out.println(Arrays.toString(arr));
    }

    public void swapAdjacentElements(int[] arr) {
        int tmp = 0;
        int arrLength = arr.length;

        if (arr.length % 2 != 0) {
            arrLength = (arrLength - 1) - 1;
        }

        for (int i = 0; i <= arrLength; i++) {
            if (i % 2 != 0) {
                tmp = arr[i - 1];
                arr[i - 1] = arr[i];
                arr[i] = tmp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
