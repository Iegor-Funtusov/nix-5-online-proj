package lev1.array;

import java.util.HashSet;
import java.util.Set;

public class UniqInArr {

    public static void uniqElem(int[] array) {
        Set<Integer> mySet = new HashSet<>();
        for (int x : array) {
            mySet.add(x);
        }
        System.out.println("Count of uniques elements = " + mySet.size());
    }

}
