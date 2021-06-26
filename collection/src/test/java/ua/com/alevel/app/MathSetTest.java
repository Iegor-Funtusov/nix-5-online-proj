package ua.com.alevel.app;

import org.junit.jupiter.api.Test;
import ua.com.alevel.app.service.MathSet;

import static org.junit.jupiter.api.Assertions.*;

public class MathSetTest {

    @Test
    void constructorTest() {
        MathSet<Double> set = new MathSet<>();
        assertEquals(10, set.getCapacity());
        assertEquals(0, set.getLastIndex());
    }

    @Test
    void add() {
        MathSet<Double> set = new MathSet<>();
        set.add(1d, 2d, 3d);
        assertEquals(10, set.getCapacity());
        assertEquals(3, set.getLastIndex());
    }

    @Test
    void addThrowsIfElementDuplicates() {
        MathSet<Double> set = new MathSet<>();
        set.add(1d, 2d, 3d);
        assertThrows(IllegalArgumentException.class, () -> set.add(3d));
        assertEquals(10, set.getCapacity());
        assertEquals(3, set.getLastIndex());
    }

    @Test
    void join() {
        MathSet<Double> set = new MathSet<>();
        MathSet<Double> set2 = new MathSet<>();
        set2.add(1d, 2d, 3d);
        MathSet<Double> set3 = new MathSet<>();
        set3.add(4d, 5d, 6d);
        set.join(set2, set3);
        assertEquals(10, set.getCapacity());
        assertEquals(6, set.getLastIndex());
        assertArrayEquals(new Double[]{1d, 2d, 3d, 4d, 5d, 6d}, set.toArray());
    }

    @Test
    void testToArray() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(1d, 2d, 3d, 4d);
        assertArrayEquals(new Double[]{2d, 3d}, set2.toArray(1, 3));
    }

    @Test
    void squash() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(1d, 2d, 3d, 4d);
        MathSet<Double> set3 = set2.squash(1, 3);
        assertArrayEquals(new Double[]{2d, 3d}, set3.toArray());
        assertEquals(2, set2.getLastIndex());
        assertArrayEquals(new Double[]{1d, 4d}, set2.toArray());
    }

    @Test
    void increaseCapacity() {
        MathSet<Double> set2 = new MathSet<>();
        for (int i = 0; i <= 10; i++) {
            set2.add((double) i);
        }
        assertEquals(16, set2.getCapacity());
    }

    @Test
    void sortAsc() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(3d, 1d, 8d, 6d);
        set2.sortAsc();
        assertArrayEquals(new Double[]{1d, 3d, 6d, 8d}, set2.toArray());
    }

    @Test
    void sortDsc() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(3d, 1d, 8d, 6d);
        set2.sortDesc();
        assertArrayEquals(new Double[]{8d, 6d, 3d, 1d}, set2.toArray());
    }

    @Test
    void get() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(1d, 2d, 3d, 4d);
        assertEquals(2d, set2.get(1));
    }

    @Test
    void getMax() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(1d, 2d, 3d, 4d);
        assertEquals(4d, set2.getMax());
    }

    @Test
    void getMin() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(1d, 2d, 3d, 4d);
        assertEquals(1d, set2.getMin());
    }

    @Test
    void getAverage() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(1d, 2d, 3d, 4d);
        assertEquals(2.5, set2.getAverage());
    }

    @Test
    void getMedian() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(5d, 2d, 10d, 7d);
        assertEquals(6d, set2.getMedian());
    }

    @Test
    void clear() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(1d, 2d, 3d, 4d);
        set2.clear();
        assertEquals(10, set2.getCapacity());
        assertEquals(0, set2.getLastIndex());
    }

    @Test
    void testClear() {
        MathSet<Double> set2 = new MathSet<>();
        set2.add(1d, 2d, 3d, 4d);
        set2.clear(new Double[]{2d,3d});
        assertEquals(10, set2.getCapacity());
        assertEquals(2, set2.getLastIndex());
        assertArrayEquals(new Double[]{1d,4d}, set2.toArray());
    }
}
