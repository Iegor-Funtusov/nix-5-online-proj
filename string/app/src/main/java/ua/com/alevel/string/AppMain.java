package ua.com.alevel.string;


import ua.com.alevel.string.lib.StringReverser;

public class AppMain {
    public static void main(String[] args) {
        String src = "Serhii Semuso";
        String dest = "rhii S";
        CharSequence firstCS = "S";
        CharSequence lastCS = "i";
        char firstChar = 'e';
        char lastChar = 'o';


        System.out.println("Current: " + src);

        System.out.println("Reversed: " + StringReverser.reverse(src));

        System.out.println("Reversed by substring: " + StringReverser.reverse(src, dest));

        System.out.println("Reversed by indexes: " + StringReverser.reverse(src, 1, 4));

        System.out.println("Reversed by chars: " + StringReverser.reverse(src, firstChar, lastChar));

        System.out.println("Reversed by char sequences: " + StringReverser.reverse(src, firstCS, lastCS));

    }
}
