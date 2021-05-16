package com.nixcourse.util;

public class StringReverser {
    public static String reverse(String src) {
        StringBuilder result = new StringBuilder();
        var temp = src.split(" ");
        for (var word : temp) {
            for (int i = word.length() - 1; i >= 0; --i) {
                result.append(word.charAt(i));
            }
            result.append(" ");
        }
        return result.toString();
    }

    public static String reverse(String src, String dest) {
        StringBuilder result = new StringBuilder(src);
        result.replace(result.indexOf(dest), result.lastIndexOf(dest), reverse(dest));
        result.delete(result.indexOf(dest)-1, result.indexOf(dest)+dest.length());
        return result.toString();
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        StringBuilder result = new StringBuilder(src);
        String dest = result.substring(firstIndex, lastIndex+1);
        return reverse(src, dest);
    }
}
