package ua.com.alevel.lib;

import java.util.Stack;

public class StringValidator {

    public static boolean isValid(String str) {
        if (str == null) return true;

        Stack<Character> s = new Stack<>();
        char[] openBr = new char[]{'{', '[', '('};
        char[] closeBr = new char[]{'}', ']', ')'};

        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (isBracket(current, openBr))
                s.push(current);
            else if (isBracket(current, closeBr)){
                if (s.empty())
                    return false;
                if (s.pop() != openBr[indexOf(current, closeBr)])
                    return false;
            }
        }
        return true;
    }

    private static boolean isBracket(char ch, char[] arr) {
        for (char c : arr) {
            if (c == ch)
                return true;
        }
        return false;
    }

    private static int indexOf(char ch, char[] arr) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ch)
                index = i;
        }
        return index;
    }
}
