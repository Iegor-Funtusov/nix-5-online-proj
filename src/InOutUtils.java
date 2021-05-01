import java.util.Scanner;

public class InOutUtils {

    public static int[] inputData() {

        Scanner in = new Scanner(System.in);
        System.out.println("Array size: ");
        int size = in.nextInt();

        if(size<1 || size>100){
            System.out.println("Array size must be in 1 <= size <= 100 range");
            return null;
        }

        System.out.println("Enter array elements");

        int[] arr = new int[size];
        for (int i=0; i<size; i++){
            arr[i]=in.nextInt();
        }

        return arr;
    }

    public static void printArray(int[] arr) {

        for (int i: arr) {
            System.out.print(i + " ");
        }
    }
}
