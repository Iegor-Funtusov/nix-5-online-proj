package main.java.ua.com.nkrasnovoronka.unit2.task2;

import java.util.Arrays;

/**
 * Дан массив, состоящий из целых чисел. Напишите программу, которая
 * подсчитывает количество положительных чисел среди элементов массива.
 * Необходимо единственное число - количество положительных элементов в
 * массиве.
 */
public class Task2 {
    public static void calculateNumberOfPositiveNumber(int[] array){
        System.out.println("Calculating positive numbers in array");
        long count = Arrays.stream(array).filter(value -> value > 0).count();
        System.out.println(count);
    }
}
