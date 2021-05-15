package com.nixcource.level1;

import java.util.HashMap;

public class UniqueSymbols {

    public static int countUniqueSymbolsInArray(int[] data) {
        HashMap<Integer, Integer> uniqueSymbols = new HashMap<>();

        for (int i : data) {
            uniqueSymbols.put(i, 0);
        }
        System.out.println(uniqueSymbols);

        return uniqueSymbols.keySet().size();
    }
}
