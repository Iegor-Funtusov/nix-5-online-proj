package lev1.array;

import java.util.Scanner;

public class ArrGo {
    public static void RunArr(){
        Scanner input = new Scanner(System.in);
        System.out.println("__________\n'1'-random array\n'2'-your array\n_________");
        int ch = input.nextInt();
        switch (ch){
            case 2:{
                System.out.println("Enter array length: ");
                int size = input.nextInt();
                int array[] = new int[size];
                System.out.println("Insert array elements:");
                for (int i = 0; i < size; i++) {
                    array[i] = input.nextInt();
                }
                UniqInArr.uniqElem(array);
                break;
            }
            case 1:{
                System.out.println("Enter array length: ");
                int size = input.nextInt();
                int[] array;
                array = new int[size];
                System.out.print("Array : [");
                for (int i = 0; i < array.length; i++) {
                    array[i] = ((int)(Math.random() * 12) - 15);
                    System.out.print(array[i]+";");
                }
                System.out.println("]");
                UniqInArr.uniqElem(array);
                break;
            }
        }




    }
}
