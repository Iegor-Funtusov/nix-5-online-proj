package ua.com.array;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        ArrayArithmetic array = new ArrayArithmetic(createRandomArray());
    }

    public static int[] createRandomArray(){
        Random random = new Random();
        int n = random.nextInt(100) + 1;
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

}
