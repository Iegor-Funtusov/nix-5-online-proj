package mathset;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MathSet<T extends Number & Comparable<T>> {
    private int capacity;
    private T[] numbers;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public MathSet(Class<? extends T> cls) {
        this.size = 0;
        this.capacity = 16;
        this.numbers = (T[]) Array.newInstance(cls, capacity);
    }

    @SuppressWarnings("unchecked")
    public MathSet(Class<? extends T> cls, int capacity) {
        this.size = 0;
        if (capacity < 0) {
            throw new IllegalArgumentException("Ёмкость не может быть отрицательной!");
        }
        this.capacity = capacity;
        this.numbers = (T[]) Array.newInstance(cls, capacity);
    }

    @SuppressWarnings("unchecked")
    public MathSet(Class<? extends T> cls, Number[] numbers) {
        this.size = 0;
        this.capacity = numbers.length;
        this.numbers = (T[]) Array.newInstance(cls, capacity);
        for (Number number : numbers) {
            if (number != null) {
                add((T) number);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public MathSet(Class<? extends T> cls, Number[]... numbers) {
        this.size = 0;
        this.capacity = 16;
        this.numbers = (T[]) Array.newInstance(cls, capacity);
        for (Number[] number : numbers) {
            for (Number n : number) {
                add((T) n);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public MathSet(Class<? extends T> cls, MathSet<T> numbers) {
        this.size = 0;
        this.capacity = numbers.size;
        this.numbers = (T[]) Array.newInstance(cls, capacity);
        join(numbers);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public MathSet(Class<? extends T> cls, MathSet<T>... numbers) {
        this.size = 0;
        this.capacity = 16;
        this.numbers = (T[]) Array.newInstance(cls, capacity);
        for (MathSet<T> ms : numbers) {
            join(ms);
        }
    }

    public void add(T n) {
        if (size == capacity) {
            increaseCapacity();
        }
        if (contains(n)) {
            return;
        }
        numbers[size] = n;
        size++;
    }

    @SafeVarargs
    public final void add(T... n) {
        if (size + n.length > capacity) {
            capacity = size + n.length;
            this.numbers = Arrays.copyOf(this.numbers, capacity);
        }
        for (T i : n) {
            if (contains(i)) {
                continue;
            }
            add(i);
        }
    }

    @SuppressWarnings("unchecked")
    public void join(MathSet<T> ms) {
        Number[] numbers = ms.toArray();
        //   T[] numbers = (T[]) ms.toArray();
        for (int i = 0; i < numbers.length; i++) {
            if ((T) numbers[i] != null)
                add((T) numbers[i]);
        }
    }

    @SafeVarargs
    public final void join(MathSet<T>... ms) {
        for (int i = 0; i < ms.length; i++) {
            join(ms[i]);
        }
    }

    public void sortDesc() {
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if ((numbers[i] != null) && (numbers[j] != null)) {
                    if (numbers[i].compareTo(numbers[j]) < 0) {
                        T temp = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j] = temp;
                    }
                }
            }
        }
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        for (int i = firstIndex; i <= lastIndex; i++) {
            for (int j = i + 1; j <= lastIndex; j++) {
                if ((numbers[i] != null) && (numbers[j] != null)) {
                    if (numbers[i].compareTo(numbers[j]) < 0) {
                        T temp = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j] = temp;
                    }
                }
            }
        }
    }

    public void sortDesc(Number value) {
        int v = getIndex(value);
        if (v != 1) {
            for (int i = v; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    if ((numbers[i] != null) && (numbers[j] != null)) {
                        if (numbers[i].compareTo(numbers[j]) < 0) {
                            T temp = numbers[i];
                            numbers[i] = numbers[j];
                            numbers[j] = temp;
                        }
                    }
                }
            }
        }
    }

    public void sortAsc() {
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if ((numbers[i] != null) && (numbers[j] != null)) {
                    if (numbers[i].compareTo(numbers[j]) > 0) {
                        T temp = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j] = temp;
                    }
                }
            }
        }
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        for (int i = firstIndex; i <= lastIndex; i++) {
            for (int j = i + 1; j <= lastIndex; j++) {
                if ((numbers[i] != null) && (numbers[j] != null)) {
                    if (numbers[i].compareTo(numbers[j]) > 0) {
                        T temp = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j] = temp;
                    }
                }
            }
        }
    }

    public void sortAsc(Number value) {
        int v = getIndex(value);
        if (v != 1) {
            for (int i = v; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    if ((numbers[i] != null) && (numbers[j] != null)) {
                        if (numbers[i].compareTo(numbers[j]) > 0) {
                            T temp = numbers[i];
                            numbers[i] = numbers[j];
                            numbers[j] = temp;
                        }
                    }
                }
            }
        }
    }

    public Number get(int index) {
        if (index < 0 || index > size)
            throw new ArrayIndexOutOfBoundsException("Индекс за пределами массива!");
        return numbers[index];
    }

    public Number getMin() {
        T n = this.numbers[0];
        for (int i = 1; i < size; i++) {
            if (numbers[i].compareTo(n) < 0) n = numbers[i];
        }
        return n;
    }

    public Number getMax() {
        T n = numbers[0];
        for (int i = 1; i < size; i++) {
            if (numbers[i].compareTo(n) > 0) n = numbers[i];
        }
        return n;
    }

    public Number getAverage() {
        double sum = 0.0;
        for (int i = 0; i < size; i++) {
            if ((numbers[i]) != null)
                sum += numbers[i].doubleValue();
        }
        return sum / size;
    }

    public Number getMedian() {
        for (int i = 0; i <= size - 1; i++) {
            for (int j = i + 1; j <= size - 1; j++) {
                if ((numbers[i] != null) && (numbers[j] != null)) {
                    if (numbers[i].doubleValue() > numbers[j].doubleValue()) {
                        T temp = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j] = temp;
                    }
                } else System.out.println("Есть пустые значения!");
            }
        }
        double median;
        if (size % 2 == 0) {
            median = (numbers[(size) / 2].doubleValue() +
                numbers[(size) / 2 - 1].doubleValue()) / 2;
        } else {
            median = numbers[(size) / 2].doubleValue();
        }
        return median;
    }

    public Number[] toArray() {
        numbers = Arrays.copyOf(this.numbers, this.numbers.length);
        return numbers;
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] numbers = new Number[lastIndex - firstIndex];
        for (int i = firstIndex, j = 0; i < lastIndex; i++, j++) {
            numbers[j] = this.numbers[i];
        }
        return numbers;
    }

    @SuppressWarnings("unchecked")
    public MathSet<?> squash(Class<? extends Number> cls, int firstIndex, int lastIndex) {
        MathSet<T> mathSet = new MathSet(cls);
        for (int i = firstIndex; i < lastIndex; i++) {
            mathSet.add(this.numbers[i]);
        }
        Number[] temp = new Number[mathSet.size];
        for (int i = 0, j = 0; i < mathSet.size; i++) {
            if (mathSet.get(i) != null) {
                temp[j] = mathSet.get(i);
                j++;
            }
        }
        Number[] m = Arrays.copyOf(temp, temp.length);
        mathSet = new MathSet(cls, m);
        return mathSet;
    }

    //   @SuppressWarnings("unchecked")
    public void clear() {
        //  public void clear (Class<? extends T> cls) {
        size = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null)
                numbers[i] = null;
        }
      /*     size = 0;
      capacity = 16;
            numbers = (T[]) Array.newInstance(cls, capacity);*/
    }

    public boolean contains(Number n) {
        for (int i = 0; i < size; i++) {
            if (n.equals(numbers[i])) return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    @SuppressWarnings("unchecked")
    public void clear(Number[] numbers) {
        if (!isEmpty()) {
            for (int i = 0; i < numbers.length; i++)
                for (int j = 0; j < this.numbers.length; j++) {
                    if ((numbers[i] != null) && (this.numbers[j] != null)) {
                        if (numbers[i].equals(this.numbers[j])) {
                            this.numbers[j] = null;
                            size--;
                        }
                    }
                }

            Number[] temp = new Number[this.numbers.length];
            for (int i = 0, j = 0; i < this.numbers.length; i++) {
                if (this.numbers[i] != null) {
                    temp[j] = this.numbers[i];
                    j++;
                }
            }
            this.numbers = (T[]) Arrays.copyOf(temp, temp.length);
        }
    }

    public int getSize() {
        return size;
    }

    private int getIndex(Number value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (value.equals(numbers[i])) {
                index = i;
                return index;
            }
        }
        return index;
    }

    private void increaseCapacity() {
        this.capacity = getSize() * 2;
        this.numbers = Arrays.copyOf(this.numbers, capacity);
    }
}

