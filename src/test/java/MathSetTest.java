import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathSetTest {

    @Test
    void constructorTest() {
        MathSet<Double> set = new MathSet<>();
        assertEquals(16, set.getCapacity());
        assertEquals(0, set.getSize());
    }

    @Test
    void add() {
        MathSet<Double> set = new MathSet<>();
        set.add(5d, 6d, 7d);
        assertEquals(16, set.getCapacity());
        assertEquals(3, set.getSize());
    }

    @Test
    void addThrowsIfElementDuplicates() {
        MathSet<Double> set = new MathSet<>();
        set.add(5d, 6d, 7d);
        assertThrows(IllegalArgumentException.class, () -> set.add(5d));
        assertEquals(16, set.getCapacity());
        assertEquals(3, set.getSize());
    }

    @Test
    void join() {
        MathSet<Double> set = new MathSet<>();
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 6d, 7d);
        MathSet<Double> set3 = new MathSet<>();
        set3.add(2d, 4d, 8d);
        set.join(set2, set3);
        assertEquals(16, set.getCapacity());
        assertEquals(6, set.getSize());
        assertArrayEquals(new Double[]{5d, 6d, 7d, 2d, 4d, 8d}, set.toArray());
    }

    @Test
    void testToArray() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 6d, 7d, 8d);
        assertArrayEquals(new Double[]{6d, 7d}, set2.toArray(1, 3));
    }

    @Test
    void squash() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 6d, 7d, 8d);
        MathSet<Double> set3 = set2.squash(1, 3);
        assertArrayEquals(new Double[]{6d, 7d}, set3.toArray());
        assertEquals(2, set2.getSize());
        assertArrayEquals(new Double[]{5d, 8d}, set2.toArray());
    }

    @Test
    void increaseCapacity() {
        MathSet<Double> set2 = new MathSet<>();
        for (int i = 0; i < 17; i++) {
            set2.add((double) i);
        }
        assertEquals(25, set2.getCapacity());
    }

    @Test
    void sortAsc() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 2d, 10d, 8d);
        set2.sortAsc();
        assertArrayEquals(new Double[]{2d, 5d, 8d, 10d}, set2.toArray());
    }

    @Test
    void sortDsc() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 2d, 10d, 8d);
        set2.sortDsc();
        assertArrayEquals(new Double[]{10d, 8d, 5d, 2d}, set2.toArray());
    }

    @Test
    void get() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 2d, 10d, 8d);
        assertEquals(2d, set2.get(1));
    }

    @Test
    void getMax() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 2d, 10d, 8d);
        assertEquals(10d, set2.getMax());
    }

    @Test
    void getMin() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 2d, 10d, 8d);
        assertEquals(2d, set2.getMin());
    }

    @Test
    void getAverage() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 2d, 10d, 8d);
        assertEquals(6.25, set2.getAverage());
    }

    @Test
    void getMedian() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 2d, 10d, 7d);
        assertEquals(6d, set2.getMedian());
    }

    @Test
    void getMedian1() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 2d, 10d, 7d, 9d);
        assertEquals(7d, set2.getMedian());
    }

    @Test
    void clear() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 2d, 10d, 8d);
        set2.clear();
        assertEquals(16, set2.getCapacity());
        assertEquals(0, set2.getSize());
    }

    @Test
    void testClear() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 2d, 10d, 8d);
        set2.clear(new Double[]{2d,10d});
        assertEquals(16, set2.getCapacity());
        assertEquals(2, set2.getSize());
        assertArrayEquals(new Double[]{5d,8d}, set2.toArray());
    }
}
