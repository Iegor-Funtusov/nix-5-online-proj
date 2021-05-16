package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class ArrayUtil {

    public static int countUniqueElements(int[] array) {
        int[] copy = new int[array.length];
        System.arraycopy(array,0,copy,0,array.length);
        int result = 0;
        Arrays.sort(copy);
        for (int i = 0; i < copy.length; i++) {
            while (i < copy.length - 1 && copy[i] == copy[i+1] ){
                i++;
            }
            result++;
        }
        return result;
    }

    public static void printArray(int[] array){
        if(array == null) {
            System.out.println("Empty array!");
            return;
        }
        System.out.print("Array: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static int[] getRandomArray(BufferedReader reader) {
        Random random = new Random();
        int[] array = new int[getArraySize(reader)];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10);
        }
        return array;
    }

    public static int getArraySize(BufferedReader reader){
        int size = 0;
        while (true) {
            try {
                System.out.print("Enter array size: ");
                size = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (size != 0)
                break;
        }
        return size;
    }

    public static int[] getArray(BufferedReader reader) {
        int[] array = new int[getArraySize(reader)];
        int counter = 0;
        while (true) {
            try {
                System.out.print("Enter integer elements separated by \"Enter\": ");
                for (int i = 0; i < array.length; i++) {
                    array[i] = Integer.parseInt(reader.readLine());
                    counter++;
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            if(counter == array.length)
                break;
        }
        return array;
    }
}
