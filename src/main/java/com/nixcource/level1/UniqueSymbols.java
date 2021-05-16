package com.nixcource.level1;

import java.util.ArrayList;
import java.util.HashMap;

public class UniqueSymbols {

    public static int countUniqueSymbolsInArray(ArrayList<Integer> data) {
        HashMap<Integer, Integer> uniqueSymbols = new HashMap<>();

        for (int i : data) {
            uniqueSymbols.put(i, 0);
        }

        return uniqueSymbols.keySet().size();
    }
}
