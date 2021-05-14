package com.nixsolutions.courses.level1;

import com.nixsolutions.courses.utils.Level1Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Level1 {

    static BufferedReader reader;

    public static void countUniqueElements() throws IOException {
        int count = 0;
        int[] array = Level1Utils.createArray(reader);
        int max = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        int[] countElementsRepeat = new int[max+1];
        for (int i : array) {
            countElementsRepeat[i]++;
        }
        for (int repeats : countElementsRepeat) {
            if(repeats != 0) {
                count++;
            }
        }
        System.out.println("Number of unique elements:\n" + count);
    }

    public static void run() {
        System.out.println("Level1.run");
        reader = new BufferedReader((new InputStreamReader(System.in)));
        System.out.println("Choose task:\n0 - exit\n1 - count unique elements of array");
        String input;
        try {
            while (!(input = reader.readLine()).equals("0")) {

                switch(input) {
                    case "1":
                        countUniqueElements();
                        break;
                }
                System.out.println("Choose task:\n0 - exit\n1 - count unique elements of array");
            }
        } catch(IOException e) {
            System.out.println("Something went wrong");
        }
    }

}
