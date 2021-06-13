package collection.service;

import java.util.Arrays;

public class MathSet<Digit extends Number & Comparable<Digit>> {

    private static final int DEFAULT_SIZE = 16;

    private static final int INCREASE_FACTOR = 2;

    private Object[] mathSet;

    private int count = 0;

    public MathSet() {
        mathSet = new Object[DEFAULT_SIZE];
    }

    public MathSet(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Pay attention: capacity cannot be null!");
        }
        this.mathSet = new Object[capacity];
    }

    public MathSet(Digit[] numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Pay attention: capacity cannot be null!");
        }
        mathSet = new Object[numbers.length];
        add(numbers);
    }

    public MathSet(Digit[]... numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Pay attention: capacity cannot be null!");
        }
        mathSet = new Object[numbers.length];
        for (Digit[] digits : numbers) {
            add(digits);
        }
    }

    public MathSet(MathSet<Digit> numbers) {
        this();
        join(numbers);
    }

    public MathSet(MathSet<Digit>... numbers) {
        this();
        join(numbers);
    }

    public void add(Digit digit) {
        if (isExist(digit)) {
            return;
        }
        if (count == mathSet.length) {
            increaseSetSize(count);
        }
        mathSet[count] = digit;
        count++;
    }

    public void add(Digit... digit) {
        if (digit.length > mathSet.length - count) {
            increaseSetSize(digit.length);
        }
        for (Digit d : digit) {
            add(d);
        }
    }

    public void join(MathSet<Digit> ms) {
        if (ms.count > mathSet.length - count) {
            increaseSetSize(ms.count);
        }
        for (Object d : ms.toArray()) {
            add((Digit) d);
        }
    }

    public void join(MathSet<Digit>... ms) {
        for (MathSet<Digit> set : ms) {
            join(set);
        }
    }

    public void sortDesc() {
        sortDesc(0, count - 1);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        if (notExistIndex(firstIndex, lastIndex)) {
            return;
        }
        sort(firstIndex, lastIndex, false);
    }

    public void sortDesc(Digit value) {
        int index = findIndex(value);
        if (!notExistIndex(index)) {
            sortDesc(0, index);
        }
    }

    public void sortAsc() {
        sortAsc(0, count - 1);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if (notExistIndex(firstIndex, lastIndex)) {
            return;
        }
        sort(firstIndex, lastIndex, true);
    }

    public void sortAsc(Digit value) {
        int index = findIndex(value);
        if (!notExistIndex(index)) {
            sortAsc(0, index);
        }
    }

    public Digit get(int index) {
        if (notExistIndex(index)) {
            return null;
        }
        return (Digit) mathSet[index];
    }


    public Digit getMax() {
        MathSet<Digit> mathSet1 = new MathSet<>(this);
        mathSet1.sortDesc();
        return mathSet1.get(0);
    }

    public Digit getMin() {
        MathSet<Digit> mathSet1 = new MathSet<>(this);
        mathSet1.sortAsc();
        return mathSet1.get(0);
    }

    public Double getAverage() {
        if (this.size() == 0) {
            return null;
        }
        double result = 0;
        for (int i = 0; i < count; i++) {
            result += this.get(i).doubleValue();
        }
        result /= count;
        return result;
    }

    public Double getMedian() {
        if (this.size() == 0) {
            return null;
        }
        MathSet<Digit> mathSet1 = new MathSet<>(this);
        mathSet1.sortAsc();

        double result;
        int size = mathSet1.size();
        if (size % 2 == 0) {
            result = mathSet1.get(size / 2 - 1).doubleValue() + mathSet1.get(size / 2).doubleValue();
            result /= 2.0;
        } else {
            result = mathSet1.get(size / 2).doubleValue();
        }
        return result;
    }

    public MathSet<Digit> squash(int firstIndex, int lastIndex) {
        if (firstIndex > lastIndex || notExistIndex(firstIndex, lastIndex)) {
            return null;
        }
        MathSet<Digit> result = new MathSet<>();
        Object[] array = toArray(firstIndex, lastIndex);
        for (Object number : array) {
            result.add(((Digit) number));
        }
        return result;
    }

    public void clear() {
        count = 0;
        mathSet = new Object[DEFAULT_SIZE];
    }

    public void clear(Digit[] numbers) {
        for (Digit number : numbers) {
            delete(number);
        }
    }

    public Object[] toArray() {
        return toArray(0, count - 1);
    }

    public Object[] toArray(int firstIndex, int lastIndex) {
        if (firstIndex > lastIndex || notExistIndex(firstIndex, lastIndex)) {
            return new Object[]{};
        }
        Object[] copy = new Object[lastIndex - firstIndex + 1];
        System.arraycopy(mathSet, firstIndex, copy, 0, lastIndex - firstIndex + 1);
        return copy;
    }

    public void delete(Digit number) {
        int index = findIndex(number);
        if (index < 0) {
            return;
        }
        mathSet[index] = null;
        if (index < count - 1) {
            System.arraycopy(mathSet, index + 1, mathSet, index, count - index - 1);
        }
        count--;
    }

    public int size() {
        return count;
    }

    private boolean isExist(Digit digit) {
        for (int i = 0; i < count; i++) {
            if (mathSet[i].equals(digit)) {
                return true;
            }
        }
        return false;
    }

    private void increaseSetSize(int count) {
        if (mathSet.length == count) {
            Object[] newArray = new Object[mathSet.length * INCREASE_FACTOR + count];
            System.arraycopy(mathSet, 0, newArray, 0, mathSet.length);
            mathSet = newArray;
        }
    }

    private void sort(int firstIndex, int lastIndex, boolean asc) {
        Object temp;
        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = firstIndex; j < lastIndex; j++) {
                if (asc) {
                    if (((Digit) mathSet[j]).compareTo(((Digit) mathSet[j + 1])) > 0) {
                        temp = mathSet[j];
                        mathSet[j] = mathSet[j + 1];
                        mathSet[j + 1] = temp;
                    }
                } else {
                    if (((Digit) mathSet[j]).compareTo(((Digit) mathSet[j + 1])) < 0) {
                        temp = mathSet[j];
                        mathSet[j] = mathSet[j + 1];
                        mathSet[j + 1] = temp;
                    }
                }
            }
        }
    }

    private int findIndex(Digit digit) {
        for (int i = 0; i < count; i++) {
            if (mathSet[i].equals(digit)) {
                return i;
            }
        }
        return -1;
    }

    private boolean notExistIndex(int... indexes) {
        for (int index : indexes) {
            if (index < 0 || index >= count) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "MathSet{" +
                "array=" + Arrays.toString(mathSet) +
                ", countObjects=" + count +
                '}';
    }
}
