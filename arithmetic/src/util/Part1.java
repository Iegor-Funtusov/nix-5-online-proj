package util;

public class Part1 {

    public void outputWholeNumbers(int [] args) {
        System.out.print("Output whole numbers: ");
        for (int i = 1; i < args.length; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}

