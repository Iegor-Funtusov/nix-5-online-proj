public class Task4 {

    public static void main(int[] arr){

        System.out.println("TASK_4");

        int counter=0;

        for(int i=1; i<arr.length-1; i++){

            if(arr[i]>arr[i-1] && arr[i]>arr[i+1]){
                counter++;
                i++;
            };

        }

        System.out.println(counter);
    }
}
