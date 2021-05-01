public class Task2 {

    public static void main(int[] arr){

        System.out.println("\nTASK_2");

        int counter = 0;

        for (int i : arr) {
            if (i > 0) {
                counter++;
            }
        }

        System.out.println(counter);
    }
}

