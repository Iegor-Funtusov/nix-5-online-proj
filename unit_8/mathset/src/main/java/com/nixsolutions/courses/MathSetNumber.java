package com.nixsolutions.courses;


import com.nixsolutions.courses.utils.ArrayUtils;

public class MathSetNumber<E extends Number & Comparable<E>> implements MathSet<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] mathSet;
    private int size;

    public MathSetNumber() {
        mathSet = new Object[DEFAULT_CAPACITY];
    }

    public MathSetNumber(int capacity) {
        if (capacity >= 0) {
            mathSet = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Capacity value is illegal");
        }
    }

    public MathSetNumber(E[] numbers) {
        mathSet = new Object[numbers.length];
        add(numbers);
    }

    public MathSetNumber(E[]... numbers) {
        mathSet = new Object[numbers.length * DEFAULT_CAPACITY];
        for (E[] array : numbers) {
            add(array);
        }
    }

    @Override
    public void add(E n) {
        if (mathSet.length == size) mathSet = resize();
        if (isUnique(n)) {
            mathSet[size++] = n;
        }
    }

    @Override
    public void add(E... n) {
        if (mathSet.length - size < n.length) mathSet = resize(n.length);
        for (E t : n) {
            add(t);
        }
    }

    @Override
    public void join(MathSet<E> ms) {
        for (Object o : ms.toArray()) {
            add((E)o);
        }
    }

    @Override
    public void join(MathSet<E>... ms) {
        for (MathSet<E> m : ms) {
            join(m);
        }
    }

    @Override
    public void sortDesc() {
        Object temp;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (((E)mathSet[j]).compareTo((E)mathSet[i]) > 0) {
                    temp = mathSet[i];
                    mathSet[i] = mathSet[j];
                    mathSet[j] = temp;
                }
            }
        }
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        Object temp;
        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = i + 1; j < lastIndex; j++) {
                if (((E)mathSet[j]).compareTo((E)mathSet[i]) > 0) {
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
        sortDesc(index, size + 1);
    }

    @Override
    public void sortAsc() {
        Object temp;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (((E)mathSet[j]).compareTo((E)mathSet[i]) < 0) {
                    temp = mathSet[i];
                    mathSet[i] = mathSet[j];
                    mathSet[j] = temp;
                }
            }
        }
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        Object temp;
        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = i + 1; j < lastIndex; j++) {
                if (((E)mathSet[j]).compareTo((E)mathSet[i]) < 0) {
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
        sortAsc(index, size + 1);
    }

    @Override
    public E get(int index) {
        if (index > mathSet.length) throw new IndexOutOfBoundsException("Index is out of boundaries");
        return (E)mathSet[index];
    }

    @Override
    public E getMax() {
        E max = (E)mathSet[0];
        for (int i = 1; i < size - 1; i++) {
            if (max.compareTo((E) mathSet[i + 1]) < 0) max = (E)mathSet[i+1];
        }
        return max;
    }

    @Override
    public E getMin() {
        E min = (E)mathSet[0];
        for (int i = 1; i < size - 1; i++) {
            if (min.compareTo((E)mathSet[i+1]) > 0) min = (E)mathSet[i+1];
        }
        return min;
    }

    @Override
    public Number getAverage() {
        int sum = 0;
        for (Object o : mathSet) {
            sum += (int)o;
        }
        return sum / size;
    }

    @Override
    public Number getMedian() {
        sortAsc();
        if (size % 2 != 0) {
            return get(size/2);
        } else {
            return (get(size/2 - 1).doubleValue() + get(size/2).doubleValue())/2;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] copy = new Object[size];
        System.arraycopy(mathSet, 0, copy, 0, size);
        return copy;
    }

    @Override
    public Object[] toArray(int firstIndex, int lastIndex) {
        return null;
    }

    @Override
    public MathSet squash(int firstIndex, int lastIndex) {
        return null;
    }

    @Override
    public void clear() {
        mathSet = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void clear(E[] numbers) {

    }

    public int getSize() {
        return size;
    }

    private Object[] resize(int newCapacity) {
        return ArrayUtils.copyOf(mathSet, newCapacity);
    }

    private Object[] resize() {
        return resize(size + size / 3);
    }

    private int indexOf(E element) {
        return ArrayUtils.indexOf(element, toArray());
    }

    private boolean isUnique(E element) {
        for (Object o : mathSet) {
            if (o != null) {
                if (o.equals(element)) return false;
            }
        }
        return true;
    }

}
