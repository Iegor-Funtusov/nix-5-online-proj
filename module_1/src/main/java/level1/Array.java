package level1;

import java.util.ArrayList;
import java.util.Scanner;

/** Дан массив чисел. Вернуть число уникальных символов
 * Пример: для 1 4 5 1 1 3 ответ 4.
 */

public class Array {

    private static boolean isSorted;
    private static int buf;

    public static void Main() {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Способ формирования массива или 'q' для выхода: ");
        System.out.println("M - Manual (ввод с клавиатуры)");
        System.out.println("R - Random (случайные числа)");
        String choice = inputUser.next();
        while (!"q".equals(choice)) {
            switch (choice) {
                case "M":
                    manualArray();
                    break;
                case "R":
                    randomArray();
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный выбор!");
            }
            System.out.println("Способ формирования массива или 'q' для выхода: ");
            System.out.println("M - Manual (ввод с клавиатуры)");
            System.out.println("R - Random (случайные числа)");
            choice = inputUser.next();
            System.out.println();
        }
    }

    public static void randomArray() {
        Scanner inputUser = new Scanner(System.in);
        System.out.print("Введите размер массива n: ");
        int n = inputUser.nextInt();
        int [] array = new int [n];
        System.out.println("Данный массив случайных чисел:");
        for (int i = 0; i < array.length; i++) {
            int sign = Math.random() < 0.5 ? -1 : 1;
            array[i] = (int) (Math.random() * 10) * sign;
            System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.println("Уникальных чисел:");
        System.out.println(sizeArray(array));
        System.out.println();
    }

    public static void manualArray() {
        Scanner inputUser = new Scanner(System.in);
        System.out.print("Введите размер массива n: ");
        int n = inputUser.nextInt();
        int [] array = new int [n];
        System.out.println("Введите числа массива:");
        for (int i = 0; i < array.length; i++) {
            array[i] = inputUser.nextInt();
          //  System.out.print(array[i] + " ");
        }
        System.out.println("Данный массив:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.println("Уникальных чисел:");
        System.out.println(sizeArray(array));
        System.out.println();
    }

    private static void sortArray(int [] array){
        for (int j = 0; j < array.length; j++) {
            isSorted = false;
            while (!isSorted) {
                isSorted = true;
                for (int i = 0; i < array.length - 1; i++) {
                    if (array[i] > array[i + 1]) {
                        isSorted = false;
                        buf = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = buf;
                    }
                }
            }
        }
    }

    public static int sizeArray(int[] array) {
        sortArray(array);
        ArrayList<Integer> B = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
           if (i == array.length - 1) {
                B.add(array[i]);
           } else if (array[i] != array[i + 1]) {
                B.add(array[i]);
            }
        }
       return B.size();
    }
}
