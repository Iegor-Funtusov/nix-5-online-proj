package ua.com.nkrasnovoronka.tests.level1.task1;

import ua.com.nkrasnovoronka.tasks.level1.task1.UniqueArrayElement;
import ua.com.nkrasnovoronka.util.UserInput;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UniqueArrayElementTest {

    public static void randomNumbersTest(){
        System.out.println("Starting random numbers test");
        Random random = new Random();
        int[] array = new int[random.nextInt(20) + 1];
        System.out.println("Initialized array with length " + array.length);
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(20) + 1;
        }
        System.out.println("Random number arrays = " + Arrays.toString(array));
        int numberOfUniqueElements = UniqueArrayElement.getNumberOfUniqueElements(array);
        System.out.println("unique elements in array = " + numberOfUniqueElements );

    }

    public static void userInputTest(){
        System.out.println("Starting user input test");
        List<Integer> integers = UserInput.userInputNumbers();
        System.out.println("User number arrays = " +integers);
        int numberOfUniqueElements = UniqueArrayElement.getNumberOfUniqueElements(
                integers.stream()
                        .mapToInt(value -> value).
                        toArray()
        );
        System.out.println("unique elements in array = " + numberOfUniqueElements );
    }
}
