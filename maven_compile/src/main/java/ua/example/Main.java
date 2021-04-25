package ua.example;

import ua.example.test_package.TestClass;
import ua.example.test_package.SecondClass;

public class Main {

    public static void main(String[] args) {
        String s = "Test compile.";
        TestClass testObj = new TestClass();
        testObj.print(s);
        SecondClass test2 = new SecondClass();
        test2.testToUpperCase(s);
    }
}