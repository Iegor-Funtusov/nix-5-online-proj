package com.nixcourse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import util.ArrayListAlgorithms;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int arraySize = Integer.parseInt(buffer.readLine());
        ArrayList<Integer> userInput = ArrayListAlgorithms.toIntegerArrayList(
                buffer.readLine(),
                arraySize
        );

        ArrayListAlgorithms.findEvenNumbers(userInput).forEach(
                i -> System.out.printf("%s ", i)
        );
        System.out.println();

        arraySize = Integer.parseInt(buffer.readLine());
        userInput = ArrayListAlgorithms.toIntegerArrayList(
                buffer.readLine(), arraySize
        );
        System.out.println(
                ArrayListAlgorithms.countPositiveNumbers(userInput)
        );

        arraySize = Integer.parseInt(buffer.readLine());
        userInput = ArrayListAlgorithms.toIntegerArrayList(buffer.readLine(), arraySize);
        System.out.println(ArrayListAlgorithms.countNumbersGreaterThanPrevious(userInput));

        arraySize = Integer.parseInt(buffer.readLine());
        userInput = ArrayListAlgorithms.toIntegerArrayList(buffer.readLine(), arraySize);
        System.out.println(ArrayListAlgorithms.countNumbersGreaterThanNeighbors(userInput));

        arraySize = Integer.parseInt(buffer.readLine());
        userInput = ArrayListAlgorithms.toIntegerArrayList(buffer.readLine(), arraySize);
        ArrayListAlgorithms.reverseIntegerArrayList(userInput);
        for (Integer i : userInput) {
            System.out.printf("%s ", i.toString());
        }
        System.out.println();

        arraySize = Integer.parseInt(buffer.readLine());
        userInput = ArrayListAlgorithms.toIntegerArrayList(buffer.readLine(), arraySize);
        ArrayListAlgorithms.swapNeighbors(userInput);
        for (Integer i : userInput) {
            System.out.printf("%s ", i.toString());
        }

        buffer.close();
    }
}