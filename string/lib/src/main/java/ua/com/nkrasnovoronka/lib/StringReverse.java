package ua.com.nkrasnovoronka.lib;

public interface StringReverse {
    String reverse(String src);

    String reverse(String src, String dest);

    String reverse(String src, int firstIndex, int lastIndex);
}
