package ua.com.alevel;

import java.util.Arrays;

public class AppMain {

    public static void main(String[] args) {
        runFirstTask();
    }

    private static void runFirstTask() {
        int[] array = {1, 25, 1, 1, 3};
        System.out.println(Arrays.toString(array));
        System.out.println("Number of unique elements: " + countUniqueElements(array));
    }

    private static int countUniqueElements(int[] array) {
        int[] uniqueItems = new int[array.length];
        int size = 0;
        for (int i = 0; i < array.length; i++) {
            if(!isPresent(uniqueItems, array[i]))
                uniqueItems[size++] = array[i];
        }
        return size;
    }

    private static boolean isPresent(int[] uniqueItems, int i) {
        for (int j = 0; j < uniqueItems.length; j++) {
            if(uniqueItems[j] == i)
                return true;
        }
        return false;
    }


}
