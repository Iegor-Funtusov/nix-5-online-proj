import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        int[] array = scanArray();

        if(array.length == 0){
            System.out.println("Array is empty");
            return;
        }

        printArray(array);

//          task1(array);
//          task2(array);
//          task3(array);
//          task4(array);
//          task5(array);
//          task6(array);
    }


    public static int[] scanArray(){
        Scanner scanner = new Scanner(System.in);
        int[] array;
        int N;

        System.out.println("Enter quantity of the elements");
        N = scanner.nextInt();

        if(N == 0)
            return new int[N];

        array = new int[N];

        System.out.println("Enter the elements");
        for(int i = 0; i < N; i++)
            array[i] = scanner.nextInt();

        return array;
    }


    public static void printArray(int []array){
        System.out.println("Your array:");

        for(int i = 0; i < array.length; i ++)
            System.out.print(array[i] + " ");

        System.out.println();
    }


    public static void task1(int []array) {
        System.out.println("Task_1: Even numbers in the array:");

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0)
                System.out.print(array[i] + " ");
        }
    }

    public static void task2(int []array){
        System.out.println("Task 2: Quantity of positive numbers in the array:");
        int positiveNumQ = 0;

        for (int i = 0; i < array.length; i++){
            if(array[i] > 0)
                positiveNumQ++;
        }

        System.out.println(positiveNumQ);
    }


    public static void task3(int []array){
        System.out.println("Task 3: Quantity of elements in array that greater than the previous one:");
        int greaterElQ = 0;

        for (int i = 1; i < array.length; i++){
            if(array[i] > array[i-1])
                greaterElQ++;
        }

        System.out.println(greaterElQ);
    }

    public static void task4(int []array){
        System.out.println("Task 4: The number of elements in the array that are greater than its neighbors:");
        int counter = 0;

        for (int i = 1; i < array.length-1; i++){
            if ((array[i] > array[i - 1]) && (array[i] > array[i+1]))
                counter++;
        }

        System.out.println(counter);
    }


    public static void task5(int []array){
        System.out.println("Task 5: Inverting the array");
        int i = 0;
        int j = array.length-1;

        while(i < j){
            array[i] += array[j];
            array[j] = array[i] - array[j];
            array[i] -= array[j];

            i++;
            j--;
        }

        printArray(array);
    }


    public static void task6(int []array){
        System.out.println("Task 6: Swapping adjacent array elements:");
        int last;

        if(array.length % 2 == 0)
            last = array.length;
        else
            last = array.length-1;

        for(int i = 0; i < last; i+=2){
            array[i] += array[i+1];
            array[i+1] = array[i] - array[i+1];
            array[i] -= array[i+1];
        }

        printArray(array);
    }
}