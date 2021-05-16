package lev2.stringValid;

import java.util.ArrayDeque;
import java.util.Deque;
public class StringValid {

    public static boolean isStringValid(String str)
    {  if (str == null) {
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
                    System.out.println("Invalid string!");
                    return false;
                }
            }
        }
        if (collection.isEmpty()) {
            System.out.println("String is valid.");
        } else {
            System.out.println("Invalid string!");
        }
        return collection.isEmpty();
    }

}
