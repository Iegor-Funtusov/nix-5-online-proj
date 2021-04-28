package ua.com.threadedcode;

import ua.com.threadedcode.array.ArrayUtil;

public class Main {
    public static void main(String[] args) {
        ArrayUtil arrayUtil = new ArrayUtil();

        arrayUtil.printEvenNumbers(new int[]{1, 2, 3, 4, 5});
        arrayUtil.printNumOfPositiveNumbers(new int[]{1, 2, 3, -1, -4});
        arrayUtil.printCountOfElemetsBiggerPrevious(new int[]{1, 2, 3, 4, 5});
        arrayUtil.printCountOfElBiggerPrevAndNext(new int[]{1, 5, 1, 5, 1});
        arrayUtil.revertArray(new int[]{4, 5, 3, 4, 2, 3});
        arrayUtil.swapAdjacentElements(new int[]{4, 5, 3, 4, 2, 3});
    }
}
