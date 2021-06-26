package service;

import mathset.MathSet;

import java.io.BufferedReader;
import java.io.IOException;

public class MathSetService {

    public static MathSet<Integer> createSetCapacity(BufferedReader reader) throws IOException {
        int capacity = Integer.parseInt(reader.readLine());
        MathSet<Integer> set = new MathSet<>(Integer.class, capacity);
        for (int i = 0; i < capacity; i++) {
            int sign = Math.random() < 0.5 ? -1 : 1;
            set.add((int) (Math.random() * 100) * sign);
        }
        return set;
    }

    public static MathSet<Integer> createSet() {
        int capacity = 16;
        MathSet<Integer> set = new MathSet<>(Integer.class, capacity);
        for (int i = 0; i < capacity; i++) {
            int sign = Math.random() < 0.5 ? -1 : 1;
            set.add((int) (Math.random() * 100) * sign);
        }
        return set;
    }

    public static void printSet(MathSet<?> set) {
        System.out.print("[");
        for (int i = 0; i < set.getSize(); i++) {
          //  System.out.print(set.get(i) + ", ");
            System.out.print(((i > 0) ? ", " : "") + set.get(i));
        }
        System.out.println("]");
        System.out.println();
    }

    public static Integer[] inputArray(BufferedReader reader) throws IOException {
        String line;
        Integer[] array = new Integer[0];
        line = reader.readLine();
        if (line.equals("")) {
            return array;
        } else {
            String[] strings = line
                .trim()
                .replaceAll(" +", "")
                .split(",");
            array = new Integer[strings.length];
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt(strings[i]);
            }
        }
        return array;
    }

    public static void sortDesc(MathSet<?> set) {
        setAcceptable(set);
        set.sortDesc();
    }

    public static void sortDesc(MathSet<?> set, int firstIndex, int lastIndex) {
        setAcceptable(set);
        indexAcceptable(set, firstIndex, lastIndex);
        set.sortDesc(firstIndex, lastIndex);
    }

    public static void sortDesc(MathSet<?> set, Number value) {
        setAcceptable(set);
        valueAcceptable(set, value);
        set.sortDesc(value);
    }

    public static void sortAsc(MathSet<?> set) {
        setAcceptable(set);
        set.sortAsc();
    }

    public static void sortAsc(MathSet<?> set, int firstIndex, int lastIndex) {
        setAcceptable(set);
        indexAcceptable(set, firstIndex, lastIndex);
        set.sortAsc(firstIndex, lastIndex);
    }

    public static void sortAsc(MathSet<?> set, Number value) {
        setAcceptable(set);
        valueAcceptable(set, value);
        set.sortAsc(value);
    }

    public static Number getNumber(MathSet<?> set, int index) {
        setAcceptable(set);
        indexAcceptable(set, index);
        return set.get(index);
    }

    public static Number getMax(MathSet<?> set) {
        setAcceptable(set);
        return set.getMax();
    }

    public static Number getMin(MathSet<?> set) {
        setAcceptable(set);
        return set.getMin();
    }

    public static Number getAverage(MathSet<?> set) {
        setAcceptable(set);
        return set.getAverage();
    }

    public static Number getMedian(MathSet<?> set) {
        setAcceptable(set);
        return set.getMedian();
    }

    public static Number[] setToArray(MathSet<?> set) {
        setAcceptable(set);
        return set.toArray();
    }

    public static Number[] setToArray(MathSet<?> set, int firstIndex, int lastIndex) {
        setAcceptable(set);
        indexAcceptable(set, firstIndex, lastIndex);
        return set.toArray(firstIndex, lastIndex);
    }

    public static void clear(MathSet<?> set) {
        setAcceptable(set);
        set.clear();
    }

    public static void clear(MathSet<?> set, Number[] numbers) {
        setAcceptable(set);
        arrayAcceptable(numbers);
        set.clear(numbers);
    }

    public static MathSet<?> squash(Class<? extends Number> cls, MathSet<?> set, int firstIndex, int lastIndex) {
        setAcceptable(set);
        indexAcceptable(set, firstIndex, lastIndex);
        return set.squash(cls, firstIndex, lastIndex);
    }

    private static void setAcceptable(MathSet<?> set) throws NullPointerException {
        if (set == null) {
            throw new NullPointerException("Множество не существует!");
        }
        if (set.getSize() == 0) {
            throw new NullPointerException("Множество пустое!");
        }
    }

    private static void indexAcceptable(MathSet<?> set, int firstIndex, int lastIndex)
        throws IllegalArgumentException, ArrayIndexOutOfBoundsException {

        if (set.getSize() < firstIndex || set.getSize() <= lastIndex || firstIndex < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс за пределами множества!");
        }
        if (firstIndex >= lastIndex) {
            throw new IllegalArgumentException("Второй индекc должен быть больше первого!");
        }
    }

    private static void indexAcceptable(MathSet<?> set, int index) throws ArrayIndexOutOfBoundsException {

        if (set.getSize() < index || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс " + index + " за пределами множества!");
        }
    }

    private static void valueAcceptable(MathSet<?> set, Number value) throws NullPointerException {

        if (!set.contains(value) || value == null) throw new NullPointerException("Такого числа во множестве нет!");
    }

    private static void arrayAcceptable(Number[] numbers) throws NullPointerException {
        if (numbers == null) throw new NullPointerException("Массив не существует!");
        for (Number number : numbers) {
            numberAcceptable(number);
        }
    }

    private static void numberAcceptable(Number number) throws NullPointerException {
        if (number == null) throw new NullPointerException("Число не существует!");
    }
}
