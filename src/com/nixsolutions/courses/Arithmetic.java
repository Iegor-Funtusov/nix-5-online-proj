package com.nixsolutions.courses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Arithmetic {

    public static void printEvenNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of array and its elements:");
        final int N = Integer.parseInt(scanner.nextLine());
        int[] array = new int[N];
        String[] s = scanner.nextLine().split(" ");
        for(int i =0 ;i < s.length;i++){
            array[i]= Integer.parseInt(s[i]);
        }
        for (int i = 0; i < N; i++) {
            if(array[i] % 2 == 0) {
                System.out.print(array[i] + " ");
            }
        }

    }

    public static void printOptions() {
        System.out.println("\n0 - exit\n1 - find even numbers\n2 - count positive numbers\n"
                + "3 - count elements greater than previous\n4 - count elements that have two greater neighbors\n"
                + "5 - reverse order\n6 - swap neighbors");
    }

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose option:");
        printOptions();
        String read;
        while ((read = input.readLine()) != null) {
            switch (read) {
                case "0" : {
                    System.exit(0);
                } break;
                case "1" : {
                    printEvenNumbers();
                } break;
                case "2" : {
                    //
                } break;
            }
            printOptions();
        }
    }
}
