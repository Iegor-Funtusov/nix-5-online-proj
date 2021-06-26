import mathset.MathSet;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MathSetTests {

    @BeforeAll
    @SuppressWarnings("unchecked")
    public static void setUp() {
        MathSet set = new MathSet(Number.class);
        set.add(1, 2L, 3f, 4.0, 5, 6.0);
        Assertions.assertEquals(set.getSize(), 6);
    }

    @Test
    @Order(1)
    public void createEmptySet() {
        MathSet<Integer> set = new MathSet<>(Integer.class);
        Assertions.assertEquals(set.getSize(), 0);
    }

    @Test
    @Order(2)
    public void createCapacityTest() {
        MathSet<Double> set = new MathSet<>(Double.class, 16);
        for (int i = 0; i < 6; i++) {
            int sign = Math.random() < 0.5 ? -1 : 1;
            set.add((Math.random() * 100) * sign);
        }
        Assertions.assertNotNull(set.get(5));
    }

    @Test
    @Order(3)
    public void createArraySet() {
        Integer[] inums = {1, 2, 3, 4, 5, 6};
        MathSet<Integer> set = new MathSet<>(Integer.class, inums);
        Assertions.assertTrue(set.contains(5));
    }

    @Test
    @Order(4)
    public void createArraysSet() {
        Double[] dnums1 = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        Double[] dnums2 = {7.0, 8.0, 9.0, 10.0, 11.0, 12.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums1, dnums2);
        Assertions.assertEquals(dnums1.length + dnums2.length, set.getSize());
    }

    @Test
    @Order(5)
    public void createSetSet() {
        MathSet<Double> mathSet = new MathSet<>(Double.class, 6);
        for (int i = 0; i < 6; i++) {
            int sign = Math.random() < 0.5 ? -1 : 1;
            mathSet.add((Math.random() * 100) * sign);
        }
        MathSet<Double> set = new MathSet<>(Double.class, mathSet);
        System.out.println(mathSet.get(0) + " = " + set.get(0));
        Assertions.assertSame(set.get(0), mathSet.get(0));
    }

    @Test
    @Order(6)
    public void createSetsSet() {
        MathSet<Double> mathSet1 = new MathSet<>(Double.class, new Double[]{1.0, 2.0, 3.0});
        MathSet<Double> mathSet2 = new MathSet<>(Double.class, new Double[]{4.0, 5.0, 6.0});

        MathSet<Double> set = new MathSet<>(Double.class, mathSet1, mathSet2);
        Assertions.assertSame(set.getSize(), 6);
    }

    @Test
    @Order(7)
    public void addNumber() {
        MathSet<Integer> set = new MathSet<>(Integer.class, new Integer[]{1, 2, 3});
        set.add(5);
        System.out.println(Arrays.toString(set.toArray()));
        Assertions.assertSame(set.get(3), 5);
    }

    @Test
    @Order(8)
    public void addNumbers() {
        MathSet<Integer> set = new MathSet<>(Integer.class, new Integer[]{1, 2, 3});
        set.add(5, 6, 7);
        System.out.println(Arrays.toString(set.toArray()));
        Assertions.assertSame(set.get(5),7);
    }

    @Test
    @Order(9)
    public void joinSet() {
        MathSet<Double> mathSet = new MathSet<>(Double.class, 6);
        for (int i = 0; i < 6; i++) {
            int sign = Math.random() < 0.5 ? -1 : 1;
            mathSet.add((Math.random() * 100) * sign);
        }
        MathSet<Double> set = new MathSet<>(Double.class, 5);
        set.join(mathSet);
        Assertions.assertSame(set.getSize(), mathSet.getSize());
    }

    @Test
    @Order(10)
    public void sortDesc() {
        Double[] dnums1 = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        Double[] dnums2 = {6.0, 5.0, 4.0, 3.0, 2.0, 1.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums1);
        set.sortDesc();
        assertArrayEquals(dnums2, set.toArray());
    }

    @Test
    @Order(11)
    public void sortDescByIndexes() {
        Double[] dnums1 = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        Double[] dnums2 = {6.0, 5.0, 3.0, 1.0, 4.0, 2.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums1);
        set.sortDesc(0, 3);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(dnums2, set.toArray());
    }

    @Test
    @Order(12)
    public void sortDescByValue() {
        Double[] dnums1 = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        Double[] dnums2 = {5.0, 1.0, 6.0, 4.0, 3.0, 2.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums1);
        set.sortDesc(3.0);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(dnums2, set.toArray());
    }

    @Test
    @Order(13)
    public void sortAsc() {
        Double[] dnums1 = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        Double[] dnums2 = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums1);
        set.sortAsc();
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(dnums2, set.toArray());
    }

    @Test
    @Order(14)
    public void sortAscByIndexes() {
        Double[] dnums1 = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        Double[] dnums2 = {1.0, 3.0, 5.0, 6.0, 4.0, 2.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums1);
        set.sortAsc(0, 3);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(dnums2, set.toArray());
    }

    @Test
    @Order(15)
    public void sortAscByValue() {
        Double[] dnums1 = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        Double[] dnums2 = {5.0, 1.0, 2.0, 3.0, 4.0, 6.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums1);
        set.sortAsc(3.0);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(dnums2, set.toArray());
    }

    @Test
    @Order(16)
    public void getNumber() {
        Double[] dnums = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums);
        Number number = set.get(3);
        Assertions.assertEquals(number, 6.0);
    }

    @Test
    @Order(17)
    public void getMax() {
        Double[] dnums = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums);
        Number number = set.getMax();
        Assertions.assertEquals(number, 6.0);
    }

    @Test
    @Order(18)
    public void getMin() {
        Double[] dnums = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums);
        Number number = set.getMin();
        Assertions.assertEquals(number, 1.0);
    }

    @Test
    @Order(19)
    public void getAverage() {
        Double[] dnums = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums);
        Number number = set.getAverage();
        Assertions.assertEquals(number, 3.5);
    }

    @Test
    @Order(20)
    public void getMedian() {
        Double[] dnums = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums);
        Number number = set.getAverage();
        Assertions.assertEquals(number, 3.5);
        set.add(7.0);
        number = set.getAverage();
        Assertions.assertEquals(number, 4.0);
    }

    @Test
    @Order(21)
    public void squash() {
        Double[] dnums = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        MathSet<Double> mathSet = new MathSet<>(Double.class, dnums);
        MathSet<Double> set = mathSet.squash(Double.class, 0, 3);
        System.out.println(Arrays.toString(set.toArray()));
        Double[] dnums3 = {5.0, 1.0, 3.0};
        assertArrayEquals(dnums3, set.toArray());
    }

    @Test
    @Order(22)
    public void clear() {
        Double[] dnums = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums);
        set.clear();
        System.out.println(Arrays.toString(set.toArray()));
        Assertions.assertEquals(set.getSize(), 0);
    }

    @Test
    @Order(23)
    public void clearByArray() {
        Double[] dnums = {5.0, 1.0, 3.0, 6.0, 4.0, 2.0};
        MathSet<Double> set = new MathSet<>(Double.class, dnums);
        Double[] dnums2 = {5.0, 3.0, 6.0};
        set.clear(dnums2);
        System.out.println(Arrays.toString(set.toArray()));
        Double[] dnums3 = {1.0, 4.0, 2.0};
        assertArrayEquals(dnums3, set.toArray());
    }
}
