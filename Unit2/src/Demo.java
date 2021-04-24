import java.util.Scanner;

public class Demo {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        System.out.println("Array size: ");
        int size = in.nextInt();

        if(size<0 || size>100){
            System.out.println("Array size must be in 0 <= size <= 100 renge");
            return;
        }

        System.out.println("Enter array elements");

        int[] arr = new int[size];
        for (int i=0; i<size; i++){
            arr[i]=in.nextInt();
        }

        Task1.main(arr);
        Task2.main(arr);
        Task3.main(arr);
        Task4.main(arr);
        Task5.main(arr);
        Task6.main(arr);

    }
}
