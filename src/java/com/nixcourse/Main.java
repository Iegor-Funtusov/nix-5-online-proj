package com.nixcourse;

import com.nixcourse.util.StringReverser;

public class Main {
    public static void main(String[] args) {
        String message = "hello world";
        System.out.println(StringReverser.reverse(message));
        System.out.println(StringReverser.reverse(message, "worl"));
        System.out.println(StringReverser.reverse(message, 3, 7));
    }
}
