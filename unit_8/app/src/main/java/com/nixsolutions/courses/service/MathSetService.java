package com.nixsolutions.courses.service;

import com.nixsolutions.courses.MathSet;
import com.nixsolutions.courses.MathSetNumber;

import java.util.Random;

public class MathSetService<E extends Number & Comparable<E>> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int RAND_MIN = 0;
    private static final int RAND_MAX = 100;

    private MathSet<E> mathSet;

    public int getSize() {
        return mathSet.getSize();
    }

    public void create() {
        mathSet = new MathSetNumber<>();
    }

    public void createRandom() {
        Integer[] arr = new Random().ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        mathSet = new MathSetNumber(arr);
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
