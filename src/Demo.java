import java.util.Scanner;

public class Demo {

        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            printMenu();
            while (true) {
                int N = Integer.parseInt(scanner.nextLine());
                switch (N) {
                    case 0 -> System.exit(0);
                    case 1 -> TasksImpl.findEven();
                    case 2 -> TasksImpl.countPositive();
                    case 3 -> TasksImpl.countGreaterThanPrev();
                    case 4 -> TasksImpl.countGreaterThanNeighbours();
                    case 5 -> TasksImpl.printReverseArray();
                    case 6 -> TasksImpl.printReverseNeighboursArray();
                }
                printMenu();
            }
        }

    public static void printMenu() {
        System.out.println("""
                
                -------------------------------------
                1 - print even numbers
                2 - count positive numbers
                3 - count elements greater than previous
                4 - count elements greater than neighbors
                5 - reverse array
                6 - reverse elements' position in array
                0 - exit
                Choose task:""");
    }

}
