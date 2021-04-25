public class Part4 {

    public void countElementsBiggerThanPreviousAndNext(int[] arr) {
        int counter = 0;

        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                counter++;
                i++;
            }
        }
        System.out.println(counter);
    }
}

