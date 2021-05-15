package ua.com.alevel;

import java.util.Arrays;

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
}
