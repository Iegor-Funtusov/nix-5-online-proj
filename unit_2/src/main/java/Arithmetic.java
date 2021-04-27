import java.util.InputMismatchException;
import java.util.Scanner;

public class Arithmetic {
    public static void main (String[] argc) {
        System.out.println("Input a number of items in array (1-100)");
        int items = checking();
        while (items <= 0 || items > 100) {
            if(items == -1) {
                System.out.println("It was not a NUMBER. Input a number");
                items = checking();
                continue;
            }
            if (items == -2) {
                System.out.println("Incorrect input. Array can have 1-100 items. Enter again.");
                items = checking();
            }
        }
        //evenNumber(items); //task 1
        //sumPositive(items); // task 2
        //moreThanPrevious(items); //task 3
        //biggerThanNeighbors(items); // task 4
        reverse(items); //task 5
        //swapping(items); //task 6
    }

    static int checking(){
        Scanner sc = new Scanner(System.in);
        int items;
        try {
            items = sc.nextInt();
            if (items <= 0 ||items > 100){
                return -2;
            }
            return items;
        } catch (InputMismatchException ex) {
        }
        return -1;
    }


    static int[] inputting(int items) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[items];
        System.out.print("Input numbers(numbers separated by the space): ");
        int num;
        for (int i = 0; i < items; i++)
        {
            num = sc.nextInt();
            arr[i] = num;
        }
        return arr;
    }

    static void evenNumber(int items){
        int[] arr = inputting(items);
        int counter = 0;
        for (int i = 0; i < items; i++)
        {
            if (arr[i]%2 == 0) {
                System.out.print(arr[i] + " ");
                counter++;
            }
        }
        if (counter == 0){
            System.out.print("Array doesn't have even numbers");
        }
    }

    static void sumPositive(int items) {
        int[] arr = inputting(items);
        int sum = 0;
        for (int i = 0; i < items; i++)
        {
            if(arr[i]>0){
                sum += arr[i];
            }
        }
        System.out.println(sum);
    }

    static void moreThanPrevious(int items) {
        int[] arr = inputting(items);
        int quantity = 0;
        for (int i = 0; i < items-1; i++)
        {
            if(arr[i+1]>arr[i]){
                quantity++;
            }
        }
        System.out.println(quantity);
    }

    static void biggerThanNeighbors(int items) {
        int[] arr = inputting(items);
        int quantity = 0;
        for (int i = 1; i < items-1; i++) {
            if(arr[i] > arr[i-1] && arr[i] > arr[i+1]){
                quantity++;
            }
        }
        System.out.println(quantity);
    }

    static void reverse (int items){
        int[] arr = inputting(items);
        int swap;
        for (int i = 0; i < items/2; i++)
        {
            swap = arr[i];
            arr[i] = arr[items-i-1];
            arr[items-i-1] = swap;
        }
        for (int i = 0; i < items; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void swapping (int items){
        int[] arr = inputting(items);
        int swap;
        if(1 == items){
            System.out.print(arr[0] + " ");
            return;
        }
        for (int i = 0; i < items; i += 2)
        {
            swap = arr[i];
            arr[i] = arr[i+1];
            arr[i+1]=swap;
            if(i+3 == items){
                break;
            }
        }
        for (int i = 0; i < items; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
