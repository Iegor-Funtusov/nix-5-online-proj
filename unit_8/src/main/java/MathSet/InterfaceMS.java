package MathSet;

public interface InterfaceMS extends Iterable<Number> {
    void add(Number n);
    void add(Number ... n);
    void join(MathSet mathset);
    void join(MathSet ... mathset);
    void sortDesc();
    void sortDesc(int firstIndex, int lastIndex);
    void sortDesc(Number value);
    void sortAsc();
    void sortAsc(int firstIndex, int lastIndex);
    void sortAsc(Number value);
    Number get(int index);
    Number getMax();
    Number getMin();
    Number getAverage();
    Number getMedian();
    Number[] toArray();
    Number[] toArray(int firstIndex, int lastIndex);
    MathSet squash(int firstIndex, int lastIndex);
    void clear(Number[] numbers);
    int length();
}
