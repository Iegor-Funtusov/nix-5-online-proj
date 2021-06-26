package ua.com.alevel.app.service;

public class MathSet<T extends Number> {

    private int capacity = 10;
    private T[] number;
    private int lastIndex;

    public MathSet() {
        number = (T[]) new Number[capacity];
    }

    public MathSet(int capacity) {
        this.capacity = capacity;
        number = (T[]) new Number[capacity];
    }

    public MathSet(T[] number) {
        this();
        add(number);
    }

    public MathSet(T[]... number) {
        this();
        for (T[] numbers : number) {
            add(numbers);
        }
    }

    public MathSet(MathSet<T> mathSet) {
        this();
        join(mathSet);
    }

    public MathSet(MathSet<T>... mathSet) {
        this();
        for (MathSet<T> m : mathSet) {
            join(m);
        }
    }

    public void add(T n) {
        if (lastIndex > 1) {
            for (int i = 0; i < lastIndex; i++) {
                T value = number[i];
                if (value.equals(n))
                    throw new IllegalArgumentException("argument is already present");
            }
        }
        number[lastIndex++] = n;
        if (lastIndex == capacity)
            increaseCapacity();
    }

    @SafeVarargs
    public final void add(T... n) {
        for (T number1 : n) {
            add(number1);
        }
    }

    public void join(MathSet<T> ms) {
        T[] msArray = ms.toArray();
        add(msArray);
    }

    @SafeVarargs
    public final void join(MathSet<T>... ms) {
        for (MathSet<T> m : ms) {
            join(m);
        }
    }

    public void sortDesc() {
        sortDesc(0, lastIndex);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        T[] tempArray = descendingSort(firstIndex, lastIndex);
        System.arraycopy(tempArray, 0, number, firstIndex, lastIndex - firstIndex);
    }

    public void sortDesc(T value){
        sortDesc(0, findIndex(value));
    }

    public void sortAsc() {
        sortAsc(0, lastIndex);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        T[] tempArray = ascendingSort(firstIndex, lastIndex);
        System.arraycopy(tempArray, 0, number, firstIndex, lastIndex - firstIndex);
    }

    public void sortAsc(T value)
    {
        sortAsc(0, findIndex(value));
    }

    public T get(int index) {
        if (number[index] == null)
            throw new IllegalArgumentException("Incorrect input");
        return number[index];
    }

    public T getMax() {
        T[] tempArray = ascendingSort(0, lastIndex);
        return tempArray[tempArray.length - 1];
    }

    public T getMin() {
        T[] tempArray = ascendingSort(0, lastIndex);
        return tempArray[0];
    }

    public T getAverage() {
        double res = 0;
        for (int i = 0; i < lastIndex; i++) {
            res += number[i].doubleValue();
        }
        res /= lastIndex;
        return (T) Double.valueOf(res);
    }

    public T getMedian() {
        T[] tempArray = ascendingSort(0, lastIndex);
        int middleIndex = tempArray.length / 2;
        if (tempArray.length % 2 == 0) {
            double sumValue = tempArray[middleIndex].doubleValue() + tempArray[middleIndex - 1].doubleValue();
            return (T) Double.valueOf(sumValue / 2d);
        }
        return tempArray[middleIndex];
    }

    public T[] toArray() {
        return toArray(0, lastIndex);
    }

    public T[] toArray(int firstIndex, int lastIndex) {
        T[] resArray = (T[]) new Number[lastIndex - firstIndex];
        System.arraycopy(number, firstIndex, resArray, 0, lastIndex - firstIndex);
        return resArray;
    }

    public MathSet<T> squash(int firstIndex, int lastIndex) {
        T[] tempArray = (T[]) new Number[lastIndex - firstIndex];
        System.arraycopy(number, firstIndex, tempArray, 0, lastIndex - firstIndex);
        clear(tempArray);
        return new MathSet<>(tempArray);
    }

    public void clear() {
        capacity = 10;
        number = (T[]) new Number[capacity];
        lastIndex = 0;
    }

    public void clear(T[] nums) {
        T[] newArray = (T[]) new Number[capacity];
        int index = 0;
        for (int i = 0; i < lastIndex; i++) {
            for (T num : nums) {
                if (number[i].equals(num)) {
                    number[i] = null;
                    break;
                }
            }
        }
        for (int i = 0; i < lastIndex; i++) {
            if (number[i] != null) {
                newArray[index] = number[i];
                index++;
            }
        }
        lastIndex = index;
        number = newArray;
    }

    private void increaseCapacity() {
        capacity = (capacity * 3) / 2 + 1;
        T[] newArray = (T[]) new Number[capacity];
        System.arraycopy(number, 0, newArray, 0, lastIndex);
        number = newArray;
    }

    private T[] ascendingSort(int firstIndex, int lastIndex) {
        T[] arr = (T[]) new Number[lastIndex - firstIndex];
        System.arraycopy(number, firstIndex, arr, 0, lastIndex - firstIndex);
        T tmp;
        for (int i = 0; i <= lastIndex - firstIndex; i++) {
            for (int j = 1; j < (lastIndex - firstIndex - i); j++) {
                if (arr[j - 1].longValue() > arr[j].longValue()) {
                    tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }

    private T[] descendingSort(int firstIndex, int lastIndex) {
        T[] arr = (T[]) new Number[lastIndex - firstIndex];
        System.arraycopy(number, firstIndex, arr, 0, lastIndex - firstIndex);
        T tmp;
        for (int i = 0; i <= lastIndex - firstIndex; i++) {
            for (int j = 1; j < (lastIndex - firstIndex - i); j++) {
                if (arr[j - 1].longValue() < arr[j].longValue()) {
                    tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }

    private int findIndex(T value) {
        for (int i = 0; i < lastIndex; i++) {
            if(value.equals(number[i]))
                return i;
        }
        throw new IllegalArgumentException("Incorrect value");
    }

    public int getCapacity() {
        return capacity;
    }

    public int getLastIndex() {
        return lastIndex;
    }
}
