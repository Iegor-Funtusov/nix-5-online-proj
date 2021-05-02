package utils;

public class KoordsUtils {

    public static int defineY(char Y){

        switch (Y) {
            case 'A' -> {
                return 0;
            }
            case 'B' -> {
                return 1;
            }
            case 'C' -> {
                return 2;
            }
            case 'D' -> {
                return 3;
            }
            case 'E' -> {
                return 4;
            }
            case 'F' -> {
                return 5;
            }
            case 'G' -> {
                return 6;
            }
            case 'H' -> {
                return 7;
            }
            default -> System.out.println("Wrong choice. Restart the program.");
        }
        return -1;
    }
}
