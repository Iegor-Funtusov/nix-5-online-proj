package com.nixcourse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int arraySize = Integer.parseInt(buffer.readLine());
        ArrayList<Integer> userInput = toIntegerArrayList(
                buffer.readLine(),
                arraySize
        );

        findEvenNumbers(userInput).forEach(
                i -> System.out.printf("%s ", i)
        );

        arraySize = Integer.parseInt(buffer.readLine());
        userInput = toIntegerArrayList(buffer.readLine(), arraySize);
        System.out.println(countPositiveNumbers(userInput));

        arraySize = Integer.parseInt(buffer.readLine());
        userInput = toIntegerArrayList(buffer.readLine(), arraySize);
        System.out.println(countNumbersGreaterThanPrevious(userInput));

        arraySize = Integer.parseInt(buffer.readLine());
        userInput = toIntegerArrayList(buffer.readLine(), arraySize);
        System.out.println(countNumbersGreaterThanNeighbors(userInput));

        arraySize = Integer.parseInt(buffer.readLine());
        userInput = toIntegerArrayList(buffer.readLine(), arraySize);
        reverseIntegerArrayList(userInput);
        System.out.println(userInput);

        buffer.close();
    }

    public static ArrayList<Integer> findEvenNumbers(ArrayList<Integer> container) {
        ArrayList<Integer> result = new ArrayList<>();

        for (Integer integer : container) {
            if (integer % 2 == 0) {
                result.add(integer);
            }
        }

        return result;
    }

    public static int countPositiveNumbers(ArrayList<Integer> container) {
        int counter = 0;

        for (Integer integer : container) {
            if (integer > 0) {
                ++counter;
            }
        }

        return counter;
    }

    public static int countNumbersGreaterThanPrevious(ArrayList<Integer> container) {
        int counter = 0;
        int previousIndex;

        for (int i = 1; i < container.size(); ++i) {
            previousIndex = i - 1;
            if (container.get(i) > container.get(previousIndex)) {
                ++counter;
            }
        }

        return counter;
    }

    public static int countNumbersGreaterThanNeighbors(ArrayList<Integer> container) {
        int counter = 0;
        int previousIndex, nextIndex, sumOfNeighbors;

        for (int i = 1; i < container.size() - 1; ++i) {
            previousIndex = i - 1;
            nextIndex = i + 1;
            sumOfNeighbors = container.get(previousIndex) + container.get(nextIndex);
            if (container.get(i) > sumOfNeighbors) ++counter;
        }

        return counter;
    }

    public static void reverseIntegerArrayList(ArrayList<Integer> container) {
        for (int i = 0; i < container.size(); ++i) {
            container.add(i, container.remove(container.size() - 1));
        }
    }

    public static ArrayList<Integer> toIntegerArrayList(String sourceString,
                                                        int containerLength) {
        ArrayList<Integer> result = new ArrayList<>();
        String[] container = sourceString.split(" ");

        for (int i = 0; i < containerLength; ++i) {
            result.add(Integer.parseInt(container[i]));
        }

        return result;
    }
}
