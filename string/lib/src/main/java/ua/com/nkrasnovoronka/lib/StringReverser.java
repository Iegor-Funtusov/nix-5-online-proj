package ua.com.nkrasnovoronka.lib;

import java.util.Objects;

/**
 * Class for reversing string
 */
public class StringReverser {

    public static String reverse(String src) {
        Objects.requireNonNull(src);
        return stringRevers(src);
    }

    public static String reverse(String src, String dest) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(dest);
        return src.replace(dest, stringRevers(dest));
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        Objects.requireNonNull(src);
        checkIndex(src, firstIndex, lastIndex);
        String before = src.substring(0, firstIndex);
        String after = src.substring(lastIndex);
        return before + src.replace(src, stringRevers(src.substring(firstIndex, lastIndex))) + after;
    }

    public static String reverse(String src, String beginSymbol, String endSymbol) {
        Objects.requireNonNull(src);
        checkIndex(src, src.indexOf(beginSymbol), src.indexOf(endSymbol));
        int firstIndex = src.indexOf(beginSymbol);
        int secondIndex = src.indexOf(endSymbol);
        return reverse(src, firstIndex, secondIndex);
    }

    public static String reverse(String src, CharSequence beginSymbol, CharSequence endSymbol) {
        Objects.requireNonNull(src);
        int begin = src.indexOf(beginSymbol.toString());
        int end = src.indexOf(endSymbol.toString());
        checkIndex(src, begin, end);
        return reverse(src, begin, end);
    }

    public static String reverse(String src, char beginSymbol, char endSymbol) {
        Objects.requireNonNull(src);
        int begin = src.indexOf(beginSymbol);
        int end = src.indexOf(endSymbol);
        checkIndex(src, begin, end);
        return reverse(src, begin, end);
    }

    /**
     * Reverse string using array
     */
    private static String stringRevers(String src) {
        char[] chars = src.toCharArray();
        char tmp;
        for (int i = 0; i < chars.length / 2; i++) {
            tmp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = tmp;
        }
        return new String(chars);
    }

    /**
     * Check if method arguments is valid
     */
    private static void checkIndex(String str, int firstIndex, int secondIndex) {
        if (firstIndex >= secondIndex) {
            throw new IllegalArgumentException(String.format("firs index %s cannot be bigger or equal then secondIndex %d", firstIndex, secondIndex));
        }
        if (str.length() < firstIndex) {
            throw new IllegalArgumentException(String.format("index %d out of length %d", firstIndex, str.length()));
        }
        if (str.length() < secondIndex) {
            throw new IllegalArgumentException(String.format("index %d out of length %d", secondIndex, str.length()));
        }
    }


}
