import java.util.Locale;
import java.util.Scanner;

public class Solver {
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        String repeat;
        do {
            int tasknum;
            do {
                System.out.println("Input task number(1-6)");
                tasknum = s.nextInt();
            } while (tasknum < 1 || tasknum > 6);
            System.out.println("Input number of elements in array.");
            int number = s.nextInt();
            switch (tasknum) {
                case 1:
                    task1(number);
                    break;
                case 2:
                    task2(number);
                    break;
                case 3:
                    task3(number);
                    break;
                case 4:
                    task4(number);
                    break;
                case 5:
                    task5(number);
                    break;
                case 6:
                    task6(number);
                    break;
            }
            System.out.println("\nRepeat?(y/n)");
            repeat = s.next();
        } while (repeat.toLowerCase().startsWith("y"));
    }


    static void task1(int number) {
        int[] arr = new int[number];
        for (int i = 0; i < number; i++) {
            arr[i] = s.nextInt();
        }
        System.out.println("Even elements: ");
        for (int n :
                arr) {
            if (n % 2 == 0)
                System.out.print(n + " ");
        }
    }

    static void task2(int number) {
        int[] arr = new int[number];
        for (int i = 0; i < number; i++)
            arr[i] = s.nextInt();
        int result = 0;
        for (int n :
                arr) {
            if (n > 0)
                result++;
        }
        System.out.print("Number of positive elements in the given array: " + result);
    }

    static void task3(int number) {
        int prev = Integer.MAX_VALUE;
        int[] arr = new int[number];
        int result = 0;
        for (int i = 0; i < number; i++) {
            arr[i] = s.nextInt();
            if (arr[i] > prev)
                result++;
            prev = arr[i];
        }
        System.out.print("Number of elements bigger than their previous one: " + result);
    }

    static void task4(int number) {
        int[] arr = new int[number];
        int result = 0;
        for (int i = 0; i < number; i++)
            arr[i] = s.nextInt();
        for (int i = 1; i < arr.length - 1; i++)
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1])
                result++;
        System.out.print("Number of elements that are both bigger than their previous one and next one: " + result);
    }

    static void task5(int number) {
        int[] arr = new int[number];
        for (int i = 0; i < number; i++)
            arr[i] = s.nextInt();
        for (int i = 0; i < number / 2; i++) {
            int el = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = el;
        }
        System.out.println("Reversed array: ");
        for (int n :
                arr) {
            System.out.print(n + " ");
        }
    }

    static void task6(int number) {
        int[] arr = new int[number];
        for (int i = 0; i < number; i++) {
            arr[i] = s.nextInt();
        }
        for (int i = 1; i < number; i += 2) {
            int n = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = n;
        }
        for (int n :
                arr) {
            System.out.print(n + " ");
        }
    }
}