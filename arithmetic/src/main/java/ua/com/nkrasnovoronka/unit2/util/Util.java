package main.java.ua.com.nkrasnovoronka.unit2.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Util {
    private static int DEFAULT_ARRAY_SIZE = 5;

    public static int[] createArrayFromUserInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Pleas enter size of array");
        int [] array = null;
        try {
            int arraySize = Integer.parseInt(bufferedReader.readLine());
            if(arraySize < 0 || arraySize > 100){
                System.err.printf("Array size is not valid%nUsing default array size%n");
                arraySize = DEFAULT_ARRAY_SIZE;
            }
            array = new int[arraySize];

            System.out.printf("Enter %d numbers to array%n", arraySize);

            array = Arrays.stream(bufferedReader.readLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if(array.length != arraySize){
                System.err.printf("Expected array size %d but was %d%n", arraySize, array.length);
                throw new IOException();
            }

        } catch (IOException e) {
            System.err.println("Oops! Pleas restart program");
        }
        return array;
    }
}
