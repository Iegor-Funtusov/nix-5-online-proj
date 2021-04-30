import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите размер массива n (1≤N≤100): ");
        int n = in.nextInt();
        int [] array = new int [n];

        if (n < 1 || n > 100) {
            System.out.println("Размер массива должен быть в пределах 1≤N≤100!");
            return;
        }

        System.out.println("Данный массив случайных чисел:");
        for (int i = 0; i < array.length; i++) {
                int sign = Math.random() < 0.5 ? -1 : 1;
                array[i] = (int) (Math.random() * 100) * sign;
                System.out.printf("%3d ", array[i]);
            }
            System.out.println();

        evenArray(array);
        positiveNum(array);
        biggerPrevious(array);
        neighbourCount(array);
        reverseArray(array);

        System.out.println("Данный массив:");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%3d ", array[i]);
        }
        System.out.println();

        swapArray(array);
    }

    public static void evenArray(int[] array) {
        System.out.println("Чётные элементы массива:");
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                System.out.print(array[i] + " ");
            }
        }
        System.out.println();
    }

    public static void positiveNum(int[] array) {
        int positiveCounter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                positiveCounter++;
            }
        }
        System.out.printf("Количество положительных элементов массива: %d", positiveCounter);
        System.out.println();
    }

    public static void biggerPrevious(int[] array) {
        int biggerCounter = 0;
        for (int i = 0; i < array.length-1; i++) {
            if (array[i+1] > array[i]) {
                biggerCounter++;
            }
        }
        System.out.printf("Количество элементов массива больше предыдушего: %d", biggerCounter);
        System.out.println();
    }

    public static void neighbourCount(int[] array) {
        int biggerCounter = 0;
        for (int i = 0; i < array.length-2; i++) {
            if (array[i+1] > array[i] && array[i+1] > array[i+2]) {
                biggerCounter++;
            }
        }
        System.out.printf("Количество элементов массива больше соседних: %d", biggerCounter);
        System.out.println();

    }

    public static void reverseArray(int[] array) {
        System.out.println("Массив в обратном порядке:");
                for (int i = 0; i < array.length / 2; i++) {
                    int temp = array[i];
                    array[i] = array[array.length-1-i];
                    array[array.length-1-i] = temp;
                }

      for (int element : array) {
            System.out.printf("%3d ", element);
        }
        System.out.println();
    }

    public static void swapArray(int[] array) {
        System.out.println("Массив после перестановки элементов:");
        for (int i = 0; i < array.length-1; i=i+2) {
                int temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
        }

       for (int element : array) {
            System.out.printf("%3d ", element);
        }
        System.out.println();
    }
}
