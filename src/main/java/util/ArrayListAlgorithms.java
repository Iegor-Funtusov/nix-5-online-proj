package util;

import java.util.ArrayList;

final public class ArrayListAlgorithms {
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

    public static void swapNeighbors(ArrayList<Integer> container) {
        Integer currentBufferElement;
        int containerLength;

        if (container.size() % 2 !=0) {
            containerLength = container.size() - 1;
        } else {
            containerLength = container.size();
        }

        for (int i = 0; i < containerLength - 1; i += 2) {
            currentBufferElement = container.get(i);
            container.set(i, container.get(i+1));
            container.set(i+1, currentBufferElement);
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