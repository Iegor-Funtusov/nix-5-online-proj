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
        String dest = src.substring(firstIndex + 1, lastIndex);
        return src.replace(dest, reverse(dest));
    }

    public static String reverse(String src, char firstChar, char lastChar) {
        return reverse(src, src.indexOf(firstChar), src.indexOf(lastChar));
    }

    public static String reverse(String src, String firstString, String lastString) {
        return reverse(src, firstString.charAt(firstString.length()-1), lastString.charAt(0));
    }
}
