package com.nixsolutions.courses;

public interface MathSet<E extends Comparable> {

    void add(E n);

    void add(E ... n);

    void join(MathSet<E> ms);

    void join(MathSet<E> ... ms);

    void sortDesc();

    void sortDesc(int firstIndex, int lastIndex);

    void sortDesc(E value);

    void sortAsc();

    void sortAsc( int firstIndex, int lastIndex);

    void sortAsc(E value);

    E get(int index);

    E getMax();

    E getMin();

    E getAverage();

    E getMedian();

    int getSize();

    Object[] toArray();

    Object[] toArray(int firstIndex, int lastIndex);

    MathSet squash(int firstIndex, int lastIndex);

    void clear();

    void clear(E[] numbers);
}
