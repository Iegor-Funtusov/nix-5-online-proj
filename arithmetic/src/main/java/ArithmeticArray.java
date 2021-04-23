public class ArithmeticArray {

    public static void printArray(int[]a){
        for (int value : a) {
            System.out.print(value + " ");
        }
        System.out.println(" ");
    }

    public static void evenNumbers(int[]a ){
        System.out.println("Print even elements: ");
        for (int value : a) {
            if (value % 2 == 0) {
                System.out.print(value + " ");
            }
        }
        System.out.println(" ");
    }

    public static void positiveElementsCount(int []a){
        System.out.println("Print the number of positive elements:");
        int count = 0;
        for (int value : a) {
            if (value > 0) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void previousLessThanNextCount(int []a){
        System.out.println("Print the number of elements, that have a smaller previous element: ");
        int count = 0;
        for(int i = 0; i<a.length-1; i++){
                if (a[i] < a[i+1]) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void previousAndNextLessCount(int []a){
        System.out.println("Print the number of elements, that have smaller previous and next elements: ");
        int count = 0;
        for(int i = 1; i<a.length-1; i++){
            if (a[i-1]<a[i] && a[i+1]<a[i]) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void arrayReverse(int []a){
        System.out.println("Before:");
        ArithmeticArray.printArray(a);
        System.out.println("Print reverse array: ");
        for(int i = 0; i<(a.length/2); i++){
            int temp = a[i];
            a[i] = a[a.length-i-1];
            a[a.length-i-1] = temp;
        }
        ArithmeticArray.printArray(a);
    }


    public static void arrayReverseNeighbour(int[]a){
        System.out.println("Before:");
        ArithmeticArray.printArray(a);
        System.out.println("Print reverse of each two elements: ");
        int arraylength = a.length;
        if(a.length%2!=0){
            arraylength=arraylength-1;
        }
        for (int i=0 ; i < arraylength; i++) {
                if(i%2==0){
                int temp = a[i + 1];
                a[i + 1] = a[i];
                a[i] = temp;
            }
        }
        ArithmeticArray.printArray(a);
    }

}




