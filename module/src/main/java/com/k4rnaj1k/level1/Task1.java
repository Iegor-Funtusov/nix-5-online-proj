package com.k4rnaj1k.level1;

import com.k4rnaj1k.RunModule;

import static com.k4rnaj1k.RunModule.s;

import java.util.Random;
import java.util.Scanner;

public class Task1 {
    static Random r = new Random();

    public static void solve() {
        System.out.println("Manual input?(y/n)");
        int size;
        int[] arr;
        if (s.next().toLowerCase().startsWith("y")) {
            System.out.println("Input size");
            size = s.nextInt();
            arr = new int[size];
            System.out.println("Input the array");
            input_arr(arr, size);
        } else {
            size = r.nextInt(11);
            arr = new int[size];
            generate(arr);
        }
        System.out.print("Your array = ");
        out_arr(arr);
        count_unique(arr);
    }

    private static void generate(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(11);
        }
    }

    private static void out_arr(int[] arr) {
        for (int n :
                arr) {
            System.out.print(n + " ");
        }
    }

    private static void input_arr(int[] arr, int size) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = s.nextInt();
        }
    }

    private static void count_unique(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            count++;
            for (int j = 0; j < i; j++) {
                if (arr[j] == arr[i]) {
                    count--;
                    break;
                }
            }
        }
        System.out.println("\nUnique numbers = " + count);
    }

    public static String name() {
        return "Count the number of unique numbers in an array";
    }
}
