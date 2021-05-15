package level1.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UniqueNumber {

    public static int[] makeArrayOfNumbersByUser() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter quantity of numbers which you planned to analise (e.g. 5): ");
        int size = Integer.parseInt(reader.readLine());
        int[] array = new int[size];
        System.out.println("Please enter" + size + "numbers (use Enter for inputting next number): ");

        for (int i = 0; i < array.length; ++i) {
            int numbers = Integer.parseInt(reader.readLine());
            array[i] = numbers;
        }
        System.out.println("Your array:" + Arrays.toString(array));
        return array;
    }


    public static int[] makeArrayOfNumbersRandom() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter quantity of numbers which you planned to analise (e.g. 5): ");
        int size = Integer.parseInt(reader.readLine());
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * size);
        }
        System.out.println("Your array:" + Arrays.toString(array));
        return array;
    }

    public static void findUnique(int[] array) {
        int count = 0;
        int amountOfNumbers = 0;
        int uniqueNumbers;
        for (int i = 0; i < array.length; ++i) {
            amountOfNumbers++;
            for (int j = i + 1; j < array.length; ++j) {
                if (array[j] == array[i]) {
                    count++;
                    break;
                }
            }
        }
        uniqueNumbers = amountOfNumbers - count;
        System.out.println("Quantity of unique numbers: " + uniqueNumbers);
    }
}
