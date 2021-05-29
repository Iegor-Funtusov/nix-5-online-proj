package ua.com.nkrasnovoronka.util;

public class Util {
    public static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }

        return str.charAt(0) + str.substring(1).toLowerCase();
    }
}
