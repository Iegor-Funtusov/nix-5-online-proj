import MathSet.MathSet;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MathSetTest {
    @Test
    @Order(1)
    public void checkEmptyConstructor(){
        MathSet<Integer> mathSet = new MathSet<Integer>();
        Assertions.assertTrue(mathSet.length() == 0);
    }

    @Order(2)
    @Test
    public void checkConstructorWithSize(){
        assertThrows(NegativeArraySizeException.class, () -> new MathSet<Integer>(-1));
    }

    @Order(3)
    @Test
    public void checkConstructorWithArray(){
        Integer[] array = new Integer[10];
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }
        MathSet<Integer> mathSet = new MathSet<Integer>(array);
        Assertions.assertTrue(mathSet.length() == 10);
    }

    @Order(4)
    @Test
    public void checkConstructorWithArrays(){
        Integer[] array = new Integer[10];
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }
        Integer[] array1 = new Integer[10];
        for (int i = 0; i < 10; i++) {
            array1[i] = i+10;
        }

        MathSet<Integer> mathSet = new MathSet<>(array, array1);
        Assertions.assertTrue(mathSet.length() == 20);
    }

    @Order(5)
    @Test
    public void addValue(){
        MathSet<Integer> set = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set.add(i);
        }
        Assertions.assertTrue(set.length() == 10);
    }

    @Order(6)
    @Test
    public void addValues(){
        MathSet<Integer> set = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set.add(i, i+10);
        }
        Assertions.assertTrue(set.length() == 20);
    }

    @Order(7)
    @Test
    public void join(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        MathSet<Integer> set2 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
            set2.add(i+10);
        }
        set1.join(set2);
        Assertions.assertTrue(set1.length() == 20);
    }

    @Order(8)
    @Test
    public void joinSomeSets(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        MathSet<Integer> set2 = new MathSet<Integer>();
        MathSet<Integer> set3 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
            set2.add(i+10);
            set3.add(i+20);
        }
        set1.join(set2, set3);
        Assertions.assertTrue(set1.length() == 30);
    }

    @Order(9)
    @Test
    public void get(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
        }
        Assertions.assertTrue(set1.get(3) == 3);
    }


    @Order(10)
    @Test
    public void sortDesc(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
        }
        set1.sortDesc();
        Assertions.assertTrue(set1.get(0) == 9);
    }

    @Order(11)
    @Test
    public void sortDescWithIndexes(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
        }
        int num = set1.get(5);
        set1.sortDesc(2, 5);
        Assertions.assertTrue(set1.get(2) == num);
    }

    @Order(12)
    @Test
    public void sortDescWithValue(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
        }
        set1.sortDesc(6);
        Assertions.assertTrue(set1.get(6) == 9);
    }

    @Order(13)
    @Test
    public void sortAsc(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 9; i >= 0; i--) {
            set1.add(i);
        }
        set1.sortAsc();
        Assertions.assertTrue(set1.get(0) == 0);
    }

    @Order(14)
    @Test
    public void sortAscWithIndexes(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 9; i >= 0; i--) {
            set1.add(i);
        }
        int num = set1.get(5);
        set1.sortAsc(2, 5);
        Assertions.assertTrue(set1.get(2) == num);
    }

    @Order(15)
    @Test
    public void sortAscWithValue(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 9; i >= 0; i--) {
            set1.add(i);
        }
        set1.sortAsc(6);
        Assertions.assertTrue(set1.get(3) == 0);
    }

    @Order(16)
    @Test
    public void getMax(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
        }
        Assertions.assertTrue(set1.getMax() == 9);
    }

    @Order(17)
    @Test
    public void getMin(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
        }
        Assertions.assertTrue(set1.getMin() == 0);
    }

    @Order(18)
    @Test
    public void getAverage(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            set1.add(i);
            sum += i;
        }
        double result = (double) sum/set1.length();
        Assertions.assertTrue(set1.getAverage() == result);
    }

    @Order(19)
    @Test
    public void getMedian(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
        }
        set1.getMedian();
        Assertions.assertTrue(set1.getAverage() == 4.5);
    }

    @Order(20)
    @Test
    public void toArray(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
        }
        Object[] array = set1.toArray();
        Assertions.assertTrue((int)array[4] == 4 && array.length == 10);
    }

    @Order(21)
    @Test
    public void toArrayWithIndexes(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
        }
        Object[] array = set1.toArray(1, 5);
        Assertions.assertTrue((int)array[1] == 2 && array.length == 4);
    }

    @Order(22)
    @Test
    public void squash(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
        }
        MathSet<Integer> set2 = set1.squash(1, 5);
        Assertions.assertTrue(set2.get(0) == 1 && set2.length() == 5);
    }

    @Order(23)
    @Test
    public void clear(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
        }
        set1.clear();
        Assertions.assertTrue(set1.length() == 0);
    }

    @Order(24)
    @Test
    public void clearNumbers(){
        MathSet<Integer> set1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            set1.add(i);
        }
        Integer[] array = new Integer[3];
        array[0] = 3;
        array[1] = 7;
        array[2] = 12;
        set1.clear(array);
        Assertions.assertTrue(set1.length() == 8);
    }

    @Order(25)
    @Test
    public void checkConstructorWithMathSet(){
        MathSet<Integer> mathSet = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            mathSet.add(i);
        }
        MathSet<Integer> mathSet1 = new MathSet<Integer>(mathSet);
        Assertions.assertTrue(mathSet1.length() == 10);
    }

    @Order(26)
    @Test
    public void checkConstructorWithMathSets(){
        MathSet<Integer> mathSet = new MathSet<Integer>();
        MathSet<Integer> mathSet1 = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            mathSet.add(i);
            mathSet1.add(i+10);
        }
        MathSet<Integer> mathSet2 = new MathSet<Integer>(mathSet,mathSet1);
        Assertions.assertTrue(mathSet2.length() == 20);
    }

    @Order(27)
    @Test
    public void checkAllExceptions(){
        MathSet<Integer> mathSet = new MathSet<Integer>();
        for (int i = 0; i < 10; i++) {
            mathSet.add(i);
        }
        MathSet<Integer> set = new MathSet<Integer>();

        assertThrows(NullPointerException.class, () -> mathSet.add((Integer) null));
        assertThrows(IllegalArgumentException.class, () -> mathSet.sortDesc(1,0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> mathSet.sortDesc(-1,4));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> mathSet.sortDesc(1,11));
        assertThrows(NullPointerException.class, () -> mathSet.sortDesc( null));
        assertThrows(IllegalArgumentException.class, () -> mathSet.sortAsc(1,0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> mathSet.sortAsc(-1,4));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> mathSet.sortAsc(1,11));
        assertThrows(NullPointerException.class, () -> mathSet.sortAsc( null));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> mathSet.get(11));
        assertThrows(RuntimeException.class, () -> set.getMax());
        assertThrows(RuntimeException.class, () -> set.getMin());
        assertThrows(RuntimeException.class, () -> set.getAverage());
        assertThrows(RuntimeException.class, () -> set.getMedian());
        assertThrows(IllegalArgumentException.class, () -> mathSet.toArray(1,0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> mathSet.toArray(-1,4));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> mathSet.toArray(1,11));
        assertThrows(IllegalArgumentException.class, () -> mathSet.squash(1,0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> mathSet.squash(-1,4));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> mathSet.squash(1,11));
        assertThrows(NullPointerException.class, () -> mathSet.clear( null));
    }
}
