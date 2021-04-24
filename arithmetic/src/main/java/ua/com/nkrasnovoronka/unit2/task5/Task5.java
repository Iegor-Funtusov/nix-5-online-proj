package main.java.ua.com.nkrasnovoronka.unit2.task5;

import java.util.Arrays;

/**
 * Напишите программу, которая переставляет элементы массива в обратном порядке
 * без использования дополнительного массива. Программа должна считать массив,
 * поменять порядок его элементов, затем вывести результат (просто вывести
 * элементы массива в обратном порядке – недостаточно!). Необходимо вывести
 * массив, полученный после перестановки элементов.
 */
public class Task5 {
    public static void reverseArray(int [] array){
        System.out.println("Reversing array");
        int tmp;
        for (int i = 0; i < array.length / 2; i++) {
            tmp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = tmp;
        }

        System.out.println(Arrays.toString(array));



    }
}
