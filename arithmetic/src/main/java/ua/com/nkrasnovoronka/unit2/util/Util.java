package main.java.ua.com.nkrasnovoronka.unit2.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
            for (int i = 0; i < arraySize; i++) {
                array[i] = Integer.parseInt(bufferedReader.readLine());
            }
        } catch (IOException e) {
            System.err.println("Oops! Pleas restart program");
        }
        return array;
    }
}
