package com.nixsolutions.courses.utils;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class ArrayUtils {

    public static <T> T[] copyOfRange(T[] original, int firstIndex, int lastIndex, Class<? extends T[]> newType) {
        int length = lastIndex - firstIndex + 1;
        T[] copy = ((Object)newType == (Object)Object[].class)
                ? (T[]) new Object[length]
                : (T[]) Array.newInstance(newType.getComponentType(), length);
        System.arraycopy(original, firstIndex, copy, 0, length);
        return copy;
    }

    public static <T> T[] copyOf(T[] original, int length, Class<? extends T[]> newType) {
        T[] copy = ((Object)newType == (Object)Object[].class)
                ? (T[]) new Object[length]
                : (T[]) Array.newInstance(newType.getComponentType(), length);
        System.arraycopy(original, 0, copy, 0, original.length);
        return copy;
    }

    public static <T> int indexOf(T element, Object[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (array[i].equals(element)) return i;
            }
        }
        throw new NoSuchElementException("No such element found in array: " + element);
    }
}
