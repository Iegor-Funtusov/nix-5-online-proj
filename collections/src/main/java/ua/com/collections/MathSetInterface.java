package ua.com.collections;

public interface MathSetInterface<N extends Number & Comparable<N>> {

    void add(N n);
    void add(N ... n);
    void join(MathSet ms);
    void join(MathSet ... ms);
    void sortDesc();
    void sortDesc(int firstIndex, int lastIndex);
    void sortDesc(N value);
    void sortAsc();
    void sortAsc(int firstIndex, int lastIndex);
    void sortAsc(Number value);
    N get(int index);
    N getMax();
    N getMin();
    N getAverage();
    N getMedian();
    N [] toArray();
    N[] toArray(int firstIndex, int lastIndex);
    MathSet squash(int firstIndex, int lastIndex);
    void clear();
    void clear(Number[] numbers);
}
