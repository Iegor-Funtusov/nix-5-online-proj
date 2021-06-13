package ua.com.nkrasnovoronka.mathset;


import org.junit.jupiter.api.*;
import ua.com.nkrasnovoronka.mathset.impl.MathSetImpl;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MathSetTest {
    private static MathSet mathSet = new MathSetImpl();

    @BeforeEach
    private void cleanMathSet(){
        mathSet.clear();
    }

    @Test
    @Order(1)
    void addElementsToMathSet(){
        for (int i = 0; i < 5; i++) {
            mathSet.add(i);
        }
        assertEquals("[0, 1, 2, 3, 4]", mathSet.toString());
    }

    @Test
    @Order(2)
    void throwExceptionIfCapacityIsNegative(){
        assertThrows(IllegalArgumentException.class, () ->new MathSetImpl(-11));
    }

    @Test
    @Order(3)
    void setMathSetCapacity(){
        MathSet testMathSet = new MathSetImpl(5);
        try {
            Field numbers = testMathSet.getClass().getDeclaredField("numbers");
            numbers.setAccessible(true);
            assertEquals(5, Array.getLength(numbers.get(testMathSet)));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    void setMathSetByNumbersArray(){
        MathSet mathSet = new MathSetImpl(new Number[]{1, 2, 3});
        assertEquals("[1, 2, 3]", mathSet.toString());
        assertEquals(3, mathSet.getSize());
    }

    @Test
    @Order(4)
    void setMathSetByNumbersArrays(){
        MathSet mathSet = new MathSetImpl(new Number[]{1, 2, 3}, new Number[]{3, 4, 5});
        assertEquals("[1, 2, 3, 4, 5]", mathSet.toString());
        assertEquals(5, mathSet.getSize());
    }

    @Test
    @Order(5)
    void setMathSetByAnotherMathSet(){
        MathSet mathSet = new MathSetImpl(new Number[]{1, 2, 3});
        MathSet mathSet1 = new MathSetImpl(mathSet);
        assertEquals("[1, 2, 3]", mathSet1.toString());
    }

    @Test
    @Order(6)
    void setMathSetByAnotherMathSets(){
        MathSet mathSet = new MathSetImpl(new Number[]{1, 2, 3});
        MathSet mathSet1 = new MathSetImpl(new Number[]{1, 2, 3, 4, 5});
        MathSet mathSet2 = new MathSetImpl(mathSet, mathSet1);
        assertEquals("[1, 2, 3, 4, 5]", mathSet2.toString());
    }

    @Test
    @Order(7)
    void joinMathSet(){
        MathSet mathSet = new MathSetImpl(new Number[]{1, 2, 3});
        MathSet mathSet1 = new MathSetImpl(new Number[]{1, 2, 3, 4, 5});
        mathSet.join(mathSet1);
        assertEquals("[1, 2, 3, 4, 5]", mathSet.toString());
    }

    @Test
    @Order(8)
    void joinMathSets(){
        MathSet mathSet = new MathSetImpl(new Number[]{1, 2, 3});
        MathSet mathSet1 = new MathSetImpl(new Number[]{1, 2, 3, 4, 5});
        MathSet mathSet2 = new MathSetImpl(new Number[]{11, 12, 13});
        mathSet.join(mathSet1, mathSet2);
        assertEquals("[1, 2, 3, 4, 5, 11, 12, 13]", mathSet.toString());
    }

    @Test
    @Order(9)
    void sortAsc(){
        mathSet.add(6, 2, 4, 5, 0);
        mathSet.sortAsc();
        assertEquals("[0, 2, 4, 5, 6]", mathSet.toString());
    }

    @Test
    @Order(9)
    void sortDesc(){
        mathSet.add(6, 2, 4, 5, 0);
        mathSet.sortDesc();
        assertEquals("[6, 5, 4, 2, 0]", mathSet.toString());
    }

    @Test
    @Order(10)
    void sortAscFromIndexToIndex(){
        mathSet.add(6, 2, 4, 5, 0);
        mathSet.sortAsc(1, 4);
        assertEquals("[6, 0, 2, 4, 5]", mathSet.toString());
    }

    @Test
    @Order(11)
    void sortDescFromIndexToIndex(){
        mathSet.add(6, 2, 4, 5, 0);
        mathSet.sortDesc(1, 4);
        assertEquals("[6, 5, 4, 2, 0]", mathSet.toString());
    }

    @Test
    @Order(12)
    void sortAscFromValue(){
        mathSet.add(6, 2, 4, 5, 0);
        mathSet.sortAsc(2);
        assertEquals("[6, 0, 2, 4, 5]", mathSet.toString());
    }

    @Test
    @Order(12)
    void sortDescFromValue(){
        mathSet.add(6, 2, 4, 5, 0);
        mathSet.sortDesc(2);
        assertEquals("[6, 5, 4, 2, 0]", mathSet.toString());
    }

    @Test
    @Order(13)
    void getByIndex(){
        mathSet.add(6, 2, 4, 5, 0);
        double v = mathSet.get(0).doubleValue();
        assertEquals(6, v);
    }

    @Test
    @Order(13)
    void getMax(){
        mathSet.add(6, 2, 4, 5, 0);
        double v = mathSet.getMax().doubleValue();
        assertEquals(6, v);
    }
    @Test
    @Order(14)
    void getMin(){
        mathSet.add(6, 2, 4, 5, 0);
        double v = mathSet.getMin().doubleValue();
        assertEquals(0, v);
    }

    @Test
    @Order(15)
    void getAverage(){
        mathSet.add(6, 2, 4, 5, 0);
        double v = mathSet.getAverage().doubleValue();
        assertEquals(3.4, v);
    }

    @Test
    @Order(16)
    void getMedian(){
        mathSet.add(6, 2, 4, 5, 0);
        double v = mathSet.getMedian().doubleValue();
        assertEquals(4, v);
    }
}
