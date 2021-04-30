import java.util.Scanner;

public class EnterPoint {
    public static void main(String[] args) {
        int number;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of elements: from 1 to 100:");
        number = scanner.nextInt();
        while(true) {
            if(number > 100 || number <= 1){
                System.out.println("Please, enter correct number: from 1 to 100");
                number = scanner.nextInt();
            }
            else{
                break;
            }
        }
            int[] arr = new int[number];
            int[] arr2 = new int[number];
            System.out.println("Enter elements:");
            for (int i = 0; i < number; i++) {
                arr[i] = arr2[i] = scanner.nextInt();
            }
            System.out.println("Your array: ");
            ArithmeticArray.printArray(arr);

            System.out.print("What would you like to get: ");
            System.out.println(" ");
            System.out.println("1 Get even numbers ");
            System.out.println("2 Get number of positive elements");
            System.out.println("3 Get number of elements, that have a smaller previous element");
            System.out.println("4 Get number of elements, that have smaller previous and next elements");
            System.out.println("5 Get reverse array");
            System.out.println("6 Get reverse of neighbours in array");

            int chosenoeration = scanner.nextInt();
            switch (chosenoeration){
                case (1):
                    ArithmeticArray.evenNumbers(arr);
                    break;
                case (2):
                    ArithmeticArray.positiveElementsCount(arr);
                    break;
                case (3):
                    ArithmeticArray.previousLessThanNextCount(arr);
                    break;
                case (4):
                    ArithmeticArray.previousAndNextLessCount(arr);
                    break;
                case (5):
                    ArithmeticArray.arrayReverse(arr);
                    break;
                case (6):
                    ArithmeticArray.arrayReverseNeighbour(arr);
                    break;
                default:
                    ArithmeticArray.evenNumbers(arr);
                    ArithmeticArray.positiveElementsCount(arr);
                    ArithmeticArray.previousLessThanNextCount(arr);
                    ArithmeticArray.previousAndNextLessCount(arr);
                    ArithmeticArray.arrayReverse(arr);
                    ArithmeticArray.arrayReverseNeighbour(arr2);
            }
        }
    }

