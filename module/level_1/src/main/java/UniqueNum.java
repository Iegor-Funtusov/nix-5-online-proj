import java.util.InputMismatchException;
import java.util.Scanner;

public class UniqueNum {
    public static void control(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = string();
            switch (input){
                case "random":{
                    random();
                }break;
                case "by hand":{
                   byHand();
                }break;
            }
            System.out.println("Do you want to continue? (yes, no)");
            String choice = scanner.next();
            switch (choice){
                case "no":
                {
                    return;
                }
            }
            System.out.println();
        }
    }

    private static String string(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the way you want to input data (random, by hand)");
        String input = scanner.nextLine();
        input = input.toLowerCase();
        return input;
    }
    private static void random (){
        int[] arr = new int[15];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.round((Math.random() * 15) % 15);
            System.out.print(arr[i] + " ");
        }
        System.out.print("\nUnique numbers: " + counter(arr));
        System.out.println();
    }

    private static void byHand(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input number of items: ");
        int items = checkNum();
        int[] arr = new int[items];
        System.out.println("Input your numbers (with space or enter button)");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
            System.out.print(arr[i] + " ");
        }
        System.out.print("\nUnique numbers: " + counter(arr));
        System.out.println();
    }

    private static int counter(int arr[]){
        int counter = 0;
        for(int i = 0; i < arr.length; i++){
            boolean flag = true;
            for (int j = 0; j < arr.length; j++){
                if(i != j) {
                    if (arr[i] == arr[j]) {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag == true){
                counter++;
            }
        }
        return counter;
    }

    private static int checkNum(){
        Scanner scanner = new Scanner(System.in);
        try {
            int num = scanner.nextInt();
            if(num <= 0 || num > 100){
                System.out.println("Number of items can be from 1 to 100. Input again");
                return checkNum();
            }
            return num;
        }
        catch (InputMismatchException ex){
            System.out.println("It is not an integer. Input an integer");
            return checkNum();
        }
    }
}
