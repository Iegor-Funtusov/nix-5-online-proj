package MathSet;

public interface InterfaceMS<E> extends Iterable<Number> {
    void add(E n);
    void add(E ... n);
//    void join(MathSet mathset);
//    void join(MathSet ... mathset);
    void sortDesc();
    void sortDesc(int firstIndex, int lastIndex);
    void sortDesc(E value);
    void sortAsc();
    void sortAsc(int firstIndex, int lastIndex);
    void sortAsc(E value);
    E get(int index);
    E getMax();
    E getMin();
    Number getAverage();
    Number getMedian();
//    E[] toArray();
//    E[] toArray(int firstIndex, int lastIndex);
    MathSet squash(int firstIndex, int lastIndex);
    void clear();
    void clear(Number[] numbers);
    int length();
}
