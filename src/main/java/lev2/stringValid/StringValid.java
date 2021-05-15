package lev2.stringValid;

import java.util.Map;
import java.util.Stack;

public class StringValid {

    public static boolean isStringValid(String str, Map<Character, Character> brk)
    {
        Stack<Character> stk = new Stack<>();

        for (char c : str.toCharArray())
        {
            if (brk.containsValue(c))
            {
                stk.push(c);
                System.out.println("String is Valid");
            }
            else
            {

                if (brk.containsKey(c))
                {
                    if (stk.isEmpty() || !brk.get(c).equals(stk.pop()))
                    {System.out.println("String is not Valid!");
                        return false;
                    }
                }
            }
        }
        return stk.isEmpty();
    }
}
