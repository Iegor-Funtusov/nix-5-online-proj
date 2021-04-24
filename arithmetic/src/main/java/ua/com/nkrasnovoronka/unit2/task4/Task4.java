package main.java.ua.com.nkrasnovoronka.unit2.task4;

/**
 * Дан массив, состоящий из целых чисел. Напишите программу, которая в данном
 * массиве определит количество элементов, у которых два соседних и, при этом,
 * оба соседних элемента меньше данного. Необходимо вывести количество
 * элементов массива, у которых два соседа и которые при этом строго больше
 * обоих своих соседей.
 */
public class Task4 {
    public static void countArrayNeighbors(int [] array){
        System.out.println("Counting array neighbors");
        int count = 0;
        for (int i = 1; i < array.length; i = i + 2) {
            if(array[i - 1] < array[i] && array[i + 1] < array[i]){
                count++;
            }
        }
        System.out.println(count);
    }
}
