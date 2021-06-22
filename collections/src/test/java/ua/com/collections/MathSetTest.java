package ua.com.collections;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathSetTest {

    public static Integer [] numbers = new Integer[] {3, 1, 5 ,24, 56, 2, 2};
    Integer [] numbers2 = new Integer[] {11, 12, 13 ,14, 15, 16, 17,
            18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
    Integer [] numbers3 = new Integer[] {110, 120, 130};
    MathSet<Integer> mathSet = new MathSet<Integer>(numbers);
    MathSet<Integer> mathSet2 = new MathSet<Integer>(numbers2);
    MathSet<Integer> mathSet3 = new MathSet<Integer>(numbers3);

    @Test
    public void createMathSet(){
        MathSet<Integer> mathSet0 = new MathSet<>(20);
        assertEquals(20, mathSet0.getArray().length);
    }

    @Test
    public void createMathSetWithNumbers(){
        assertEquals(6, mathSet.getCounter());
    }

    @Test
    public void setMathSets(){
        //24, 2 - идут по 2 раза
        MathSet<Integer> mathSets = new MathSet<Integer>(mathSet, mathSet2);
        assertEquals(25, mathSets.getCounter());
    }

    @Test
    public void addToMathSetNumbers() {
        mathSet.add(100,12,1);
        assertEquals(8, mathSet.getCounter());
    }

    @Test
    public void addToMathSetNumber() {
        mathSet.add(100);
        assertEquals(7, mathSet.getCounter());
    }

    @Test
    public void joinToMathSet() {
        mathSet.join(mathSet2);
        assertEquals(25, mathSet.getCounter());
    }

    @Test
    public void joinToMathSets() {
        mathSet.join(mathSet2, mathSet3);
        assertEquals(28, mathSet.getCounter());
    }

    @Test
    public void isUnique() {
        assertFalse(mathSet.isUnique(3));
    }

    @Test
    public void getByIndex() {
        Integer integer = 3;
        assertEquals(mathSet.get(1),integer);
    }

    @Test
    public void toArray() {
        assertArrayEquals(mathSet.toArray(), new Integer[] {3, 1, 5 ,24, 56, 2});
    }

    @Test
    public void toArrayParams() {
        assertArrayEquals(mathSet.toArray(1,4), new Integer[] {3, 1, 5 ,24});
    }

    @Test
    public void squash() {
        MathSet check = (mathSet.squash(1,3));
        Integer[] arr = {3, 1, 5};
        MathSet<Integer> check2 = new MathSet<Integer>(arr);
        assertArrayEquals(check2.toArray(), check2.toArray());
    }

    @Test
    public void getMax() {
        Integer integer = 56;
        assertEquals(integer, mathSet.getMax());
    }

    @Test
    public void getMin() {
        Integer integer = 1;
        assertEquals(integer, mathSet.getMin());
    }

    @Test
    public void getAverage() {
        double sum = 0;
        for (int i =1; i < mathSet.getCounter(); i++){
            sum += mathSet.get(i).doubleValue();
        }
        assertEquals(sum/mathSet.getCounter(), mathSet.getAverage(), 0.0);
    }

    @Test
    public void getMedian() {
        assertEquals(5, mathSet.getMedian());
    }

    @Test
    public void sortAsc() {
        mathSet.sortAsc();
        assertArrayEquals(mathSet.toArray(), new Integer[]{1, 2, 3, 5, 24, 56});
    }

    @Test
    public void sortDesc() {
        mathSet.sortDesc();
        assertArrayEquals(mathSet.toArray(), new Integer[]{56, 24, 5, 3, 2, 1});

    }

    @Test
    public void generalSortAsc03() {
        mathSet.sortAsc(1, 3);
        assertArrayEquals(mathSet.toArray(), new Integer[]{1, 3, 5, 24, 56, 2});
    }

    @Test
    public void generalSortDesc03() {
        mathSet.sortDesc(1, 3);
        assertArrayEquals(mathSet.toArray(), new Integer[]{5, 3, 1, 24, 56, 2});
    }

    @Test
    public void generalSortAscFromValue() {
        mathSet.sortAsc(1);
        assertArrayEquals(mathSet.toArray(), new Integer[]{3, 1, 2, 5, 24, 56});
    }

    @Test
    public void generalSortDescFromValue() {
        mathSet.sortDesc(1);
        assertArrayEquals(mathSet.toArray(), new Integer[]{3, 56, 24, 5, 2, 1});
    }

    @Test
    public void clear() {
        mathSet.clear();
        assertEquals(mathSet.getCounter(), 0);
        assertEquals(mathSet.getArray().length, 16);
    }

    @Test
    public void clearFromValues() {
        Integer[] arr = new Integer[]{3, 56};
        mathSet.clear(arr);
        assertEquals(mathSet.getCounter(), 4);
    }

    @Test
    public void clearFromValuesDouble() {
        Double[] arr = new Double[]{3.6, 56.6};
        Double[] arr1 = new Double[]{3.6, 5.4, 23.1, 56.6};
        MathSet<Double> mathSet4 = new MathSet<Double>();
        mathSet4.add(arr1);
        mathSet4.clear(arr);
        assertEquals(mathSet4.getCounter(), 2);
    }

}







