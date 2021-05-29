package com.k4rnaj1k;

public class StringReverse {
    public static String reverse(String src) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= src.length(); i++) {
            res.append(src.charAt(src.length() - i));
        }
        return res.toString();
    }

    public static String reverse(String src, String  dest) {
        StringBuilder res = new StringBuilder(src);
        int start = src.indexOf(dest);
        for (int i = 1; i <= dest.length(); i++) {
            res.replace(start + i-1, start+i, String.valueOf(dest.charAt(dest.length()-i)));
        }
        return res.toString();
    }
    
    public static String reverse(String src, int firstIndex, int lastIndex){
        StringBuilder res = new StringBuilder(src);
        for (int i = firstIndex; i <= lastIndex; i++) {
            res.replace(i, i+1, String.valueOf(src.charAt(lastIndex-i)));
        }
        return res.toString();
    }
}
