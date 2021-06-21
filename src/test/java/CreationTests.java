import Set.MathSet;
import Set.MathSetImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreationTests {

    public Number[] fillInNumbers(){
       Number []numbers = new Number[(int) (Math.random() * 20)];
        for(int i = 0; i < numbers.length; i++){
            numbers[i] = Math.random() * 10;
        }
        return numbers;
    }

    @Test
    public void constructorTest1(){
        Number []numbers = fillInNumbers();
        MathSet setToCheck = new MathSetImpl(numbers);

        Assertions.assertNotNull(setToCheck);
    }


    @Test
    public void constructorTest2(){
        Number []numbers1 = fillInNumbers();
        Number []numbers2 = fillInNumbers();
        Number []numbers3 = fillInNumbers();

        MathSet setToCheck = new MathSetImpl(numbers1, numbers2, numbers3);
        Assertions.assertNotNull(setToCheck);
    }

    @Test
    public void constructorTest3(){
        Number []numbers = fillInNumbers();
        MathSetImpl set = new MathSetImpl(numbers);

        MathSet setToCheck = new MathSetImpl(set);
        Assertions.assertNotNull(setToCheck);
    }




    @Test
    public void constructorTest4(){
        Number []numbers1 = fillInNumbers();
        MathSet set1 = new MathSetImpl(numbers1);

        Number []numbers2 = fillInNumbers();
        MathSet set2 = new MathSetImpl(numbers2);

        Number []numbers3 = fillInNumbers();
        MathSet set3 = new MathSetImpl(numbers3);

        MathSet setToCheck = new MathSetImpl(set1, set2, set3);
        Assertions.assertNotNull(setToCheck);
    }
}
