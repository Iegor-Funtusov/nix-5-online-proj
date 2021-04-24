package main.java.ua.com.nkrasnovoronka.unit2.task3;

/**
 * Дан массив, состоящий из целых чисел. Напишите программу, которая подсчитает
 * количество элементов массива, больших предыдущего (элемента с предыдущим
 * номером). Необходимо вывести единственное число - количество элементов
 * массива, больших предыдущего.
 */
public class Task3 {
    public static void countNextNumberInArray(int[] array) {
        System.out.println("Counting next bigger value in array");
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]){
                count++;
            }
        }
        System.out.println(count);

    }
}
