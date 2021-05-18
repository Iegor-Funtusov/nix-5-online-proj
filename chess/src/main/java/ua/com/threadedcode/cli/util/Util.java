package ua.com.threadedcode.cli.util;

public class Util {
    public static int[] StringToIntegerArray(String string) {
        String[] str = string.split(",");
        int[] arr = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        return arr;
    }
}
