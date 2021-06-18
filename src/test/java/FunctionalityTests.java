import SetPackage.MathSet;
import SetPackage.MathSetImpl;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FunctionalityTests {
    private static MathSet set;


    @BeforeAll
    public static void setUp(){
        set = new MathSetImpl(getRandomNumbers());
    }


    private static Number[] getRandomNumbers(){
        Number []numbers = new Number[(int) (Math.random() * 10)];
        for(int i = 0; i < numbers.length; i++){
            numbers[i] = Math.random() * Integer.MAX_VALUE;
        }
        return numbers;
    }


    @Test
    @Order(1)
    public void addTest1(){
        int prevSize = set.size();
        set.add(Math.random() * Integer.MAX_VALUE);
        Assertions.assertEquals(prevSize, set.size() - 1);
    }


    @Test
    @Order(2)
    public void addTest2(){
        int prevSize = set.size();
        Number []elementsToAdd = getRandomNumbers();
        set.add(elementsToAdd);
        Assertions.assertEquals(prevSize, set.size() - elementsToAdd.length);
    }


    @Test
    @Order(3)
    public void joinTest1(){
        int prevSize = set.size();
        MathSetImpl setToJoin = new MathSetImpl(getRandomNumbers());
        set.join(setToJoin);
        Assertions.assertEquals(set.size(), prevSize + setToJoin.size());
    }


    @Test
    @Order(4)
    public void joinTest2(){
        int prevSize = set.size();
        MathSetImpl[]setsToJoin = new MathSetImpl[(int) (Math.random() * 5)];
        int quantityOfElements = 0;
        for(int i = 0; i < setsToJoin.length; i++){
            setsToJoin[i] = new MathSetImpl(getRandomNumbers());
            quantityOfElements += setsToJoin[i].size();
        }
        set.join(setsToJoin);
        Assertions.assertEquals(set.size(), prevSize + quantityOfElements);
    }

    @Test
    @Order(5)
    public void getTest(){
        Number element = set.get((int)(Math.random() * (set.size() - 1)));
        Assertions.assertNotNull(element);
    }

    @Test
    @Order(6)
    public void getMaxTest(){
        Number element = set.getMax();
        Assertions.assertNotNull(element);
    }


    @Test
    @Order(7)
    public void getMinTest(){
        Number element = set.getMin();
        Assertions.assertNotNull(element);
    }


    @Test
    @Order(8)
    public void getAverageTest(){
        Number element = set.getAverage();
        Assertions.assertNotNull(element);
    }


    @Test
    @Order(9)
    public void getMedianTest(){
        Number element = set.getMedian();
        Assertions.assertNotNull(element);
    }


    @Test
    @Order(10)
    public void toArrayTest1(){
        Number []elements = set.toArray();
        Assertions.assertNotNull(elements);
        Assertions.assertEquals(set.size(), elements.length);
    }


    @Test
    @Order(11)
    public void toArrayTest2(){
        int firstIndex = (int)(Math.random() * (set.size() / 2));   //Первый индекс из первой половины мн-ва
        int lastIndex = set.size() - (int)(Math.random() * (set.size() / 2));    //Второй индекс - из второй части
        Number []elements = set.toArray(firstIndex, lastIndex);
        Assertions.assertNotNull(elements);
    }


    @Test
    @Order(12)
    public void squashTest(){
        int firstIndex = (int)(Math.random() * (set.size() / 2));
        int lastIndex = set.size() - (int)(Math.random() * (set.size() / 2));

        MathSet setToCheck = set.squash(firstIndex, lastIndex);
        Assertions.assertNotNull(setToCheck);
    }



    @Test
    @Order(13)
    public void sizeTest(){
        Assertions.assertNotEquals(set.size(), 0);
        int prevSize = set.size();
        set.add(Math.random());
        Assertions.assertEquals(prevSize + 1, set.size());
    }


    @Test
    @Order(14)
    public void clearTest1() {
        int prevSize = set.size();
        Number []numbersToDelete = new Number[(int)(Math.random() * set.size())];
        for(int i = 0; i < numbersToDelete.length; i++){
            numbersToDelete[i] = set.get((int)(Math.random() * (set.size() - 1)));
        }
        set.clear(numbersToDelete);
        Assertions.assertTrue( prevSize - numbersToDelete.length <= set.size());
    }


    @Test
    @Order(15)
    public void clearTest2(){
        set.clear();
        Assertions.assertEquals(0, set.size());
    }

}
