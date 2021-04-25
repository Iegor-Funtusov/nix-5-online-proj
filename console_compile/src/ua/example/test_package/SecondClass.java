package ua.example.test_package;

import org.apache.commons.lang3.*;

public class SecondClass {

    public void testToUpperCase(String string){
        string = StringUtils.upperCase(string);
        System.out.println(string);
    }
}