package com.nixsolutions.courses;

import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class MathSetTest {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int RAND_MIN = 0;
    private static final int RAND_MAX = 1000;

    @Test
    public void emptyConstructor() {
        MathSet<Integer> mathSet = new MathSetNumber<>();
        Assertions.assertNotNull(mathSet, "Unable to create instance");
    }

    @Test
    public void capacityConstructor() {
        MathSet<Integer> mathSet = new MathSetNumber<>(DEFAULT_CAPACITY);
        Assertions.assertNotNull(mathSet, "Unable to create instance");
    }

    @Test
    public void arrayConstructor() {
        Integer[] array = new Random().ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        Assertions.assertEquals(array.length, mathSet.getSize());
    }

    @Test
    public void arrayVarargsConstructor() {
        Integer[][] array = IntStream.range(0, 5)
                .mapToObj(rowArray -> new Random().ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                        .distinct()
                        .boxed()
                        .toArray(Integer[]::new))
                .distinct()
                .toArray(Integer[][]::new);
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        int arrayElements = 0;
        for (Integer[] integers : array) {
            arrayElements += integers.length;
        }
        Assertions.assertEquals(arrayElements, mathSet.getSize());
    }

    @Test
    public void joinMathSet() {
        MathSet<Integer> mathSet = createFilledSet();
        MathSet<Integer> emptySet = new MathSetNumber<>();
        emptySet.join(mathSet);
        Assertions.assertEquals(mathSet.getSize(), emptySet.getSize());
    }

    @Test
    public void joinVarargsMathSet() {
        Integer[] array = new Random().ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        MathSet<Integer> firstSet = new MathSetNumber<>(array);
        MathSet<Integer> secondSet = new MathSetNumber<>(array);
        MathSet<Integer> emptySet = new MathSetNumber<>();
        emptySet.join(firstSet, secondSet);
        Assertions.assertEquals(firstSet.getSize(), emptySet.getSize());
    }

    @Test
    public void sortDesc() {
        Integer[] array = new Random().ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        mathSet.sortDesc();
        Assertions.assertEquals(mathSet.getMax(), mathSet.toArray()[0]);
    }

    @Test
    public void sortDescWithIndexes() {
        Integer[] array = new Random().ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        mathSet.sortDesc(2, 5);
        Integer maxOfRange = Arrays.stream(Arrays.copyOfRange(array, 2, 5))
                .max(Integer::compare)
                .get();
        Assertions.assertEquals(maxOfRange, mathSet.toArray()[2]);
    }

    @Test
    public void sortDescWithElement() {
        Integer[] array = new Random().ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        mathSet.sortDesc(array[2]);
        Integer maxOfRange = Arrays.stream(Arrays.copyOfRange(array, 2, array.length - 1))
                .max(Integer::compare)
                .get();
        Assertions.assertEquals(maxOfRange, mathSet.toArray()[2]);
    }

    @Test
    public void sortAsc() {
        Integer[] array = new Random().ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        mathSet.sortAsc();
        Assertions.assertEquals(mathSet.getMin(), mathSet.toArray()[0]);
    }

    @Test
    public void sortAscWithIndexes() {
        Integer[] array = new Random().ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        mathSet.sortAsc(2, 5);
        Integer maxOfRange = Arrays.stream(Arrays.copyOfRange(array, 2, 5))
                .min(Integer::compare)
                .get();
        Assertions.assertEquals(maxOfRange, mathSet.toArray()[2]);
    }

    @Test
    public void get() {
        Integer[] array = new Random().ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        Assertions.assertEquals(array[2], mathSet.get(2));
    }

    @Test
    public void getMax() {
        MathSet<Integer> mathSet = createFilledSet();
        Integer max = Arrays.stream(mathSet.toArray())
                .map(i -> (Integer) i)
                .max(Integer::compare)
                .get();
        Assertions.assertEquals(max, mathSet.getMax());
    }

    @Test
    public void getMin() {
        MathSet<Integer> mathSet = createFilledSet();
        Integer min = Arrays.stream(mathSet.toArray())
                .map(i -> (Integer) i)
                .min(Integer::compare)
                .get();
        Assertions.assertEquals(min, mathSet.getMin());
    }

    @Test
    public void getAverage() {
        Integer[] array = new Random().ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        double average = Arrays.stream(array).mapToInt(Integer::intValue).average().orElse(Double.NaN);
        Assertions.assertEquals(average, mathSet.getAverage());
    }

    @Test
    public void getMedian() {
        Integer[] array = new Random().ints(DEFAULT_CAPACITY + 1, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        Arrays.stream(array).sorted().forEach(System.out::println);
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        Median median = new Median();
        double medianValue = median.evaluate(Arrays.stream(array).mapToDouble(i -> i).toArray());
        Assertions.assertEquals(medianValue, mathSet.getMedian().doubleValue());
    }

    @Test
    public void toArrayEmpty() {
        Integer[] array = new Random().ints(DEFAULT_CAPACITY + 1, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        Assertions.assertEquals(array, mathSet.toArray());
    }

    @Test
    public void clear() {
        MathSet<Integer> mathSet = createFilledSet();
        mathSet.clear();
        boolean isAnyValue = false;
        for (Object o : mathSet.toArray()) {
            if (o != null) {
                isAnyValue = true;
                break;
            }
        }
        Assertions.assertFalse(isAnyValue);
    }

    private MathSet<Integer> createFilledSet() {
        Integer[] array = new Random().ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
        return new MathSetNumber<>(array);
    }

}
