package ua.com.alevel.lib;

import java.util.Objects;

public class ReverseString {

    public static String reverse(String str) {
        Objects.requireNonNull(str);
        return reverseStr(str);
    }

    public static String reverse(String str, String subStr) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(subStr);
        return str.replaceAll(subStr, reverseStr(subStr));
    }

    public static String reverse(String str, int firstIndex, int lastIndex) {
        Objects.requireNonNull(str);
        isIndexRight(str, firstIndex, lastIndex);
        String subStr = str.substring(firstIndex, lastIndex + 1);
        return str.replaceFirst(subStr, reverseStr(subStr));
    }

    public static String reverse(String str, char firstChar, char lastChar) {
        Objects.requireNonNull(str);
        int firstChInd = str.indexOf(firstChar);
        int secondChInd = str.indexOf(lastChar);
        isIndexRight(str, firstChInd, secondChInd);
        return reverse(str, firstChInd, secondChInd);
    }

    public static String reverse(String str, String firstSubStr, String lastSubStr) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(firstSubStr);
        Objects.requireNonNull(lastSubStr);
        isIndexRight(str, str.indexOf(firstSubStr), str.indexOf(lastSubStr));
        int firstIndex = str.indexOf(firstSubStr);
        int secondIndex = str.indexOf(lastSubStr) + lastSubStr.length() - 1;
        return reverse(str, firstIndex, secondIndex);
    }

    public static String reverse(String str, CharSequence firstSeq, CharSequence lastSeq) {
        Objects.requireNonNull(str);
        int firstIndex = str.indexOf(firstSeq.toString());
        int secondIndex = str.indexOf(lastSeq.toString()) + lastSeq.length() - 1;
        isIndexRight(str, firstIndex, secondIndex - lastSeq.length() + 1);
        return reverse(str, firstIndex, secondIndex);
    }

    private static String reverseStr(String str) {
        char[] arr = str.toCharArray();
        String revStr = "";
        for (int i = arr.length - 1; i >= 0; i--) {
            revStr = revStr + arr[i];
        }
        return revStr;
    }

    private static void isIndexRight(String str, int firstIndex, int secondIndex) {
        if (str.length() < firstIndex || str.length() < secondIndex) {
            throw new IllegalArgumentException("index is out of bounds");
        }
        if (firstIndex >= secondIndex) {
            throw new IllegalArgumentException("first index must be lower then second");
        }
    }
}
