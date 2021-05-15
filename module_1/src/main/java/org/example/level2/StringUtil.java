package org.example.level2;

import java.util.Stack;

public class StringUtil {
    public boolean validateBrackets(String string) {

        Stack<Character> stack = new Stack();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '[' ||
                    string.charAt(i) == '{' ||
                    string.charAt(i) == '(') {

                stack.push(string.charAt(i));

            } else if (string.charAt(i) == ']' ||
                    string.charAt(i) == ')' ||
                    string.charAt(i) == '}'
            ) {
                try {
                    stack.pop();
                } catch (Exception e) {
                    return false;
                }
            }
        }
        System.out.println(stack);
        return stack.empty();
    }
}
