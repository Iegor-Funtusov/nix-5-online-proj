package com.k4rnaj1k.controller;

import com.k4rnaj1k.service.MathSet;
import com.k4rnaj1k.service.impl.MathSetImpl;

public class MathSetTest {
    public static void main(String[] args) {
        MathSet<Integer> set = new MathSetImpl<>();
        set.add(1);
//        set.add(1.5); - will not work, since we're working with integers.
        set.add(-5);
        set.sortAsc();
        printSet(set);
        set.sortDesc();
        printSet(set);
        set.sortAsc(0,2);
        printSet(set);
        System.out.println("Trying to add repeating nums to set.");
        set.add(5, 3, 5, 6, 4);
        printSet(set);
        System.out.println("Sorted");
        set.sortAsc();
        printSet(set);
        System.out.println("Creating another set");
        MathSet<Integer> set1 = new MathSetImpl<>(set);
        printSet(set1);
        set1.add(10);
        System.out.println("Added another number to second set");
        printSet(set1);
        System.out.println("Created another set");
        MathSet<Integer> set2 = new MathSetImpl<>();
        set2.add(7,13,-3);
        printSet(set2);
        System.out.println("Adding the third set to the second set");
        set1.join(set2);
        printSet(set1);
    }

    private static void printSet(MathSet set){
        for (Number n :
                set.toArray()) {
            System.out.println(n);
        }
    }
}
