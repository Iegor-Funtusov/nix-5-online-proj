package lev2.stringValid;

import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class StringGo {
    public static void StringRun(){
        Map<Character, Character> brackets = new Hashtable<>();
        brackets.put(']', '[');
        brackets.put('}', '{');
        brackets.put(')', '(');
        Scanner s = new Scanner(System.in);
        System.out.println("Write a string and press 'Enter': ");
        StringValid.isStringValid(s.nextLine(), brackets);
        System.out.println(" ");
    }}

