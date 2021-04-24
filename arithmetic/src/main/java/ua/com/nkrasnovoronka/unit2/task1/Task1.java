package main.java.ua.com.nkrasnovoronka.unit2.task1;

/**
 * Дан массив, состоящий из целых чисел. Напишите программу, которая выводит те
 * элементы массива, которые являются чётными числами.
 * Необходимо вывести все четные элементы массива (то есть те элементы, которые
 * являются четными числами).
 */
public class Task1 {
    public static void arrayEvenNumbersPrinter(int[] array) {
        System.out.println("Printing all even numbers from array");
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            if (i % 2 == 0) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);

    }
}
