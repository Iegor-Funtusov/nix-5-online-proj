public class Task6 {

    public static void main(int[] arr){

        System.out.println("\nTASK_6");

        for(int i=0; i < arr.length-1; i+=2){

            int tmp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = tmp;

        }

        for (int i : arr) {

            System.out.print(i);

        }

    }
}