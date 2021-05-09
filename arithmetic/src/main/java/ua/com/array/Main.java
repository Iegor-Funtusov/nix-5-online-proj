package ua.com.array;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayArithmetic array;
        boolean flag = true;
        while (flag) {
            array = createArrayArithmetic(reader);
            arrayArithmeticMethodsTest(array);
            System.out.println("Please choose one of next options: \n 1 - create new array \n 0 - exit");
            switch (Integer.parseInt(reader.readLine())){
                case 1:
                    flag = true;
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Unknown options! Exiting...");
                    flag = false;
                    break;
            }
        }
    }

    private static void arrayArithmeticMethodsTest(ArrayArithmetic array) {
        System.out.println("Array: " + array);

        System.out.print("Even numbers:");
        array.printEvenNumbers();

        System.out.println("\nThe number of positive numbers: "
                + array.numberOfPositiveElements());

        System.out.println("The number of elements that are bigger than the previous one: "
                + array.numberOfBiggerThanPrevious());

        System.out.println("The number of elements that are bigger than their neighbors: "
                + array.numberOfBiggerThanNeighbors());

        array.reverseArray();
        System.out.println("Reversed array: " + array);

        array.reshufflePair();
        System.out.println("Array after pair reshuffle: " + array);
    }

    @SneakyThrows
    private static ArrayArithmetic createArrayArithmetic(BufferedReader reader) {
        ArrayArithmetic array = null;
        int size;
        System.out.print("Enter array size: ");
        size = Integer.parseInt(reader.readLine());
        while (array == null) {
            System.out.print("Please, choose method of array creating: \n 1 - Manual \n 2 - Random \n 0 - exit \n --> ");
            switch (Integer.parseInt(reader.readLine())) {
                case 0:
                    System.exit(0);
                case 1:
                    array = new ArrayArithmetic(createManualEnteredArray(reader, size));
                    break;
                case 2:
                    array = new ArrayArithmetic(createRandomArray(size));
                    break;
                default:
                    System.out.println("Please make your choice!");
                    break;
            }
        }
        return array;
    }

    public static int[] createRandomArray(int size){
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100) - 50;
        }
        return array;
    }

    @SneakyThrows
    public static int[] createManualEnteredArray(BufferedReader reader, int size){
        int[] array = new int[size];
        System.out.println("Enter items separated by \"Enter\": ");
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(reader.readLine());
        }
        return array;
    }

}
