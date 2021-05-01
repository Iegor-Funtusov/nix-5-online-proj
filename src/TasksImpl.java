public class TasksImpl {

    public static void findEven() {

        System.out.println("TASK_1");

        int[] arr = InOutUtils.inputData();

        System.out.println("Even numbers:");

        for (int i : arr) {
            if (i % 2 == 0) {
                System.out.print(i+" ");
            }
        }
    }

    public static void countPositive() {

        System.out.println("TASK_2");

        int[] arr = InOutUtils.inputData();
        int counter = 0;

        for (int i : arr) {
            if (i > 0) {
                counter++;
            }
        }

        System.out.println("Positive amount:");
        System.out.print(counter);
    }

    public static void countGreaterThanPrev() {

        System.out.println("TASK_3");

        int[] arr = InOutUtils.inputData();
        int counter=0;

        for(int i=1; i<arr.length; i++){

            if(arr[i]>arr[i-1]) counter++;

        }

        System.out.println("Greater than previous amount:");
        System.out.print(counter);
    }

    public static void countGreaterThanNeighbours() {

        System.out.println("TASK_4");

        int[] arr = InOutUtils.inputData();
        int counter=0;

        for(int i=1; i<arr.length-1; i++){

            if(arr[i]>arr[i-1] && arr[i]>arr[i+1]){
                counter++;
                i++;
            };

        }

        System.out.println("Greater than neighbours amount:");
        System.out.print(counter);
    }

    public static void printReverseArray() {

        System.out.println("TASK_5");

        int[] arr = InOutUtils.inputData();

        for(int i=0; i < arr.length/2; i++){

            int tmp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = tmp;

        }

        System.out.println("Reversed array:");

        InOutUtils.printArray(arr);
    }

    public static void printReverseNeighboursArray() {

        System.out.println("TASK_6");

        int[] arr = InOutUtils.inputData();

        for(int i=0; i < arr.length-1; i+=2){

            int tmp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = tmp;

        }

        System.out.println("Reversed neighbours array:");

        InOutUtils.printArray(arr);
    }
}
