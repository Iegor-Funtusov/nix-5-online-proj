package ua.com.nkrasnovoronka.tasks.level2.task1;

import java.util.ArrayDeque;
import java.util.Deque;

public class BracketParser {
    public static boolean isStringValid(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                deque.push(c);
            }
            if (c == ')' || c == ']' || c == '}') {
                int number = c == ')' ? 1 : 2; // using ascii code
                if (deque.isEmpty() || deque.pop() + number != c) {
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }
}
