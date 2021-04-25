import java.util.Scanner;

public class Demo {

    public static int[] createArray() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the planned size of your array: ");
        int size = sc.nextInt();
        int[] myArray = new int[size];
        System.out.println("Please enter the elements of the array: ");
        for (int i = 0; i < size; i++) {
            myArray[i] = sc.nextInt();
        }
        return myArray;
    }

    public static void main(String[] args) {
        int[] array = createArray();

        System.out.println("Part1");
        Part1 part1 = new Part1();
        part1.outputWholeNumbers(array);
        System.out.println("Part2");
        Part2 part2 = new Part2();
        part2.quantityPositiveNumbers(array);
        System.out.println("Part3");
        Part3 part3 = new Part3();
        part3.quantityElementsBiggerPrevious(array);
        System.out.println("Part4");
        Part4 part4 = new Part4();
        part4.countElementsBiggerThanPreviousAndNext(array);
        System.out.println("Part5");
        Part5 part5 = new Part5();
        part5.reverseMassive(array);
        System.out.println("Part6");
        Part6 part6 = new Part6();
        part6.arrayTransposition(array);
    }
}
