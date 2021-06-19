package ua.com.alevel.app.service;

public class MathSet {

    private Number[] numbers;
    private int capacity;
    private int amount;

    public MathSet() {
        capacity = 10;
        numbers = new Number[capacity];
        amount = 0;
    }

    public MathSet(int capacity) {
        this.capacity = capacity;
        numbers = new Number[capacity];
        amount = 0;
    }

    public MathSet(Number[] numbers) {
        capacity = numbers.length;
        amount = 0;
        this.numbers = new Number[capacity];
        add(numbers);
    }

    public MathSet(Number[] ... numbers) {
        capacity = numbers.length;
        amount = 0;
        this.numbers = new Number[capacity];
        for (Number[] n : numbers) {
            add(n);
        }
    }

    public MathSet(MathSet numbers) {
        Number[] arr = numbers.toArray();
        capacity = arr.length;
        amount = 0;
        this.numbers = new Number[capacity];
        add(arr);
    }

    public MathSet(MathSet... numbers) {
        capacity = numbers[0].toArray().length;
        amount = 0;
        this.numbers = new Number[capacity];
        for (MathSet n : numbers) {
            add(n.toArray());
        }
    }

    public void add(Number n) {
        if (amount == numbers.length) {
            increaseCapacity();
        }
        if (isContain(n)) {
            return;
        }
        numbers[amount] = n;
        amount++;
    }

    public void add(Number... n) {
        for (Number number : n) {
            add(number);
        }
    }

    public void join(MathSet ms) {
        add(ms.toArray());
    }

    public void join(MathSet... ms) {
        for (MathSet mathSet : ms) {
            join(mathSet);
        }
    }

    public void sortDesc() {
        sortDesc(0, amount);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        lastIndex = Math.min(amount, lastIndex);
        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = firstIndex; j < lastIndex - 1; j++) {
                if (numbers[j].doubleValue() < numbers[j + 1].doubleValue()) {
                    Number temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }

    public void sortDesc(Number value) {
        if (isContain(value)) {
            sortDesc(0, getIndexOf(value));
        }
    }

    public void sortAsc() {
        sortAsc(0, amount);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        lastIndex = Math.min(amount, lastIndex);
        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = firstIndex; j < lastIndex-1; j++) {
                if (numbers[j].doubleValue() > numbers[j + 1].doubleValue()) {
                    Number temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }

    public void sortAsc(Number value) {
        if (isContain(value)) {
            sortAsc(0, getIndexOf(value));
        }
    }

    public Number get(int index) {
        return numbers[index];
    }

    public Number getMax() {
        Number max = numbers[0];
        for (int i = 0; i < amount; i++) {
            if (numbers[i].doubleValue() > max.doubleValue()) {
                max = numbers[i];
            }
        }
        return max;
    }

    public Number getMin() {
        Number min = numbers[0];
        for (int i = 0; i < amount; i++) {
            if (numbers[i].doubleValue() < min.doubleValue()) {
                min = numbers[i];
            }
        }
        return min;
    }

    public Number getAverage() {
        double sum = 0;
        for (int i = 0; i < amount; i++) {
            sum += numbers[i].doubleValue();
        }
        return sum / amount;
    }

    public Number getMedian() {
        return amount % 2 != 0 ? numbers[amount /2] : ((numbers[amount /2].doubleValue() + numbers[amount /2 - 1].doubleValue())/2);
    }

    public Number[] toArray(){
        Number[] arr = new Number[amount];
        if (amount >= 0) System.arraycopy(numbers, 0, arr, 0, amount);
        return arr;
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && lastIndex > 0 && lastIndex > firstIndex) {
            Number[] arr = new Number[lastIndex - firstIndex];
            int arrIterator = 0;
            for (int i = firstIndex; i < lastIndex; i++) {
                arr[arrIterator++] = numbers[i];
            }
            return arr;
        }
        throw new IndexOutOfBoundsException();
    }

    public MathSet squash(int firstIndex, int lastIndex) {
        MathSet temp = new MathSet();
        for (int i = firstIndex; i < lastIndex; i++) {
            temp.add(numbers[i]);
        }
        return temp;
    }

    public void clear() {
        amount = 0;
        numbers = new Number[capacity];
    }

    public void clear(Number[] numbers) {
        for (Number number : numbers) {
            if (isContain(number)) {
                this.numbers[getIndexOf(number)] = this.numbers[amount - 1];
                this.numbers[amount - 1] = null;
                amount--;
            }
        }
    }

    private void increaseCapacity() {
        capacity = (capacity * 3) / 2 + 1;
        Number[] tempArr = new Number[capacity];
        System.arraycopy(numbers, 0, tempArr, 0, numbers.length);
        numbers = tempArr;
    }

    private boolean isContain(Number number) {
        for (int i = 0; i < amount; i++) {
            if (number.doubleValue() == numbers[i].doubleValue()) {
                return true;
            }
        }
        return false;
    }

    private int getIndexOf(Number value) {
        for (int i = 0; i < amount; i++) {
            if (value.doubleValue() == numbers[i].doubleValue()) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }
}
