package com.k4rnaj1k.service;

public interface MathSet<T extends Number & Comparable<T>> {
    void add(T n);

    void add(T... n);

    void join(MathSet<T> ms);

    void join(MathSet<T> ... ms);

    void sortDesc();

    void sortDesc(int firstIndex, int lastIndex);

    void sortDesc(T value);

    void sortAsc();

    void sortAsc(int firstIndex, int lastIndex);

    void sortAsc(T value);

    Number get(int index);

    Number getMax();

    Number getMin();

    Number getAverage();

    Number getMedian();

    Number[] toArray();

    Number[] toArray(int firstIndex, int lastIndex);

    MathSet<T> squash(int firstIndex, int lastIndex);

    void clear();

    void clear(T[] numbers);
}
