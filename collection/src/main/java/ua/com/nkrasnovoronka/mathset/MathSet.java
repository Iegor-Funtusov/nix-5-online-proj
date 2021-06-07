package ua.com.nkrasnovoronka.mathset;

public interface MathSet extends Iterable {

    void add(Number n);

    void add(Number... n);

    void join(MathSet ms);

    void join(MathSet... ms);

    void sortDesc();

    void sortDesc(int firstIndex, int lastIndex);

    void sortDesc(Number value);

    void sortAsc();

    void sortAsc(int firstIndex, int lastIndex);

    void sortAsc(Number value);

    Number get(int index);

    boolean contains(Number n);

    int getSize();

    Number getMax();

    Number getMin();

    Number getAverage();

    Number getMedian();

    Number[] toArray();

    Number[] toArray(int firstIndex, int lastIndex);

    MathSet squash(int firstIndex, int lastIndex);

    void clear();

    void clear(Number[] numbers);

    String toString();
}
