package com.nixsolutions.courses.service;

import com.nixsolutions.courses.MathSet;
import com.nixsolutions.courses.MathSetNumber;

public class MathSetService<E extends Number & Comparable<E>> {

    private MathSet<E> mathSet;

    public int getSize() {
        return mathSet.getSize();
    }

    public boolean create() {
        mathSet = new MathSetNumber<>();
        return !isNull();
    }

    public MathSet<E> createNewSet(E[] numbers) {
        return new MathSetNumber<>(numbers);
    }

    public void add(E[] numbers) {
        mathSet.add(numbers);
    }

    public void join(MathSet<E> ms) {
        mathSet.join(ms);
    }

    public void sortDesc() {
        mathSet.sortDesc();
    }
    public void sortAsc() {
        mathSet.sortAsc();
    }

    public E get(int index) {
        return mathSet.get(index);
    }

    public E getMin() {
        return mathSet.getMin();
    }

    public E getMax() {
        return mathSet.getMax();
    }

    public Number getAverage() {
        return mathSet.getAverage();
    }

    public Number getMedian() {
        return mathSet.getMedian();
    }

    public MathSet<E> squash(int firstIndex, int lastIndex) {
        return mathSet.squash(firstIndex, lastIndex);
    }

    public Number[] toArray() {
        return mathSet.toArray();
    }

    public void clear() {
        mathSet.clear();
    }

    public String toString() {
        return mathSet.toString();
    }

    public boolean isNull() {
        return mathSet == null;
    }
}
