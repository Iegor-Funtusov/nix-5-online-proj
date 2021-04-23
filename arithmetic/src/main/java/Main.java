import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int number;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of elements: from 1 to 100:");
        number = scanner.nextInt();
        int [] arr = new int[number];
        System.out.println("Enter elements:");
        for(int i = 0; i < number; i++)
        {
            arr[i] = scanner.nextInt();
        }
        System.out.println("Your array: ");
        ArithmeticArray.printArray(arr);

        ArithmeticArray.evenNumbers(arr);
        ArithmeticArray.positiveElementsCount(arr);
        ArithmeticArray.previousLessThanNextCount(arr);
        ArithmeticArray.previousAndNextLessCount(arr);
        //next methods exchange array!!!!!
        ArithmeticArray.arrayReverse(arr);
        ArithmeticArray.arrayReverseNeighbour(arr);
    }
}
