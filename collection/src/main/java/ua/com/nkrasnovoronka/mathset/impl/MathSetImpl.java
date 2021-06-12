package ua.com.nkrasnovoronka.mathset.impl;

import ua.com.nkrasnovoronka.mathset.MathSet;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;
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
        for (MathSet mathSet : numbers) {
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
        for (MathSet mathSet : ms) {
            join(mathSet);
        }
    }

    @Override
    public void sortDesc() {
        quickSort(numbers, 0, size - 1, true);
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        isValidIndex(firstIndex, lastIndex);
        Number[] sort = toArray(firstIndex, lastIndex);
        quickSort(sort, 0, sort.length - 1, true);
        System.arraycopy(sort, 0, numbers, firstIndex, sort.length);
    }

    @Override
    public void sortDesc(Number value) {
        int index = getIndex(value);
        if(index != -1){
            sortDesc(index, size - 1);
        }
    }

    @Override
    public void sortAsc() {
        quickSort(numbers, 0, size - 1, false);
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        isValidIndex(firstIndex, lastIndex);
        Number[] sort = toArray(firstIndex, lastIndex);
        quickSort(sort, 0, sort.length - 1, false);
        System.arraycopy(sort, 0, numbers, firstIndex, sort.length);
    }

    @Override
    public void sortAsc(Number value) {
        int index = getIndex(value);
        if(index != -1){
            sortAsc(index, size - 1);
        }
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
        Number[] n = numbers;
        quickSort(n, 0, size - 1, true);
        return n[0];
    }

    @Override
    public Number getMin() {
        Number[] n = numbers;
        quickSort(n, 0, size - 1, false);
        return n[0];
    }

    @Override
    public Number getAverage() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += numbers[i].doubleValue();
        }

        return sum / size;
    }

    @Override
    public Number getMedian() {
        sortAsc();
        if(size %2 != 0){
            return numbers[size / 2];
        }
        double first = numbers[size / 2 - 1].doubleValue();
        double second = numbers[size / 2].doubleValue();
        return( first + second) / 2;
    }

    @Override
    public Number[] toArray() {
        Number[] toArray = new Number[size];
        System.arraycopy(numbers, 0, toArray, 0, size);
        return toArray;
    }

    @Override
    public Number[] toArray(int firstIndex, int lastIndex) {
        isValidIndex(firstIndex, lastIndex);
        Number[] subNumber = new Number[lastIndex - firstIndex + 1];
        System.arraycopy(numbers, firstIndex, subNumber, 0, subNumber.length);
        return subNumber;
    }

    @Override
    public MathSet squash(int firstIndex, int lastIndex) {
        isValidIndex(firstIndex, lastIndex);
        return new MathSetImpl(toArray(firstIndex, lastIndex));
    }

    @Override
    public void clear() {
        numbers = new Number[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void clear(Number[] numbers) {
        for (Number n : numbers) {
            for (int i = 0; i < size; i++) {
                if (n.equals(this.numbers[i])) {
                    this.numbers[i] = null;
                }
            }
        }
        clearFromNulls();
    }

    private void clearFromNulls() {
        Number[] n = new Number[DEFAULT_CAPACITY];
        int curSize = 0;
        int index = 0;
        for (int i = 0; i < this.size; i++) {
            if (numbers[i] != null) {
                n[index++] = numbers[i];
                curSize++;
            }
        }
        numbers = n;
        this.size = curSize;


    }

    @Override
    public Iterator<Number> iterator() {
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Number next() {
                if (hasNext()) {
                    return numbers[index++];
                }
                throw new NoSuchElementException();
            }
        };
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

    private void isValidIndex(int firstIndex, int lastIndex) {
        if ((firstIndex >= lastIndex || firstIndex > size) || (lastIndex > size)) {
            throw new IllegalArgumentException("Pleas check index input");
        }
    }

    private void quickSort(Number[] num, int from, int to, boolean desk) {

        if (from < to) {
            int pi = partition(num, from, to, desk);
            if (desk) {
                quickSort(num, from, pi, true);
                quickSort(num, pi + 1, to, true);
            } else {
                quickSort(num, from, pi - 1, false);
                quickSort(num, pi + 1, to, false);
            }
        }
    }

    private int partition(Number[] num, int from, int to, boolean desk) {
        double pivot;
        int i;
        if (desk) {
            i = from;
            pivot = num[from].doubleValue();
            for (int j = from + 1; j <= to; j++) {
                if (num[j].doubleValue() > pivot) {
                    i++;
                    swap(num, i, j);
                }
            }
            swap(num, i, from);
            return i;
        } else {
            i = (from - 1);
            pivot = num[to].doubleValue();
            for (int j = from; j < to; j++) {
                if (num[j].doubleValue() <= pivot) {
                    i++;
                    swap(num, i, j);
                }
            }
            swap(num, i + 1, to);
            return i + 1;
        }
    }

    private int getIndex(Number val) {
        for (int i = 0; i < size; i++) {
            if(numbers[i].equals(val)){
                return i;
            }
        }
        return -1;
    }

    private void swap(Number[] num, int i, int j) {
        Number temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

}
