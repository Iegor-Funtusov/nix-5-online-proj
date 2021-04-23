package com.k4rnaj1k;
import org.apache.commons.lang3.*;

import java.util.Locale;

public class Crud {
    public static void main(String[] args) {
        System.out.println("Main in crud.");
        Secondary crud2 = new Secondary();
        crud2.print();
        System.out.println(StringUtils.upperCase("lower case string to upper case using string utils"));
        AnSecondary anSecondary = new AnSecondary();
        System.out.println("local string after creating an instance of another secondary class: " + anSecondary.getLocals());
        anSecondary.setLocals("changed");
        System.out.println("local string after setting it with lombok setter: " + anSecondary.getLocals());
    }
}
