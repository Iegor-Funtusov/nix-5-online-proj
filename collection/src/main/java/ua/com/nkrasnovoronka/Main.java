package ua.com.nkrasnovoronka;

import ua.com.nkrasnovoronka.mathset.MathSet;
import ua.com.nkrasnovoronka.mathset.impl.MathSetImpl;

public class Main {
    public static void main(String[] args) {
        MathSet mathSet = new MathSetImpl(new Number[]{1, 2}, new Number[]{3, 4, 5});
        System.out.println(mathSet);
    }
}
