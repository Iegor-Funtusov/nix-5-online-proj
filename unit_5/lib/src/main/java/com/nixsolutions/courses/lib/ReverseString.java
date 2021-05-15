package com.nixsolutions.courses.lib;

public class ReverseString {

    public static String reverse(String src) {
        char[] line = src.toCharArray();
        char[] result = new char[src.length()];
        for (int i = 0; i < line.length; i++) {
            result[i] = line[line.length - i - 1];
        }
        return new String(result);
    }

    public static String reverse(String src, String dest) {
        return src.replace(dest, reverse(dest));
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        return null;
    }

    public static String reverse(String src, char firstChar, char lastChar) {
        return null;
    }

    public static String reverse(String src, String firstString, String lastString) {
        return null;
    }
}
