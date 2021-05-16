package ua.com.module1.service;

import ua.com.module1.DetermineNumberUniqueSymbols;

import java.util.Scanner;

public class UniqueNumbers {
    public static void getUniqueNumber() {
        int number;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of elements: from 1 to 20:");
        number = scanner.nextInt();
            if(number > 20 || number <= 1){
                System.out.println("Please, enter correct number: from 1 to 20");
                number = scanner.nextInt();
            }
            else{
                int[] arr = new int[number];
                System.out.println("Enter elements:");
                for (int i = 0; i < number; i++) {
                    arr[i] = scanner.nextInt();
                }
                printArray(arr);
                int count = DetermineNumberUniqueSymbols.getUniqueNumber(arr);
                System.out.println("The number of unique numbers is " + count);
            }
    }

    public static void printArray (int [] arr){
        System.out.println("Your array: ");
            for (int value : arr) {
                System.out.print(value + " ");
            }
            System.out.println(" ");
        }

    public static void getUniqueNumberAuto(){
        int number = (int) Math.round(Math.random() * 10) + 5;
        System.out.println("Number of elements: " + number);
        int [] array = new int [number];
        for(int i = 0; i < array.length; i++){
            array[i] = (int) Math.round(Math.random() * 15);
        }
        printArray(array);
        int count = DetermineNumberUniqueSymbols.getUniqueNumber(array);
        System.out.println("The number of unique numbers is " + count);
    }
}
