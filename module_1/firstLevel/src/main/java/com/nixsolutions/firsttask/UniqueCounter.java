package com.nixsolutions.firsttask;

import java.util.HashSet;
import java.util.Set;

public class UniqueCounter
{
    // task 1.1
    public static int uniqueCount(int[] array) {
        Set<Integer> mySet = new HashSet<>();
        for (int x : array) {
            mySet.add(x);
        }
        System.out.println("This array contains " + mySet.size() + " uniques elements");
        return mySet.size();
    }
}
