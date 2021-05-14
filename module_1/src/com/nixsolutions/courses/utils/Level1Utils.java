package com.nixsolutions.courses.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class Level1Utils {

     private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 100;

    public static int[] createArray(BufferedReader reader) throws IOException {
        System.out.println("Do you want to input array?\n0 - no\n1 - yes");
        int[] array;
        if(reader.readLine().equals("0")){
            array = generateArray();
        } else {
            System.out.println("Enter size of array:");
            array = new int[Integer.parseInt(reader.readLine())];
            System.out.println("Enter array elements space-separated:");
            String[] arr = reader.readLine().split(" ");
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt(arr[i]);
            }
        }
        return array;
    }

    public static int[] generateArray() {
        Random random = new Random();
        int n = random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN;
        System.out.println("Size of array:\n" + n);
        System.out.println("Array of random numbers:");
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN;
            System.out.print(array[i] + " ");
        }
        System.out.println("");
        return array;
    }

}
