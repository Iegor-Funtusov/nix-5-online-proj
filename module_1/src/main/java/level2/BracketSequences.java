package level2;

import java.util.LinkedList;
import java.util.Scanner;

/** Учитывая строку, содержащую символы '(', ')', '{', '}', '[' и ']',
 * определите, является ли входная строка допустимой.
 * Входная строка действительна, если:
 * Открытые скобки должны быть закрыты скобками того же типа.
 * Открытые скобки должны быть закрыты в правильном порядке.
 * Обратите внимание, что пустая строка также считается допустимой.
 */

public class BracketSequences {
    public static void Main() {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Введите строку, содержащую символы: '(', ')', '{', '}', '[' и ']:");
        String string = inputUser.nextLine();
       // System.out.println(string);
        if (!checkingSequence(string)) {
            System.out.println("Строка недопустимая!");
        } else System.out.println("Строка допустимая!");
        System.out.println();
    }

    static boolean checkingSequence(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case ')':
                case ']':
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char top = stack.pop();
                    if (top != ch) {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }
}
