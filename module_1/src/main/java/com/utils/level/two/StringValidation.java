package com.utils.level.two;

import java.util.Stack;

public class StringValidation {

    public static boolean CheckValidation(String string) {
        Stack<Character> sequence = new Stack<>();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i)=='(' || string.charAt(i)=='{' || string.charAt(i)=='[') {
                sequence.push(string.charAt(i));
            }
            if (string.charAt(i)==')' || string.charAt(i)=='}' || string.charAt(i)==']') {
                if (sequence.isEmpty()) {
                    return false;
                }
                if (getReversedChar(string.charAt(i)) != sequence.peek()) {
                    return false;
                }
                sequence.pop();
            }
        }
        return sequence.isEmpty();
    }

    public static char getReversedChar(char sym) {
        switch (sym) {
            case '(':
                return ')';
            case '[':
                return ']';
            case '{':
                return '}';
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
            default:
                System.out.println("Wrong character passed!");
                return ' ';
        }
    }
}
