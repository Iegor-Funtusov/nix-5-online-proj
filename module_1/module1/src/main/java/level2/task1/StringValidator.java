package level2.task1;

import java.util.ArrayDeque;
import java.util.Deque;

public class StringValidator {

    public static boolean stringValidator(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String is valid.");
        }
        Deque<Character> collection = new ArrayDeque<>();
        for (char bracket : str.toCharArray()) {
            if (bracket == '(' || bracket == '[' || bracket == '{') {
                collection.push(bracket);
            }
            if (bracket == ')' || bracket == ']' || bracket == '}') {
                int number = bracket == ')' ? 1 : 2;
                if (collection.isEmpty() || collection.pop() + number != bracket) {
                    System.out.println("String is invalid.");
                    return false;
                }
            }
        }
        if (collection.isEmpty()) {
            System.out.println("String is valid.");
        } else {
            System.out.println("String is invalid.");
        }
        return collection.isEmpty();
    }
}
