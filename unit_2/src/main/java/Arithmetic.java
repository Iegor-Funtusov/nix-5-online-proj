import java.util.Scanner;

public class Arithmetic {
    public static void main (String[] argc) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number of items in array");
        int items = sc.nextInt();
        //evenNumber(items); //task 1
        //sumPositive(items); // task 2
        //moreThanPrevious(items); //task 3
        //biggerThanNeighbors(items); // task 4
        //reverse(items); //task 5
        swapping(items); //task 6
    }

    static int[] inputting(int items) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[items];
        System.out.print("Input numbers: ");
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
        for (int i = 0; i < items; i++)
        {
            if (arr[i]%2 == 0) {
                System.out.print(arr[i] + " ");
            }
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
