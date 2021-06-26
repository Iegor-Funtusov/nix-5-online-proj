package com.nixsolutions.courses;

import com.nixsolutions.courses.utils.ArrayUtils;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class MathSetTest {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int RAND_MIN = 0;
    private static final int RAND_MAX = 1000;
    private static final Random rand = new Random();

    @Test
    public void emptyConstructor() {
        MathSet<Integer> mathSet = new MathSetNumber<>();
        Assertions.assertNotNull(mathSet, "Unable to create instance");
    }

    @Test
    public void capacityConstructor() {
        MathSet<Integer> mathSet = new MathSetNumber<>(DEFAULT_CAPACITY);

        Assertions.assertNotNull(mathSet, "Unable to create instance");
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MathSetNumber<>(-1));
    }

    @Test
    public void arrayConstructor() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        Assertions.assertEquals(array.length, mathSet.getSize());
    }

    @Test
    public void arrayVarargsConstructor() {
        Integer[][] array = IntStream.range(0, rand.nextInt(DEFAULT_CAPACITY))
                .mapToObj(rowArray -> randomArray())
                .toArray(Integer[][]::new);
        int distinct = (int) Arrays.stream(array).flatMap(Arrays::stream).distinct().count();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        Assertions.assertEquals(distinct, mathSet.getSize());
    }

    @Test
    public void setConstructor() {
        MathSet<Integer> set = new MathSetNumber<>(randomArray());
        MathSet<Integer> mathSet = new MathSetNumber<>(set);
        Assertions.assertEquals(set.getSize(), mathSet.getSize());
    }

    @Test
    public void setVarargsConstructor() {
        MathSet[] ms = new MathSetNumber[rand.nextInt(DEFAULT_CAPACITY)];
        for (int i = 0; i < ms.length; i++) {
            ms[i] = new MathSetNumber<>(randomArray());
        }
        int distinct = (int)Arrays.stream(ms).map(MathSet::toArray).flatMap(Arrays::stream).filter(Objects::nonNull).distinct().count();
        MathSet<Integer> mathSet = new MathSetNumber<>(ms);
        Assertions.assertEquals(distinct, mathSet.getSize());
    }

    @Test
    public void add() {
        MathSet<Integer> mathSet = new MathSetNumber<>(randomArray());
        Integer element = rand.nextInt(DEFAULT_CAPACITY);
        mathSet.add(element);
        Assertions.assertEquals(element, mathSet.get(mathSet.getSize() - 1));
    }

    @Test
    public void addVarargs() {
        MathSet<Integer> mathSet = new MathSetNumber<>(randomArray());
        int oldSize = mathSet.getSize();
        Integer[] elements = randomArray();
        mathSet.add(elements);
        Assertions.assertTrue(Arrays.equals(elements, mathSet.toArray(oldSize, mathSet.getSize() - 1)));
    }

    @Test
    public void join() {
        MathSet<Integer> mathSet = new MathSetNumber<>(randomArray());
        MathSet<Integer> emptySet = new MathSetNumber<>();
        emptySet.join(mathSet);
        Assertions.assertEquals(mathSet.getSize(), emptySet.getSize());
    }

    @Test
    public void joinVarargs() {
        Integer[] array = randomArray();
        MathSet<Integer> firstSet = new MathSetNumber<>(array);
        MathSet<Integer> secondSet = new MathSetNumber<>(array);
        MathSet<Integer> emptySet = new MathSetNumber<>();
        emptySet.join(firstSet, secondSet);
        Assertions.assertEquals(array.length, emptySet.getSize());
    }

    @Test
    public void sortDesc() {
        MathSet<Integer> mathSet = new MathSetNumber<>(randomArray());
        mathSet.sortDesc();
        Number[] a = mathSet.toArray();

        Assertions.assertTrue(checkDesc(a, 0, a.length - 1));
    }

    @Test
    public void sortDescWithIndexes() {
        MathSet<Integer> mathSet = new MathSetNumber<>(randomArray());
        int indexFrom = DEFAULT_CAPACITY/2 - DEFAULT_CAPACITY/3;
        int indexTo = DEFAULT_CAPACITY/2 + DEFAULT_CAPACITY/3;
        mathSet.sortDesc(indexFrom, indexTo);
        Number[] a = mathSet.toArray();

        Assertions.assertTrue(checkDesc(a, indexFrom, indexTo));
    }

    @Test
    public void sortDescWithElement() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        int elementIndex = rand.nextInt(DEFAULT_CAPACITY - 1);
        mathSet.sortDesc(array[elementIndex]);
        Number[] a = mathSet.toArray();

        Assertions.assertTrue(checkDesc(a, elementIndex, a.length - 1));
    }

    @Test
    public void sortAsc() {
        MathSet<Integer> mathSet = new MathSetNumber<>(randomArray());
        mathSet.sortAsc();
        Number[] a = mathSet.toArray();

        Assertions.assertTrue(checkAsc(a, 0, a.length - 1));
    }

    @Test
    public void sortAscWithIndexes() {
        MathSet<Integer> mathSet = new MathSetNumber<>(randomArray());
        int indexFrom = DEFAULT_CAPACITY/2 - DEFAULT_CAPACITY/3;
        int indexTo = DEFAULT_CAPACITY/2 + DEFAULT_CAPACITY/3;
        mathSet.sortAsc(indexFrom, indexTo);
        Number[] a = mathSet.toArray();

        Assertions.assertTrue(checkAsc(a, indexFrom, indexTo));
    }

    @Test
    public void sortAscWithElement() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        int elementIndex = rand.nextInt(DEFAULT_CAPACITY - 1);
        mathSet.sortAsc(array[elementIndex]);
        Number[] a = mathSet.toArray();

        Assertions.assertTrue(checkAsc(a, elementIndex, a.length - 1));
    }

    @Test
    public void get() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        int elementIndex = rand.nextInt(DEFAULT_CAPACITY - 1);
        Assertions.assertEquals(array[elementIndex], mathSet.get(elementIndex));
    }

    @Test
    public void getMax() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        Integer max = Arrays.stream(array)
                .max(Integer::compare)
                .get();
        Assertions.assertEquals(max, mathSet.getMax());
    }

    @Test
    public void getMin() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        Integer min = Arrays.stream(array)
                .min(Integer::compare)
                .get();
        Assertions.assertEquals(min, mathSet.getMin());
    }

    @Test
    public void getAverage() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        double average = Arrays.stream(array).mapToInt(Integer::intValue).average().orElse(Double.NaN);
        Assertions.assertEquals(average, mathSet.getAverage().doubleValue());
    }

    @Test
    public void getMedian() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        MathSet<Integer> emptySet = new MathSetNumber<>();
        Median median = new Median();
        double medianValue = median.evaluate(Arrays.stream(array).mapToDouble(i -> i).toArray());

        Assertions.assertEquals(medianValue, mathSet.getMedian().doubleValue());
        Assertions.assertThrows(IllegalStateException.class, emptySet::getMedian);
    }

    @Test
    public void toArray() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        Assertions.assertTrue(Arrays.equals(array,mathSet.toArray()));
    }

    @Test
    public void toArrayWithIndexes() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        int indexFrom = DEFAULT_CAPACITY/2 - DEFAULT_CAPACITY/3;
        int indexTo = DEFAULT_CAPACITY/2 + DEFAULT_CAPACITY/3;
        Integer[] arr = ArrayUtils.copyOfRange(array, indexFrom, indexTo, Integer[].class);

        Assertions.assertTrue(Arrays.equals(arr,mathSet.toArray(indexFrom, indexTo)));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> mathSet.toArray(-1, array.length));
        Assertions.assertThrows(IllegalArgumentException.class, () -> mathSet.toArray(indexTo, indexFrom));
    }

    @Test
    public void squash() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        int indexFrom = DEFAULT_CAPACITY/2 - DEFAULT_CAPACITY/3;
        int indexTo = DEFAULT_CAPACITY/2 + DEFAULT_CAPACITY/3;
        Integer[] arr = ArrayUtils.copyOfRange(array, indexFrom, indexTo, Integer[].class);
        MathSet<Integer> squashSet = mathSet.squash(indexFrom, indexTo);
        Assertions.assertTrue(Arrays.equals(arr, squashSet.toArray()));
    }

    @Test
    public void clear() {
        MathSet mathSet = new MathSetNumber(randomArray());
        mathSet.clear();
        boolean isAnyValue = false;
        for (Number n : mathSet.toArray()) {
            if (n != null) {
                isAnyValue = true;
                break;
            }
        }
        Assertions.assertFalse(isAnyValue);
    }

    @Test
    public void clearWithArray() {
        Integer[] array = randomArray();
        MathSet mathSet = new MathSetNumber(array);
        int indexFrom = DEFAULT_CAPACITY/2 - DEFAULT_CAPACITY/3;
        int indexTo = DEFAULT_CAPACITY/2 + DEFAULT_CAPACITY/3;
        Integer[] arr = ArrayUtils.copyOfRange(array, indexFrom, indexTo, Integer[].class);
        mathSet.clear(arr);
        boolean contains = Arrays.asList(mathSet.toArray()).containsAll(Arrays.asList(arr));
        Assertions.assertFalse(contains);
    }

    private boolean checkDesc(Number[] a, int indexFrom, int indexTo) {
        boolean descending = true;

        for (int i = indexFrom; i < indexTo; i++) {
            if (((Integer) a[i]).compareTo((Integer) a[i + 1]) < 0) {
                descending = false;
                break;
            }
        }
        return descending;
    }

    private boolean checkAsc(Number[] a, int indexFrom, int indexTo) {
        boolean descending = true;

        for (int i = indexFrom; i < indexTo; i++) {
            if (((Integer) a[i]).compareTo((Integer) a[i + 1]) > 0) {
                descending = false;
                break;
            }
        }
        return descending;
    }

    private Integer[] randomArray() {
        return rand.ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
    }

}
