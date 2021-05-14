package ua.com.alevel.lib;

import java.util.HashSet;
import java.util.Set;

public class UniqueSymbolsSearcher {

    public static int countUnique(int[] arr) {
        Set<Integer> mySet = new HashSet<>();
        for (int x : arr) {
            mySet.add(x);
        }
        return mySet.size();
    }
}
