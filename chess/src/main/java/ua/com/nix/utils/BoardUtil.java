package ua.com.nix.utils;

public class BoardUtil {

    public static boolean isSpaceWhite(int rowNumber, int columnNumber) {
        return (isEven(rowNumber) && isEven(columnNumber))
                || (!isEven(rowNumber) && !isEven(columnNumber));
    }

    private static boolean isEven(int i){
        return i % 2 == 0;
    }

}
