package ua.com.nkrasnovoronka;

import ua.com.nkrasnovoronka.mathset.MathSet;
import ua.com.nkrasnovoronka.mathset.impl.MathSetImpl;

public class Main {
    public static void main(String[] args) {
        MathSet mathSet = new MathSetImpl(new Number[]{1, 3, 2, 0}, new Number[]{3, 4, 5});
        System.out.println(mathSet);
        System.out.println(mathSet.getAverage());
        mathSet.sortAsc();
        System.out.println(mathSet);
        System.out.println(mathSet.getMedian());
        System.out.println(mathSet.getMin());
        System.out.println(mathSet.getMax());

    }
}
