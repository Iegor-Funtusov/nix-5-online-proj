package com.nixsolutions.courses.level2;

import com.nixsolutions.courses.utils.Level2Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Level2 {

    public static void validateString(String src) {
        boolean isValid = true;
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
//                continue;
            } else if(c == ')' || c == ']' || c == '}'){
                if (stack.isEmpty()) {
                    isValid = false;
                    break;
                }
                switch (c) {
                    case ')':
                        if (stack.pop() != '(') {
                            isValid = false;
                        }
                        break;
                    case ']':
                        if (stack.pop() != '[') {
                            isValid = false;
                        }
                        break;
                    case '}':
                        if (stack.pop() != '{') {
                            isValid = false;
                        }
                        break;
                }
                if (!isValid) break;
            }
        }
        if(stack.isEmpty() && isValid) {
            System.out.println("Valid");
        } else {
            System.out.println("Not valid");
        }
    }

    public static void run() {
        System.out.println("-----Validating string with parentheses-----");
        BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
        try {
            while (true) {
                String src = Level2Utils.createString(reader);
                validateString(src);
                Level2Utils.printOptions();
                if(reader.readLine().equals("0")) {
                    break;
                }
            }
        } catch(IOException e) {
            System.out.println("Something went wrong");
        }
    }

}
