public class Task3 {

    public static void main(int[] arr){

        System.out.println("TASK_3");

        int counter=0;

        for(int i=1; i<arr.length; i++){

            if(arr[i]>arr[i-1]) counter++;

        }

        System.out.println(counter);
    }
}
