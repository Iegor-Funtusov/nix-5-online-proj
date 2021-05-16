package ua.com.module1;

import java.util.Stack;

public class CheckString {
   public static boolean getCheckerString (String s){
       Stack<Character> stack = new Stack<>();
       for (int i = 0; i < s.length(); i++) {
           if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
               stack.push(s.charAt(i));
           } else if (s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']') {
               if (stack.size() == 0) {
                   return false;
               }
               switch (stack.pop()) {
                   case '(': {
                       if (s.charAt(i) != ')') {
                           return false;
                       }
                       break;
                   }
                   case '{': {
                       if (s.charAt(i) != '}') {
                           return false;
                       }
                       break;
                   }
                   case '[': {
                       if (s.charAt(i) != ']') {
                           return false;
                       }
                       break;
                   }
                   default: continue;
               }
           }
       }
       if(stack.size() == 0){
           return true;
       }
       return false;
   }
}
