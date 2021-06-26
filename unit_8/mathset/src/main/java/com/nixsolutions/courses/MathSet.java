package com.nixsolutions.courses;

public interface MathSet<E extends Number & Comparable<E>> {

    void add(E n);

    void add(E ... n);

    void join(MathSet<E> ms);

    void join(MathSet<E> ... ms);

    void sortDesc();

    void sortDesc(int firstIndex, int lastIndex) throws IndexOutOfBoundsException;

    void sortDesc(E value);

    void sortAsc();

    void sortAsc( int firstIndex, int lastIndex) throws IndexOutOfBoundsException;

    void sortAsc(E value);

    E get(int index) throws IllegalStateException;

    E getMax() throws IllegalStateException;

    E getMin() throws IllegalStateException;

    Number getAverage() throws IllegalStateException;

    Number getMedian() throws IllegalStateException;

    int getSize();

    E[] toArray();

    E[] toArray(int firstIndex, int lastIndex) throws IllegalArgumentException, IndexOutOfBoundsException;

    MathSet<E> squash(int firstIndex, int lastIndex);

    void clear();

    void clear(E[] numbers);
}
