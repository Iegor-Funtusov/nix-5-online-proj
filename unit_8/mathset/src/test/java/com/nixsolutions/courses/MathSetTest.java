package com.nixsolutions.courses;

import com.nixsolutions.courses.utils.ArrayUtils;
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
    }

    @Test
    public void arrayConstructor() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        Assertions.assertEquals(array.length, mathSet.getSize());
    }

    @Test
    public void arrayVarargsConstructor() {
        Integer[][] array = IntStream.range(0, DEFAULT_CAPACITY)
                .mapToObj(rowArray -> randomArray())
                .toArray(Integer[][]::new);
        int distinct = (int) Arrays.stream(array).flatMap(Arrays::stream).distinct().count();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        Assertions.assertEquals(distinct, mathSet.getSize());
    }

    @Test
    public void joinMathSet() {
        MathSet<Integer> mathSet = new MathSetNumber<>(randomArray());
        MathSet<Integer> emptySet = new MathSetNumber<>();
        emptySet.join(mathSet);
        Assertions.assertEquals(mathSet.getSize(), emptySet.getSize());
    }

    @Test
    public void joinVarargsMathSet() {
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
        boolean descending = true;
        Number[] a = mathSet.toArray();
        for (int i = 0; i < a.length - 1; i++) {
            if (((Integer) a[i]).compareTo((Integer) a[i + 1]) < 0) {
                descending = false;
                break;
            }
        }
        Assertions.assertTrue(descending);
    }

    @Test
    public void sortDescWithIndexes() {
        MathSet<Integer> mathSet = new MathSetNumber<>(randomArray());
        int indexFrom = DEFAULT_CAPACITY/2 - DEFAULT_CAPACITY/3;
        int indexTo = DEFAULT_CAPACITY/2 + DEFAULT_CAPACITY/3;
        mathSet.sortDesc(indexFrom, indexTo);
        boolean descending = true;
        Number[] a = mathSet.toArray();
        for (int i = indexFrom; i < indexTo - 1; i++) {
            if (((Integer) a[i]).compareTo((Integer) a[i + 1]) < 0) {
                descending = false;
                break;
            }
        }
        Assertions.assertTrue(descending);
    }

    @Test
    public void sortDescWithElement() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        int elementIndex = rand.nextInt(DEFAULT_CAPACITY - 1);
        mathSet.sortDesc(array[elementIndex]);
        boolean descending = true;
        Number[] a = mathSet.toArray();
        for (int i = elementIndex; i < a.length - 1; i++) {
            if (((Integer) a[i]).compareTo((Integer) a[i + 1]) < 0) {
                descending = false;
                break;
            }
        }
        Assertions.assertTrue(descending);
    }

    @Test
    public void sortAsc() {
        MathSet<Integer> mathSet = new MathSetNumber<>(randomArray());
        mathSet.sortAsc();
        boolean ascending = true;
        Number[] a = mathSet.toArray();
        for (int i = 0; i < a.length - 1; i++) {
            if (((Integer) a[i]).compareTo((Integer) a[i + 1]) > 0) {
                ascending = false;
                break;
            }
        }
        Assertions.assertTrue(ascending);
    }

    @Test
    public void sortAscWithIndexes() {
        MathSet<Integer> mathSet = new MathSetNumber<>(randomArray());
        int indexFrom = DEFAULT_CAPACITY/2 - DEFAULT_CAPACITY/3;
        int indexTo = DEFAULT_CAPACITY/2 + DEFAULT_CAPACITY/3;
        mathSet.sortDesc(indexFrom, indexTo);
        boolean descending = true;
        Number[] a = mathSet.toArray();
        for (int i = indexFrom; i < indexTo - 1; i++) {
            if (((Integer) a[i]).compareTo((Integer) a[i + 1]) < 0) {
                descending = false;
                break;
            }
        }
        Assertions.assertTrue(descending);
    }

    @Test
    public void sortAscWithElement() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        int elementIndex = rand.nextInt(DEFAULT_CAPACITY - 1);
        mathSet.sortDesc(array[elementIndex]);
        boolean ascending = true;
        Number[] a = mathSet.toArray();
        for (int i = elementIndex; i < a.length - 1; i++) {
            if (((Integer) a[i]).compareTo((Integer) a[i + 1]) < 0) {
                ascending = false;
                break;
            }
        }
        Assertions.assertTrue(ascending);
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
        Assertions.assertEquals(average, mathSet.getAverage());
    }

    @Test
    public void getMedian() {
        Integer[] array = randomArray();
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        Median median = new Median();
        double medianValue = median.evaluate(Arrays.stream(array).mapToDouble(i -> i).toArray());
        Assertions.assertEquals(medianValue, mathSet.getMedian());
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
    }

    @Test
    public void clear() {
        MathSet<Integer> mathSet = new MathSetNumber<>(randomArray());
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
        MathSet<Integer> mathSet = new MathSetNumber<>(array);
        int indexFrom = DEFAULT_CAPACITY/2 - DEFAULT_CAPACITY/3;
        int indexTo = DEFAULT_CAPACITY/2 + DEFAULT_CAPACITY/3;
        Integer[] arr = ArrayUtils.copyOfRange(array, indexFrom, indexTo, Integer[].class);
        mathSet.clear(arr);
        boolean contains = Arrays.asList(mathSet.toArray()).containsAll(Arrays.asList(arr));
        Assertions.assertFalse(contains);
    }

    private Integer[] randomArray() {
        return rand.ints(DEFAULT_CAPACITY, RAND_MIN, RAND_MAX)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);
    }

}
