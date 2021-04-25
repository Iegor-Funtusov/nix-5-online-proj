package com.nixsolution;
import java.util.Scanner;

public class Arithmetic {

    public static void main(String[] args) {

        try (Scanner inputNumber = new Scanner(System.in)) {
            
            System.out.println("Please, enter the number of elements: ");
            int arraySize = inputNumber.nextInt();
            int array[] = new int[arraySize];
            System.out.println("Please, input numbers:");
            for (int i = 0; i < arraySize; i++) {
                array[i] = inputNumber.nextInt();
                
            }
            
            firstArray(array);
            secondArray(array);
            thirdArray(array);
            fourthArray(array);
            fifthArray(array);
            sixthArray(array);
            
        }
    }

    //Outputs even numbers
   final public static void firstArray(int[] input) {
        System.out.println("First Array:");
        for (int i : input) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }

        }
        System.out.println("");
    }

   //Counts the number of positive numbers among the elements of the array.
   final  public static void secondArray(int[] input) {
        System.out.println("Second Array:");
        int a = 0;
        for (int i : input) {
            if (i>0) {
                ++a;
            }
        }
        System.out.println(a);
    }

   //Counts the number of elements in the array greater than the previous one
   final public static void thirdArray(int[] input) {
        System.out.println("Third Array:");
        int a = 0;
        for (int i = 1; i<input.length;i++) {
            if (input[i]>input[i-1]) {
                a++;
            }

        }
        System.out.println(a);

    }
   
   //Determines the number of elements that have two adjacent elements and
   //at the same time both adjacent elements are less than the given one.
   final public static void fourthArray(int[] input) {
        System.out.println("Fourth Array:");
        int a = 0;
        for (int i = 1; i<input.length-1;i++) {
            if (input[i]>input[i-1] && input[i]>input[i+1]) {
                a++;
            }

        }
        System.out.println(a);

    }

   //Rearranges the elements of an array in reverse order
   //without using an additional array.
   final public static void fifthArray(int[] input) {
        System.out.println("Fifth Array:");
        int a = 0;
        int n = input.length;
        for (int i = 0; i < n / 2; i++) {
            a = input[n - i - 1];
            input[n - i - 1] = input[i];
            input[i] = a;
        }
        for (int i : input) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
   
   //Swaps adjacent array elements
   final  public static void sixthArray(int[] input) {
        System.out.println("Sixth Array:");
        int b = 0;
        int n = input.length;
        for (int i = 0; i < n / 2; i+=2) {
            b = input[i];  // b = 1
            input[i]=input[i+1];// 1=2
            input[i+1]=b;// 2 = b(1)
        }
        for (int i : input) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
} // end of Arithmetic


