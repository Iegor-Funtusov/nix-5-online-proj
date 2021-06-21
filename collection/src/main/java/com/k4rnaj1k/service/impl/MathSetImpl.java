package com.k4rnaj1k.service.impl;

import com.k4rnaj1k.service.MathSet;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public class MathSetImpl
        <T extends Number & Comparable<T>> implements MathSet<T> {
    Number[] arr;
    private final int DEFAULT_SIZE = 16;
    private int count;

    public MathSetImpl() {
        arr = new Number[DEFAULT_SIZE];
    }

    public MathSetImpl(int capacity) {
        arr = new Number[capacity];
    }

    public MathSetImpl(T[] numbers) {
        arr = new Number[DEFAULT_SIZE];
        for (Number n :
                numbers) {
            add(n);
        }
    }

    public MathSetImpl(T[]... numbers) {
        arr = new Number[DEFAULT_SIZE];
        for (Number[] numberarr :
                numbers) {
            for (Number number :
                    numberarr) {
                add(number);
            }
        }
    }

    public MathSetImpl(MathSet<T> numbers) {
        arr = new Number[DEFAULT_SIZE];
        for (Number number :
                numbers.toArray()) {
            add(number);
        }
    }

    public MathSetImpl(MathSet<T>... numbers) {
        arr = new Number[DEFAULT_SIZE];
        for (MathSet set :
                numbers) {
            for (Number n :
                    set.toArray()) {
                add(n);
            }
        }
    }

    @Override
    public void add(Number n) {
        if (isExisting(n)) {
            return;
        }
        if (count > arr.length) {
            extendTheArray();
        }
        arr[count] = n;
        count++;
    }

    @Override
    public void add(Number... n) {
        for (Number number :
                n) {
            add(number);
        }
    }

    @Override
    public void join(MathSet ms) {
        for (Number number :
                ms.toArray()) {
            add(number);
        }
    }

    @Override
    public void join(MathSet... ms) {
        for (MathSet set :
                ms) {
            for (Number n :
                    set.toArray()) {
                add(n);
            }
        }
    }

    @Override
    public void sortDesc() {
        sort(0, count, false);
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        sort(firstIndex, lastIndex, false);
    }

    @Override
    public void sortDesc(Number value) {
        int firstIndex = getFirstIndex(value);
        if (firstIndex == -1) {
            return;
        }
        sort(firstIndex, count, false);
    }

    @Override
    public void sortAsc() {
        sort(0, count, true);
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        sort(firstIndex, lastIndex, true);
    }

    @Override
    public void sortAsc(Number value) {
        int firstIndex = getFirstIndex(value);
        if (firstIndex == -1) {
            return;
        }
        sort(firstIndex, arr.length, true);
    }

    @Override
    public Number get(int index) {
        Objects.checkIndex(index, arr.length);
        return arr[index];
    }

    @Override
    public Number getMax() {
        T max = (T) arr[0];
        for (int i = 0; i < arr.length && arr[i] != null; i++) {
            if (max.compareTo((T) arr[i]) == 1) {
                max = (T) arr[i];
            }
        }
        return max;
    }

    @Override
    public Number getMin() {
        T min = (T) arr[0];
        for (int i = 0; i < arr.length && arr[i] != null; i++) {
            if (min.compareTo((T) arr[i]) == -1) {
                min = (T) arr[i];
            }
        }
        return min;
    }

    @Override
    public Number getAverage() {
        BigDecimal summ = new BigDecimal("0");
        for (int i = 0; i < arr.length; i++) {
            summ.add(new BigDecimal(arr[i].toString()));
        }
        return summ.divide(new BigDecimal(String.valueOf(arr.length)), MathContext.DECIMAL32);

    }

    @Override
    public Number getMedian() {
        Number[] temp = new Number[count];
        for (int i = 0; i < count; i++) {
            temp[i] = arr[count];
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (((T) temp[i]).compareTo((T) temp[j]) == 1) {
                    Number tempnum = temp[i];
                    temp[i] = temp[j];
                    temp[j] = tempnum;
                } else if (((T) temp[i]).compareTo((T) temp[j]) == -1) {
                    Number tempnum = temp[j];
                    temp[j] = temp[i];
                    temp[i] = tempnum;
                }
            }
        }
        return temp[count / 2];
    }

    @Override
    public Number[] toArray() {
        Number[] res = new Number[count];
        for (int i = 0; i < count; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    @Override
    public Number[] toArray(int firstIndex, int lastIndex) {
        Objects.checkIndex(firstIndex, arr.length);
        Objects.checkIndex(lastIndex, arr.length);
        Objects.checkIndex(firstIndex, lastIndex);
        Number[] res = new Number[lastIndex - firstIndex];
        for (int i = firstIndex; i < lastIndex; i++) {
            res[i - firstIndex] = arr[i];
        }
        return res;
    }

    @Override
    public MathSet squash(int firstIndex, int lastIndex) {
        Objects.checkIndex(firstIndex, arr.length);
        Objects.checkIndex(lastIndex, arr.length);
        Objects.checkIndex(firstIndex, lastIndex);
        MathSetImpl<T> res = new MathSetImpl<>();
        for (Number n :
                toArray(firstIndex, lastIndex)) {
            res.add(n);
        }
        return res;
    }

    @Override
    public void clear() {
        arr = new Number[DEFAULT_SIZE];
        count = 0;
    }

    private void delete(Number n) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(n)) {
                Number[] res = new Number[arr.length - 1];
                int c = 0;
                for (int j = 0; j < res.length; j++) {
                    if (j == i) {
                        c++;
                        count--;
                    }
                    res[j] = arr[j + c];
                }
                arr = res;
                break;
            }
        }
    }

    @Override
    public void clear(Number[] numbers) {
        for (Number n :
                numbers) {
            delete(n);
        }
    }

    private int getFirstIndex(Number value) {
        if (!isExisting(value)) {
            return -1;
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(value)) {
                    return i;
                }
            }
        }
        return 0;
    }

    private void extendTheArray() {
        Number[] temp = new Number[arr.length + DEFAULT_SIZE];
        for (int j = 0; j < arr.length; j++) {
            temp[j] = arr[j];
        }
    }

    private boolean isExisting(Number n) {
        for (Number checking :
                arr) {
            if (checking != null) {
                if (checking.equals(n)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void sort(int firstIndex, int lastIndex, boolean asc) {
        Objects.checkIndex(firstIndex, count);
        Objects.checkIndex(lastIndex, count + 1);
        Objects.checkIndex(firstIndex, lastIndex);
        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = firstIndex; j < lastIndex; j++) {
                if (((T) arr[i]).compareTo((T) arr[j]) == (asc ? -1 : 1)) {
                    Number temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
