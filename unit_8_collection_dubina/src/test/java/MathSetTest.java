import collection.service.MathSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathSetTest {

    @Test
    void createSet() {
        MathSet<Integer> mathSet = new MathSet<>();
        assertEquals(16, mathSet.getCapacity());
        assertEquals(0, mathSet.getCount());
    }

    @Test
    void add() {
        MathSet<Integer> mathSet = new MathSet<>();
        mathSet.add(1,2,3);
        assertEquals(16, mathSet.getCapacity());
        assertEquals(3, mathSet.getCount());
    }

    @Test
    void addDuplicate() {
        MathSet<Integer> mathSet = new MathSet<>();
        mathSet.add(1,2,3);
        mathSet.add(1);
        assertEquals(16, mathSet.getCapacity());
        assertEquals(3, mathSet.getCount());
    }

    @Test
    void join() {
        MathSet<Integer> mathSet = new MathSet<>();
        MathSet<Integer> mathSet1 = new MathSet<>();
        mathSet1.add(1,2,3);
        MathSet<Integer> set3 = new MathSet<>();
        set3.add(4,5,6);
        mathSet.join(mathSet1, set3);
        assertEquals(16, mathSet.getCapacity());
        assertEquals(6, mathSet.getCount());
        assertArrayEquals(new Integer[]{1,2,3,4,5,6}, mathSet.toArray());
    }

    @Test
    void squash() {
        MathSet<Integer> mathSet = new MathSet<>();
        mathSet.add(1,2,3,4);
        MathSet<Integer> mathSet1 = mathSet.squash(1, 2);
        assertArrayEquals(new Integer[]{2, 3}, mathSet1.toArray());
        assertEquals(2, mathSet1.getCount());
    }

    @Test
    void sortAsc() {
        MathSet<Integer> mathSet = new MathSet<>();
        mathSet.add(10,2,6,3);
        mathSet.sortAsc();
        assertArrayEquals(new Integer[]{2,3,6,10}, mathSet.toArray());
    }

    @Test
    void sortDsc() {
        MathSet<Integer> mathSet = new MathSet<>();
        mathSet.add(10,2,6,3);
        mathSet.sortDesc();
        assertArrayEquals(new Integer[]{10,6,3,2}, mathSet.toArray());
    }

    @Test
    void get() {
        MathSet<Integer> mathSet = new MathSet<>();
        mathSet.add(1,2,3,4);
        assertEquals(4, mathSet.get(3));
    }

    @Test
    void getMax() {
        MathSet<Integer> mathSet = new MathSet<>();
        mathSet.add(1,2,3,4);
        assertEquals(4, mathSet.getMax());
    }

    @Test
    void getMin() {
        MathSet<Integer> mathSet = new MathSet<>();
        mathSet.add(1,2,3);
        assertEquals(1, mathSet.getMin());
    }

    @Test
    void getAverage() {
        MathSet<Integer> mathSet = new MathSet<>();
        mathSet.add(1,2,3);
        assertEquals(2, mathSet.getAverage());
    }

    @Test
    void getMedian() {
        MathSet<Integer> mathSet = new MathSet<>();
        mathSet.add(10,20,30,40);
        assertEquals(25, mathSet.getMedian());
    }

    @Test
    void clearAllSet() {
        MathSet<Integer> mathSet = new MathSet<>();
        mathSet.add(1,2,3,4);
        mathSet.clear();
        assertEquals(16, mathSet.getCapacity());
        assertEquals(0, mathSet.getCount());
    }

    @Test
    void clearNumbers() {
        MathSet<Integer> mathSet = new MathSet<>();
        mathSet.add(1,2,3,4);
        mathSet.clear(new Integer[]{1,2});
        assertEquals(16, mathSet.getCapacity());
        assertEquals(2, mathSet.getCount());
        assertArrayEquals(new Integer[]{3,4}, mathSet.toArray());
    }
}

