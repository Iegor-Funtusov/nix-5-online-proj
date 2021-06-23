package com.nixsolutions.courses.utils;

import java.util.NoSuchElementException;

public class ArrayUtils {

    public static <T> T[] copyOf(T[] original, int newLength) {
        T[] copy = (T[]) new Object[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
//        for (int i = 0; i < original.length; i++) {
//            copy[i] = original[i];
//        }
        return copy;
    }

    public static <T> int indexOf(T element, Object[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) return i;
        }
        throw new NoSuchElementException("No such element found in array");
    }
}
