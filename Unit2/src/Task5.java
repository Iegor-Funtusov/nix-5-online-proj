public class Task5 {

    public static void main(int[] arr){

        System.out.println("TASK_5");

        for(int i=0; i < arr.length/2; i++){

            int tmp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = tmp;

        }

        for (int i : arr) {

            System.out.print(i);
        }

    }
}