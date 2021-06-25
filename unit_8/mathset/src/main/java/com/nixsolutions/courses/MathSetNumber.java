package com.nixsolutions.courses;


import com.nixsolutions.courses.utils.ArrayUtils;

import java.util.NoSuchElementException;

public class MathSetNumber<E extends Number & Comparable<E>> implements MathSet<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Number[] mathSet;
    private int size;

    public MathSetNumber() {
        mathSet = new Number[DEFAULT_CAPACITY];
    }

    public MathSetNumber(int capacity) {
        if (capacity >= 0) {
            mathSet = new Number[capacity];
        } else {
            throw new IllegalArgumentException("Capacity value is illegal");
        }
    }

    public MathSetNumber(E[] numbers) {
        mathSet = new Number[numbers.length];
        add(numbers);
    }

    @SafeVarargs
    public MathSetNumber(E[]... numbers) {
        mathSet = new Number[numbers.length * DEFAULT_CAPACITY];
        for (E[] array : numbers) {
            add(array);
        }
    }

    @Override
    public void add(E n) {
        if (mathSet.length == size) resize();
        if (isUnique(n)) {
            mathSet[size++] = n;
        }
    }

    @SafeVarargs
    @Override
    public final void add(E... n) {
        if (mathSet.length - size < n.length) resize(mathSet.length + n.length);
        for (E t : n) {
            add(t);
        }
    }

    @Override
    public void join(MathSet<E> ms) {
        if (mathSet.length - size < ms.getSize()) resize(mathSet.length + ms.getSize());
        for (Number n : ms.toArray()) {
            add((E) n);
        }
    }

    @SafeVarargs
    @Override
    public final void join(MathSet<E>... ms) {
        for (MathSet<E> m : ms) {
            join(m);
        }
    }

    @Override
    public void sortDesc() {
        sortDesc(0, size);
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        Number temp;
        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = i + 1; j < lastIndex; j++) {
                if (((E) mathSet[j]).compareTo((E) mathSet[i]) > 0) {
                    temp = mathSet[i];
                    mathSet[i] = mathSet[j];
                    mathSet[j] = temp;
                }
            }
        }
    }

    @Override
    public void sortDesc(E value) {
        int index = indexOf(value);
        sortDesc(index, size);
    }

    @Override
    public void sortAsc() {
        sortAsc(0, size);
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        Number temp;
        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = i + 1; j < lastIndex; j++) {
                if (((E) mathSet[j]).compareTo((E) mathSet[i]) < 0) {
                    temp = mathSet[i];
                    mathSet[i] = mathSet[j];
                    mathSet[j] = temp;
                }
            }
        }
    }

    @Override
    public void sortAsc(E value) {
        int index = indexOf(value);
        sortAsc(index, size);
    }

    @Override
    public E get(int index) {
        if (validIndex(index)) return (E) mathSet[index];
        return null;
    }

    @Override
    public E getMax() {
        E max = (E) mathSet[0];
        for (int i = 1; i < size - 1; i++) {
            if (max.compareTo((E) mathSet[i + 1]) < 0) max = (E) mathSet[i + 1];
        }
        return max;
    }

    @Override
    public E getMin() {
        E min = (E) mathSet[0];
        for (int i = 1; i < size - 1; i++) {
            if (min.compareTo((E) mathSet[i + 1]) > 0) min = (E) mathSet[i + 1];
        }
        return min;
    }

    @Override
    public Number getAverage() {
        double sum = 0;
        for (Number n : mathSet) {
            sum += (int) n;
        }
        return sum / size;
    }

    @Override
    public Number getMedian() {
        MathSetNumber set = new MathSetNumber(mathSet);
        set.sortAsc();
        if (set.getSize() % 2 != 0) {
            return set.get(set.getSize() / 2);
        } else {
            return (set.get(set.getSize() / 2 - 1).doubleValue() + set.get(set.getSize() / 2).doubleValue()) / 2;
        }
    }

    @Override
    public Number[] toArray() {
        return ArrayUtils.copyOfRange(mathSet, 0, size - 1, Number[].class);
    }

    @Override
    public Number[] toArray(int firstIndex, int lastIndex) {
        if (validIndex(firstIndex) && validIndex(lastIndex) && lastIndex - firstIndex + 1 < size)
            return ArrayUtils.copyOfRange(mathSet, firstIndex, lastIndex, Number[].class);
        throw new IllegalArgumentException("Illegal pair of indexes");
    }

    @Override
    public MathSet<E> squash(int firstIndex, int lastIndex) {
        return new MathSetNumber<E>((E[]) toArray(firstIndex, lastIndex));
    }

    @Override
    public void clear() {
        mathSet = new Number[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void clear(E[] numbers) {
        for (E number : numbers) {
            try {
                int index = indexOf(number);
                mathSet[index] = null;
                size--;
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public int getSize() {
        return size;
    }

    private void resize(int newCapacity) {
        ArrayUtils.copyOf(mathSet, newCapacity, Number[].class);
    }

    private void resize() {
        resize(size + (size / 2));
    }

    private int indexOf(E element) {
        return ArrayUtils.indexOf(element, toArray());
    }

    private boolean isUnique(E element) {
        if (size == 0) return true;
        for (Number n : mathSet) {
            if (n != null) {
                if (n.equals(element)) return false;
            }
        }
        return true;
    }

    private boolean validIndex(int index) {
        if (index >= 0 && index <= size - 1) return true;
        throw new IndexOutOfBoundsException("Index is out of boundaries");
    }

}
