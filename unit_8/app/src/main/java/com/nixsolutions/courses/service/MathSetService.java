package com.nixsolutions.courses.service;

import com.nixsolutions.courses.MathSet;
import com.nixsolutions.courses.MathSetNumber;

public class MathSetService<E extends Number> {

    private MathSet<E> mathSet;

    public MathSet<E> getMathSet() {
        return mathSet;
    }

    public boolean create() {
        mathSet = new MathSetNumber();
        return !isNull();
    }
    public MathSet<E> createNewSet(E[] numbers) {
        return new MathSetNumber(numbers);
    }

    public boolean add(E[] numbers) {
        int oldSize = mathSet.getSize();
        mathSet.add(numbers);
        return mathSet.getSize() > oldSize;
    }

    public boolean join(MathSet<E> ms) {
        int oldSize = mathSet.getSize();
        mathSet.join(ms);
        return mathSet.getSize() > oldSize;
    }

    public boolean sortDesc() {
        mathSet.sortDesc();
        boolean descending = true;
        Number[] a = mathSet.toArray();
        for (int i = 0; i < a.length - 1; i++) {
            if (((Integer) a[i]).compareTo((Integer) a[i + 1]) < 0) {
                descending = false;
                break;
            }
        }
        return descending;
    }
    public boolean sortAsc() {
        mathSet.sortAsc();
        boolean ascending = true;
        Number[] a = mathSet.toArray();
        for (int i = 0; i < a.length - 1; i++) {
            if (((Integer) a[i]).compareTo((Integer) a[i + 1]) > 0) {
                ascending = false;
                break;
            }
        }
        return ascending;
    }

    public E get(int index) {
        return mathSet.get(index);
    }

    public Number getMin() {
        return mathSet.getMin();
    }

    public Number getMax() {
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

    public Number[] toArray(MathSet<E> set) {
        return set.toArray();
    }

    public void clear() {
        mathSet.clear();
    }

    public int getSize() {
        return mathSet.getSize();
    }

    public boolean isNull() {
        return mathSet == null;
    }
}
