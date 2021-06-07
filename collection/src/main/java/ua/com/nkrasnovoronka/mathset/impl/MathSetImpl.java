package ua.com.nkrasnovoronka.mathset.impl;

import ua.com.nkrasnovoronka.mathset.MathSet;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class MathSetImpl implements MathSet {

    private int size = 0;
    private final int DEFAULT_CAPACITY = 16;
    private Number[] numbers;

    public MathSetImpl() {
        this.numbers = new Number[DEFAULT_CAPACITY];
    }

    public MathSetImpl(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("MathSet capacity cannot be less or equal 0");
        }
        this.numbers = new Number[capacity];
    }

    public MathSetImpl(Number[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Numbers array cannot be null");
        }
        this.numbers = numbers;
        size = numbers.length;
    }

    public MathSetImpl(Number[]... numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Numbers array cannot be null");
        }
        this.numbers = new Number[numbers.length];
        for (Number[] n : numbers) {
            add(n);
        }
    }

    public MathSetImpl(MathSet numbers) {
        this(numbers.toArray());
    }

    public MathSetImpl(MathSet... numbers) {
        for(MathSet mathSet:numbers){
            add(mathSet.toArray());
        }
    }

    @Override
    public void add(Number n) {
        resizeIfNeeded();
        if (!contains(n)) {
            numbers[size] = n;
            size++;
        }
    }

    @Override
    public void add(Number... n) {
        for (Number cur : n) {
            if (!contains(cur)) {
                add(cur);
            }
        }
    }

    @Override
    public void join(MathSet ms) {
        Number[] numbers = ms.toArray();
        for (Number n : numbers) {
            add(n);
        }
    }

    @Override
    public void join(MathSet... ms) {
        for(MathSet mathSet: ms){
            join(mathSet);
        }
    }

    @Override
    public void sortDesc() {

    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {

    }

    @Override
    public void sortDesc(Number value) {

    }

    @Override
    public void sortAsc() {

    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {

    }

    @Override
    public void sortAsc(Number value) {

    }

    @Override
    public Number get(int index) {
        Objects.checkIndex(index, size);
        return numbers[index];
    }

    @Override
    public boolean contains(Number n) {
        for (int i = 0; i < size; i++) {
            if (numbers[i].equals(n)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Number getMax() {
        return null;
    }

    @Override
    public Number getMin() {
        return null;
    }

    @Override
    public Number getAverage() {
        return null;
    }

    @Override
    public Number getMedian() {
        return null;
    }

    @Override
    public Number[] toArray() {
        Number[] toArray = new Number[size];
        System.arraycopy(numbers, 0, toArray, 0, size);
        return toArray;
    }

    @Override
    public Number[] toArray(int firstIndex, int lastIndex) {
        return new Number[0];
    }

    @Override
    public MathSet squash(int firstIndex, int lastIndex) {
        return null;
    }

    @Override
    public void clear() {
        numbers = new Number[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void clear(Number[] numbers) {

    }

    @Override
    public Iterator iterator() {
        return null;
    }

    private void resizeIfNeeded() {
        if (numbers.length == size) {
            Number[] newElements = new Number[(numbers.length * 3) / 2 + 1];
            System.arraycopy(numbers, 0, newElements, 0, size);
            numbers = newElements;
        }
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            sj.add(numbers[i].toString());
        }
        return sj.toString();
    }
}
