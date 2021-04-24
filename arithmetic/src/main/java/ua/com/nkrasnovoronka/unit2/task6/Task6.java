package main.java.ua.com.nkrasnovoronka.unit2.task6;

import java.util.Arrays;

/**
 * Напишите программу, которая переставляет соседние элементы массива (1-й
 * элемент поменять с 2-м, 3-й с 4-м и т.д. Если элементов нечетное число, то
 * последний элемент остается на своем месте). Необходимо вывести массив,
 * полученный после перестановки элементов.
 */
public class Task6 {
    public static void swapNeighbors(int[] array) {
        System.out.println("Swapping neighbors");
        int tmp;
        for (int i = 1; i < array.length; i = i + 2) {
            tmp = array[i - 1];
            array[i - 1] = array[i];
            array[i] = tmp;
        }

        System.out.println(Arrays.toString(array));
    }
}
