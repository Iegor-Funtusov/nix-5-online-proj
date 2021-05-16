package com.k4rnaj1k.level2;

import java.util.Scanner;

//import static com.k4rnaj1k.RunModule.s;

public class StringCheck {
    public static void run() {
        Scanner s = new Scanner(System.in);
        String regex = "\\s.*?|\\{([^\\[\\](){])+|\\[([]]|[^\\[(){}])+|\\((([^]\\[({}])+)";
        try {
            System.out.print("Please enter string.\n");
            String input = s.nextLine();
            System.out.println("input = " + input);
            if(input.replaceAll(regex, "").length()>0)
                throw new Exception("String isn't valid.");
            System.out.println("String is valid.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static String name = "String checker";
}
