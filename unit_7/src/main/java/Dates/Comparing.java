package Dates;

import Controllers.DateTypes;
import Controllers.InputOutput;

import java.util.Arrays;
import java.util.Scanner;


public class Comparing {
    public static void compare() {
        System.out.println("if you want to return to selection of functions input \"R\"\nInput dates that you want to compare (if you want to end inputting just input \"stop\"):");
        int[][] dates = new int[1][7];
        int[][] dates1;
        int m = 0, format = 0;
        while (true) {
            System.out.println("Your date (R - return, F - format):");
            int[] arr = DateTypes.dateControl(format);
            if (arr[0] == -2)
                return;
            if (arr[0] != -1) {
                dates = Arrays.copyOf(dates, dates.length + 1);
                dates[m] = arr;
            } else {
                dates1 = Arrays.copyOf(dates, dates.length - 1);
                dates = dates1;
                break;
            }
            format = dates[m][6];
            m++;
        }

        System.out.println("Unsorted dates:");
        for (int i = 0; i < dates.length; i++) {
            InputOutput.Output(dates[i]);
        }

        for (int k = 0; k < dates.length; k++) {
            for (int d = k+1; d < dates.length; d++) {
                if (Difference.checkOrder(dates[k], dates[d], 2) == false) {
                     int[] swap = dates[k];
                     dates[k] = dates[d];
                     dates[d] = swap;
                }
                System.out.println("Sorted dates:" + d);
                for (int i = 0; i < dates.length; i++) {
                    InputOutput.Output(dates[i]);
                }
            }
        }

        System.out.println("How would you like to display dates:\n" +
                "1 >> descending\n" +
                "2 >> ascending");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        while (true){
            switch (string) {
                case "1": {
                    System.out.println("Sorted dates:");
                    for (int i = dates.length-1; i >= 0; i--) {
                        InputOutput.Output(dates[i]);
                    }
                    return;
                }
                case "2": {
                    System.out.println("Sorted dates:");
                    for (int i = 0; i < dates.length; i++) {
                        InputOutput.Output(dates[i]);
                    }
                    return;
                }
                default: {
                    System.out.println("Wrong input. Input again");
                    string = scanner.nextLine();
                }
            }
        }
    }
}
