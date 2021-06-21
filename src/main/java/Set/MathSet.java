package Set;

public interface MathSet<E extends Number>{
    void add(E n);

    void add(E ... n);

    void join(MathSet ms);

    void join(MathSet ... ms);

    void sortDesc();

    void sortDesc(int firstIndex, int lastIndex);

    void sortDesc(E value);

    void sortAsc();

    void sortAsc(int firstIndex, int lastIndex);

    void sortAsc(E value);

    E get(int index);

    E getMax();

    E getMin();

    Double getAverage();

    Double getMedian();

    Number[] toArray();

    Number[] toArray(int firstIndex, int lastIndex);

    MathSet squash(int firstIndex, int lastIndex);

    void clear();

    void clear(E[] numbers);

    int size();

    Node getTop();
}