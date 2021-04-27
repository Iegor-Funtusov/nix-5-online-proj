package util;

public class Part4 {

    public void countElementsBiggerThanPreviousAndNext(int[] arr) {
        System.out.print("Quantity of elements which bigger than previous and than next: ");
        int counter = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                counter++;
                i++;
            }
        }
        System.out.println(" " + counter);
    }
}

