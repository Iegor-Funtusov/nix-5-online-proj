package ua.com.array;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        ArrayArithmetic array = new ArrayArithmetic(createRandomArray());
        System.out.println("Array: " + array);

        System.out.print("Even numbers:");
        array.printEvenNumbers();

        System.out.println("\nThe number of positive numbers: "
                + array.numberOfPositiveElements());

        System.out.println("The number of elements that are bigger than previous one: "
                + array.numberOfBiggerThanPrevious());

        System.out.println("The number of elements that are bigger than their neighbors: "
                + array.numberOfBiggerThanNeighbors());

        array.reverseArray();
        System.out.println("Reversed array: " + array);

        array.reshufflePair();
        System.out.println("Array after pair reshuffle: " + array);
    }

    public static int[] createRandomArray(){
        Random random = new Random();
        int n = random.nextInt(5) + 5;
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100) - 50;
        }
        return array;
    }

}
